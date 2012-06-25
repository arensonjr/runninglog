package com.jeffa.runninglog.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.jeffa.runninglog.workouts.BadWorkoutException;
import com.jeffa.runninglog.workouts.IWorkout;


/**
 * Loads and dumps workouts to/from Strings.
 * 
 * @author arensonjr
 */
public abstract class AWorkoutFileStorage implements IWorkoutStorage
{
	// Helper classes to generate filenames
	private DateTimeFormatter fileNameFormatter = DateTimeFormat.forPattern( "yyyy_MM_dd_HH_mm_ss'.txt'" );
	private DateTimeFormatter dirNameFormatter  = DateTimeFormat.forPattern( "yyyy_MM" );
	// TODO: rootDir = LogDirProvider.getRootDir(); or something similar, to be more complicated
	// (e.g. handle {Windows, Mac, Linux} differently)
	private String rootDirName = System.getProperty("user.home") + File.separator + ".arensonRL";
	
	// Factory to give us blank workouts to fill in
	IWorkoutFactory factory;
	
	/**
	 * Changes the root logging directory for this workout object. If there are any logs in the old directory, moves
	 * them to the new directory.
	 * 
	 * If any of the movement or directory creation fails, the root logging directory will not change and this method
	 * will return false.
	 *  
	 * @param _path New root log directory
	 * @return true on success of moving files & creating new root logging directory; false on any failures 
	 */
	public Boolean changeRootDir( String _path )
	{
		// Create new directory
		File oldRootDir = new File( rootDirName );
		File newRootDir = new File( _path );
		if ( !newRootDir.exists() ) {
			if ( !newRootDir.mkdirs() ) {
				return Boolean.valueOf( false );
			}
		}
		
		// Move over old files
		for ( File curr : oldRootDir.listFiles() ) {
			if ( !curr.renameTo( new File( newRootDir, curr.getName() ) ) ) {
				return Boolean.valueOf( false );
			}
		}
		
		// If we've gotten to this point,
		return Boolean.valueOf( true );
	}
	
	/**
	 * Dumps a workout object, T, to a string that we can store in a file.
	 * @param _workout TODO
	 * @return TODO
	 */
	public String dump( IWorkout _workout )
	{
		StringBuilder ret = new StringBuilder();
		ret.append( _workout.getType() );
		ret.append( '\0' );
		
		for ( String field : _workout.getAllFields() ) {
			ret.append( field );
			ret.append( '\0' );
			ret.append( _workout.getField( field ) );
			ret.append( '\0' );
		}
		
		return ret.toString();
	}
	
	@Override
	public void store( IWorkout _workout ) throws BadWorkoutException, IOException
	{
		// Check for valid input
		DateTime workoutDate = _workout.getDate();
		if ( null == workoutDate ) {
			throw new BadWorkoutException( "Workout had null date" );
		}
		
		// Make sure we can create the directory to store it in 
		File toStoreIn = new File( rootDirName, dirNameFormatter.print( workoutDate ) );
		if ( !toStoreIn.mkdirs() ) {
			throw new IOException( "Could not write directory path to new wkout file, " +
				fileNameFormatter.print( workoutDate ) );
		}
		
		// Write to the file
		BufferedWriter wkoutWriter = new BufferedWriter( new FileWriter(
			new File( toStoreIn, fileNameFormatter.print( workoutDate ) ) ) );
		wkoutWriter.write( dump( _workout ) );
		wkoutWriter.close();
	}
	
	@Override
	public void storeAll( Collection< IWorkout > _workouts ) throws BadWorkoutException, IOException
	{
		// Just a wrapper around store()
		for ( IWorkout workout : _workouts ) {
			store( workout );
		}
	}
	
	/**
	 * Given the dumped string content of a workout, returns the workout object that it represents.
	 * 
	 * @param _workoutDump String content of a workout (as formatted by dump())
	 * @param _empty An empty workout of the expected return type for us to fill in
	 * @return Workout object representing the input string
	 * @throws BadWorkoutException for a malformatted input string, that does not correspond to the workout in question
	 */
	protected IWorkout parseWorkout( String _workoutDump ) throws BadWorkoutException
	{
		
		// Current position in our iteration through workoutDump
		int currPos = 0;
		
		// Check for correct workout type
		String name = _workoutDump.substring( 0, _workoutDump.indexOf( '\0' ) );
		IWorkout ret = factory.getNewInstance( name );
		if ( null == ret ) {
			throw new BadWorkoutException( "Found unknown workout type: " + name );
		}
		
		currPos = _workoutDump.indexOf( '\0' ) + 1;
		
		// Add all of the fields
		while ( currPos < _workoutDump.length() ) {
			// The key is everything before the first null char
			int newPos = _workoutDump.indexOf( '\0', currPos );
			if ( newPos == -1 ) {
				throw new BadWorkoutException( "Extra chars in string after " +
					"last key-value pair: " + _workoutDump.substring( currPos ) );
			}
			String key = _workoutDump.substring( currPos, newPos );
			currPos = newPos + 1;
			
			// Advance to the value, or complain if there is none
			newPos = _workoutDump.indexOf( '\0', currPos );
			if ( newPos == -1 ) {
				throw new BadWorkoutException( "Found key '" + key +
						"' with no value" );
			}
			String valueStr = _workoutDump.substring( currPos, newPos );
			try {
				ret.setField( key, valueStr );
			} catch ( IllegalArgumentException e ) {
				throw new BadWorkoutException( e );
			}
			currPos = newPos + 1;
		}
		
		// Check that none of the fields are missing
		for ( String field : ret.getAllFields() ) {
			if ( null == ret.getField( field ) ) {
				throw new BadWorkoutException( "Field " + field + " missing from input string" );
			}
		}
		
		return ret;
	}
	
	@Override
	public Set< IWorkout > loadAll() throws BadWorkoutException, IOException
	{
		Set< IWorkout > ret = new HashSet< IWorkout >();
		
		// Locate all of the workouts
		for ( File yearMonthDir : new File( rootDirName ).listFiles() ) {
			for ( File workoutFile : yearMonthDir.listFiles() ) {
				try {
					String fileTxt = readFile( workoutFile );
					ret.add( parseWorkout( fileTxt ) );
				} catch ( FileNotFoundException e ) {
					// It's not mission-critical that we find every workout, but we should probably keep track of it
					System.err.println( "ERROR: Filesystem changed out from under us; listed the file " +
						workoutFile.getName() + " but got a FileNotFoundError. Skipping" );
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * TODO: Refactor this to a "Files" utility class, or something? One with lots of static methods like this?
	 * @param _file File to read
	 * @return The full contents of the file as a string
	 * @throws IOException on file read errors
	 * @throws FileNotFoundException
	 */
	public static String readFile( File _file ) throws FileNotFoundException, IOException
	{
		// If these throw an exception, then we're done
		StringBuilder accumulator = new StringBuilder();
		BufferedReader reader = new BufferedReader( new FileReader( _file ) );
		
		// If this throws an exception, we need to make sure we close the reader before exiting
		String line;
		try {
			while ( null != ( line = reader.readLine() ) ) {
				accumulator.append( line );
			}
			
			return accumulator.toString();
		} finally {
			if ( null != reader ) {
				reader.close();
			}
		}
	}
}

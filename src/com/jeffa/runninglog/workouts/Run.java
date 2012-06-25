package com.jeffa.runninglog.workouts;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Implements a running workout for the Arenson running log.
 * 
 * @author arensonjr
 */
public class Run extends AWorkout {
	// Workout-specific variables
	private static Set< String > VALID_INT_FIELDS = new HashSet< String >(
		Arrays.asList( "time", "shoeID" ) );
	private static Set< String > VALID_STRING_FIELDS = new HashSet< String >(
			Arrays.asList( "comments", "weather" ) );
	private static Set< String > VALID_DOUBLE_FIELDS = new HashSet< String >(
			Arrays.asList( "pace", "miles" ) );
	private static Set< String > VALID_LONG_FIELDS = new HashSet< String >(
			Arrays.< String >asList( /* None */ ) );
	private static Set< String > ALL_FIELDS = new HashSet< String >(
			Arrays.asList( "time", "shoeID", "comments", "weather", "pace", "miles" ) );
	
	/**
	 * Setup default values for variables
	 */
	public Run()
	{
		setIntField( "time", null );
		setIntField( "shoeID", null );
		setDoubleField( "pace", null );
		setDoubleField( "miles", null );
		setStringField( "comments", null );
		setStringField( "weather", null );
	}
	
	@Override
	public Run getInstance()
	{
		return new Run();
	}

	@Override
	public String getType()
	{
		return "Run";
	}
	
	@Override
	public Set< String > getAllFields()
	{
		return ALL_FIELDS;
	}

	@Override
	public Set< String > getIntFields()
	{
		return VALID_INT_FIELDS;
	}
	
	@Override
	public Set< String > getStringFields()
	{
		return VALID_STRING_FIELDS;
	}
	
	@Override
	public Set< String > getLongFields()
	{
		return VALID_LONG_FIELDS;
	}
	
	@Override
	public Set< String > getDoubleFields()
	{
		return VALID_DOUBLE_FIELDS;
	}
}

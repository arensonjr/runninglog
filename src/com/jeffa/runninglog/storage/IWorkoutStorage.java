package com.jeffa.runninglog.storage;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import com.jeffa.runninglog.workouts.BadWorkoutException;
import com.jeffa.runninglog.workouts.IWorkout;

/**
 * A storage format for workouts. It will store to, and load from, the parameterized type
 * of the workout, for any given workout type.
 *
 *  One good implementation to create would be StringWorkoutStorage, which
 *  dumps the workouts to text strings in files, and another called
 *  DatabaseWorkoutStorage, which could dump the workouts to an sqlite
 *  connection.
 * 
 * @author arensonjr
 */
public interface IWorkoutStorage {
	/**
	 * ...
	 * @param _workout
	 * @throws BadWorkoutException if the input does not have an associated workout date
	 * @throws IOException on inability to write the workout
	 */
	void store( IWorkout _workout ) throws BadWorkoutException, IOException;
	
	/**
	 * ...
	 * @param _workouts
	 * @throws BadWorkoutException if the input does not have an associated workout date
	 * @throws IOException on inability to write the workouts
	 */
	void storeAll( Collection< IWorkout > _workouts ) throws BadWorkoutException, IOException;
	
	/**
	 * ...
	 * @return A collection of all the previously saved workouts found in the default resource location
	 * @throws BadWorkoutException if the stored workouts in the resource are not in the correct format
	 * @throws IOException on inability to read the workouts from the resource
	 */
	Set< IWorkout > loadAll() throws BadWorkoutException, IOException;
}

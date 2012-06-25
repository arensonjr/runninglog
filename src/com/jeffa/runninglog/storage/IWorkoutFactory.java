/**
 * 
 */
package com.jeffa.runninglog.storage;

import java.util.Set;

import com.jeffa.runninglog.workouts.IWorkout;

/**
 * A class to generate workouts based on their name.
 * 
 * @author arensonjr
 */
public interface IWorkoutFactory {
	/**
	 * @param name Name of the IWorkout implementation to get 
	 * @return Instantiation of an IWorkout, or null if the name did not match
	 */
	IWorkout getNewInstance( String _name );
	
	/**
	 * @return A set of all workout names known by this factory
	 */
	Set< String > getKnownWorkouts();
}

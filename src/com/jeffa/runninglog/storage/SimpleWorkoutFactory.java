package com.jeffa.runninglog.storage;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.jeffa.runninglog.workouts.IWorkout;
import com.jeffa.runninglog.workouts.Run;

public class SimpleWorkoutFactory implements IWorkoutFactory {
	// Which workouts do we know how to instantiate?
	private static Set< IWorkout > KNOWN_WORKOUTS = new HashSet< IWorkout >(
			Arrays.asList( new Run() ) );
	private static Set< String > KNOWN_WORKOUTS_NAMES = new HashSet< String >(
			Arrays.asList( new Run().getType() ) );
	
	@Override
	public IWorkout getNewInstance( String _name )
	{
		for ( IWorkout curr : KNOWN_WORKOUTS ) {
			if ( _name.equals( curr.getType() ) ) {
				return curr.getInstance();
			}
		}
		
		// If none matched:
		return null;
	}
	
	@Override
	public Set< String > getKnownWorkouts()
	{
		return KNOWN_WORKOUTS_NAMES;
	}
}

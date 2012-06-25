package com.jeffa.runninglog.workouts;

/**
 * This exception should be thrown for an improper instantiation or invalid
 * state of an IWorkout object.
 * 
 * @author arensonjr
 */
public class BadWorkoutException extends Exception {
	private static final long serialVersionUID = 1L;

	public BadWorkoutException( String _msg )
	{
		super( _msg );
	}
	
	public BadWorkoutException( Exception e )
	{
		super( e );
	}
}

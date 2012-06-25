package com.jeffa.runninglog.workouts;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;

/** 
 * @author arensonjr
 */
public abstract class AWorkout implements IWorkout {	
	// The date of this workout
	protected DateTime date;
	
	// Workout variables
	protected Map< String, String > stringFields = new HashMap< String, String >();
	protected Map< String, Integer > intFields = new HashMap< String, Integer >();
	protected Map< String, Long > longFields = new HashMap< String, Long >();
	protected Map< String, Double > doubleFields = new HashMap< String, Double >();
	
	// ID for workouts
	protected Integer id;
	protected static Integer numWorkouts;
	
	/**
	 * Establish a workout ID for this workout
	 */
	public AWorkout()
	{
		id = ++numWorkouts;
	}
	
	/**
	 * Return the valid fields of this workout.
	 */
	public abstract Set< String > getAllFields();
	
	/**
	 * @return A set of all valid Integer fields of this workout.
	 */
	public abstract Set< String > getIntFields();
	
	/**
	 * @return A set of all valid Long fields of this workout.
	 */
	public abstract Set< String > getLongFields();
	
	/**
	 * @return A set of all valid Double fields of this workout.
	 */
	public abstract Set< String > getDoubleFields();
	
	/**
	 * @return A set of all valid String fields of this workout.
	 */
	public abstract Set< String > getStringFields();
	
	@Override
	public abstract IWorkout getInstance();
	
	/**
	 * Load a workout with a given workout ID
	 */
	public AWorkout( Integer _id )
	{
		id = _id;
		if ( numWorkouts < id ) {
			numWorkouts = id;
		}
	}
	
	@Override
	public boolean isStringField( String _fieldName )
	{
		return getStringFields().contains( _fieldName );
	}
	
	@Override
	public boolean isIntField( String _fieldName )
	{
		return getIntFields().contains( _fieldName );
	}
	
	@Override
	public boolean isDoubleField( String _fieldName )
	{
		return getDoubleFields().contains( _fieldName );
	}
	
	@Override
	public boolean isLongField( String _fieldName )
	{
		return getLongFields().contains( _fieldName );
	}
	
	@Override
	public DateTime getDate() {
		return date;
	}
	
	@Override
	public void setDate( DateTime _date )
	{
		date = _date;
	}
	
	@Override
	public void setIntField( String _fieldName, Integer _value ) throws IllegalArgumentException
	{
		if ( getIntFields().contains( _fieldName ) ) {
			intFields.put( _fieldName, _value );
		} else {
			throw new IllegalArgumentException( _fieldName + " is not a valid" +
				" Integer field for " + getType() );
		}
	}
	
	@Override
	public void setLongField( String _fieldName, Long _value ) throws IllegalArgumentException
	{
		if ( getLongFields().contains( _fieldName ) ) {
			longFields.put( _fieldName, _value );
		} else {
			throw new IllegalArgumentException( _fieldName + " is not a valid" +
				" Long field for " + getType() );
		}
	}
	
	@Override
	public void setDoubleField( String _fieldName, Double _value ) throws IllegalArgumentException
	{
		if ( getDoubleFields().contains( _fieldName ) ) {
			doubleFields.put( _fieldName, _value );
		} else {
			throw new IllegalArgumentException( _fieldName + " is not a valid" +
				" Double field for " + getType() );
		}
	}
	
	@Override
	public void setStringField( String _fieldName, String _value ) throws IllegalArgumentException
	{
		if ( getStringFields().contains( _fieldName ) ) {
			stringFields.put( _fieldName, _value );
		} else {
			throw new IllegalArgumentException( _fieldName + " is not a valid" +
				" String field for " + getType() );
		}
	}
	
	@Override
	public void setField( String _fieldName, String _valueStr ) throws IllegalArgumentException, NumberFormatException
	{
		if ( getIntFields().contains( _fieldName ) ) {
			setIntField( _fieldName, Integer.parseInt( _valueStr ) );
		}
		
		else if ( getDoubleFields().contains( _fieldName ) ) {
			setDoubleField( _fieldName, Double.parseDouble( _valueStr ) );
		}
		
		else if ( getLongFields().contains( _fieldName ) ) {
			setLongField( _fieldName, Long.parseLong( _valueStr ) );
		}
		
		else if ( getStringFields().contains( _fieldName ) ) {
		setStringField( _fieldName, _valueStr );
		}
		
		else { // None of the field types matched
			throw new IllegalArgumentException( _fieldName + " is not a valid field for " + getType() );
		}
	}
	
	@Override
	public String getField( String _fieldName ) throws IllegalArgumentException
	{
		// This is just a wrapper around all of the individual getters.
		if ( getIntFields().contains( _fieldName ) ) {
			return  getIntField( _fieldName ).toString();
		} else if ( getLongFields().contains( _fieldName ) ) {
			return  getLongField( _fieldName ).toString();
		} else if ( getDoubleFields().contains( _fieldName.toString()  ) ) {
			return  getDoubleField( _fieldName ).toString();
		} else if ( getStringFields().contains( _fieldName ) ) {
			return getStringField( _fieldName );
		} else {
			throw new IllegalArgumentException( _fieldName + " is not a valid fieldname for " + getType() );
		}
	}
	
	@Override
	public Integer getIntField( String _fieldName )
	{
		if ( getIntFields().contains( _fieldName ) ) {
			return getIntField( _fieldName );
		} else {
			throw new IllegalArgumentException( _fieldName + " is not a valid Integer fieldname for " + getType() );
		}
	}
	
	@Override
	public Long getLongField( String _fieldName )
	{
		if ( getLongFields().contains( _fieldName ) ) {
			return getLongField( _fieldName );
		} else {
			throw new IllegalArgumentException( _fieldName + " is not a valid Long fieldname for " + getType() );
		}
	}
	
	@Override
	public Double getDoubleField( String _fieldName )
	{
		if ( getDoubleFields().contains( _fieldName ) ) {
			return getDoubleField( _fieldName );
		} else {
			throw new IllegalArgumentException( _fieldName + " is not a valid Double fieldname for " + getType() );
		}
	}
	
	@Override
	public String getStringField( String _fieldName )
	{
		if ( getStringFields().contains( _fieldName ) ) {
			return getStringField( _fieldName );
		} else {
			throw new IllegalArgumentException( _fieldName + " is not a valid String fieldname for " + getType() );
		}
	}
	
	
	/**
	 * Internal helper method to parse a pace from a string representation.
	 * Valid string representations are as follows:
	 * <ul>
	 *     <li>"MM:SS", parsed as minutes per mile
	 *     <li>Output of Double.toString(), parsed as minutes per mile
	 * </ul> 
	 * @param _pace String representing pace
	 * @return Pace as a Double, in minutes per mile
	 */
	protected Double parsePace( String _pace ) throws NumberFormatException
	{
		Double finalPace = null;
		
		// Case: Double.toString()
		try {
			finalPace = Double.parseDouble( _pace );
			return finalPace;
		} catch ( NumberFormatException e ) {}
		
		// Case: "MM:SS"
		try {
			String[] minAndSec = _pace.split( ":" );
			if ( minAndSec.length != 2 ) {
				throw new NumberFormatException( "Bad pace str: not a double," +
					" and bad number of ':' -- " + _pace );
			}
			
			// If there were exactly two elements, we have our minutes & seconds
			finalPace = Double.parseDouble( minAndSec[ 0 ] ) +
				( Double.parseDouble( minAndSec[ 1 ] ) / 60.0 );
			return finalPace;
		} catch ( NumberFormatException e ) {
			throw e; // We were going to throw one anyway
		}
	}
	
	/**
	 * Internal helper method to format a pace for transfer as a string. Takes
	 * a Double value in min/mile and returns a string of the form "[M...]M:SS".
	 * @param _pace Double representation of a pace
	 * @return String representation of a pace value
	 */
	protected String formatPace( Double _pace )
	{
		Integer min = _pace.intValue();
		Integer sec = new Double( ( _pace - _pace.intValue() ) * 60.0 ).intValue();
		
		// Seconds, we have to figure out some place value (minutes are fine)
		String secStr = sec.toString();
		if ( secStr.length() < 2 ) {
			secStr = "0" + secStr;
		}
		
		return min.toString() + ":" + secStr;
	}
}

package com.jeffa.runninglog.workouts;

import java.util.Set;

import org.joda.time.DateTime;

/**
 * This interface specifies the data bundle that can be handled by this running
 * log application.
 * 
 * @author arensonjr
 */
public interface IWorkout
{	
	/**
	 * Return the date of the workout.
	 */
	DateTime getDate();
	
	/**
	 * Set the date of the workout
	 */
	void setDate( DateTime _date );
	
	/**
	 * Return the type of the workout. This should be a string that can be
	 * displayed as a type to an end user, or can be used as a unique string
	 * identifier for this workout type. 
	 */
	String getType();
	
	/**
	 * Return the valid fields of this workout.
	 */
	Set< String > getAllFields();
	
	/**
	 * Return the valid Integer fields of this workout.
	 */
	Set< String > getIntFields();
	
	/**
	 * Return the valid Long fields of this workout.
	 */
	Set< String > getLongFields();
	
	/**
	 * Return the valid Double fields of this workout.
	 */
	Set< String > getDoubleFields();
	
	/**
	 * Return the valid String fields of this workout.
	 */
	Set< String > getStringFields();
	
	/**
	 * Return the display ("toString()") value for a given field.
	 * @param _fieldName The field to retrieve data for 
	 * @throws IllegalArgumentException if it is not a valid field of the
	 *         workout.
	 */
	String getField( String _fieldName ) throws IllegalArgumentException;
	
	/**
	 * Return an Integer field's value.
	 * @param _fieldName The field to retrieve data for 
	 * @throws IllegalArgumentException if it is not a valid Integer field
	 *         of the workout.
	 */
	Integer getIntField( String _fieldName ) throws IllegalArgumentException;
	
	/**
	 * Return an Long field's value.
	 * @param _fieldName The field to retrieve data for 
	 * @throws IllegalArgumentException if it is not a valid Long field
	 *         of the workout.
	 */
	Long getLongField( String _fieldName ) throws IllegalArgumentException;
	
	/**
	 * Return an Double field's value.
	 * @param _fieldName The field to retrieve data for 
	 * @throws IllegalArgumentException if it is not a valid Double field
	 *         of the workout.
	 */
	Double getDoubleField( String _fieldName ) throws IllegalArgumentException;
	
	/**
	 * Return an String field's value.
	 * @param _fieldName The field to retrieve data for 
	 * @throws IllegalArgumentException if it is not a valid String field
	 *         of the workout.
	 */
	String getStringField( String _fieldName ) throws IllegalArgumentException;
	
	/**
	 * Given a field name, set it to the appropriate value.
	 * @throws IllegalArgumentException if the input fieldName is not a valid
	 *         String field -- should be contained in the output of
	 *         getStringFields()
	 */
	void setStringField( String _fieldName, String _value ) throws IllegalArgumentException;
	
	/**
	 * Given a field name, set it to the appropriate value.
	 * @throws IllegalArgumentException if the input fieldName is not a valid
	 *         Integer field -- should be contained in the output of
	 *         getStringFields()
	 */
	void setIntField( String _fieldName, Integer _value ) throws IllegalArgumentException;
	
	/**
	 * Given a field name, set it to the appropriate value.
	 * @throws IllegalArgumentException if the input fieldName is not a valid
	 *         Long field -- should be contained in the output of
	 *         getStringFields()
	 */
	void setLongField( String _fieldName, Long _value ) throws IllegalArgumentException;
	
	/**
	 * Given a field name, set it to the appropriate value.
	 * @throws IllegalArgumentException if the input fieldName is not a valid
	 *         Double field -- should be contained in the output of
	 *         getStringFields()
	 */
	void setDoubleField( String _fieldName, Double _value ) throws IllegalArgumentException;
	
	/**
	 * Given a field name, parse the input string to the correct type and set the field's value.
	 * @param _fieldName Field to set
	 * @param _valueStr = [TRUE VALUE].toString()
	 * @throws IllegalArgumentException if _fieldName is not a valid field for this workout
	 * @throws NumberFormatException if _valueStr is not a valid value for the given field
	 */
	void setField( String _fieldName, String _valueStr ) throws IllegalArgumentException, NumberFormatException;
	
	/**
	 * @param _fieldName The field to check
	 * @return true if it is a String field, false if it is not
	 */
	boolean isStringField( String _fieldName );
	
	/**
	 * @param _fieldName The field to check
	 * @return true if it is a Integer field, false if it is not
	 */
	boolean isIntField( String _fieldName );
	
	/**
	 * @param _fieldName The field to check
	 * @return true if it is a Double field, false if it is not
	 */
	boolean isDoubleField( String _fieldName );
	
	/**
	 * @param _fieldName The field to check
	 * @return true if it is a Long field, false if it is not
	 */
	boolean isLongField( String _fieldName );
	
	/**
	 * @return An empty instantiation of the concrete class.
	 */
	IWorkout getInstance();
}

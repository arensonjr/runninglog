package com.jeffa.runninglog.view.swing;

import java.util.List;

import javax.swing.JPanel;

import com.jeffa.runninglog.workouts.IWorkout;

public class AddPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private final RunningGui master;
	
	// Page organization
	private JPanel typeAndDate;
	private List< JPanel > fields;

	public AddPanel( RunningGui _master )
	{
		// Make sure we're still a valid JPanel
		super();
		
		master = _master;
		setup();
	}
	
	/**
	 * Set up the GUI to get ready for addition of workouts.
	 */
	private void setup()
	{
		/*
		 * The general setup for the panel is as follows:
		 * 
		 * +----------------------------------------------------+
		 * | Workout Type: _______  | Workout Date: ___________ | <-- DatePicker?
		 * +------------------------+---------------------------+
		 * | Field One: _________   |      ...                  |
		 * | Field Two: _________   |      ...                  |
		 * |        < etc. >        |      ...                  |
		 * |          ...           |      ...                  |
		 * |          ...           |      ...                  |
		 * +----------------------------------------------------+
		 */
		
	}
}

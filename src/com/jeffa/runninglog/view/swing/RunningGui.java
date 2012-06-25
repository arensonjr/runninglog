package com.jeffa.runninglog.view.swing;

import java.io.IOException;
import java.util.Set;

import javax.swing.JFrame;

import com.jeffa.runninglog.storage.IWorkoutStorage;
import com.jeffa.runninglog.storage.IWorkoutFactory;
import com.jeffa.runninglog.storage.SimpleWorkoutFactory;
import com.jeffa.runninglog.storage.SimpleWorkoutStorage;
import com.jeffa.runninglog.workouts.BadWorkoutException;
import com.jeffa.runninglog.workouts.IWorkout;

/**
 * Starts a GUI window used to manage an Arenson running log.
 * 
 * @author arensonjr
 */
public class RunningGui {
	/**
	 * Two function stubs to make this command-line-runnable
	 */
	public RunningGui() {}
	public static void main( String[] args )
	{
		RunningGui runMe = new RunningGui();
		try {
			runMe.run();
			System.exit( 0 );
		} catch ( Exception e ) {
			System.err.println( "Got Exception: " + e.getMessage() );
			System.exit( 1 );
		}
	}
	
	/********************* Internals *********************/
	
	// Where our stuff is stored
	Set< IWorkout > allWorkouts;
	IWorkoutStorage mainStorage = new SimpleWorkoutStorage();
	IWorkoutFactory mainFactory = new SimpleWorkoutFactory();
	RunningTabs mainGui;
	
	/**
	 * Launch a running log GUI, connected to the default storage location.
	 */
	public void run() throws IOException, BadWorkoutException
	{
		// Load in our data
		allWorkouts = mainStorage.loadAll();
		
		// Generate our GUI
		JFrame mainFrame = new JFrame();
		mainGui = createMainGui();
        mainGui.setOpaque( true );
        mainFrame.setContentPane( mainGui );
        mainFrame.setVisible( true );
        mainFrame.repaint();
	}
	
	/**
	 * @return A new, configured RunningGUI
	 */
	private RunningTabs createMainGui()
	{
		return new RunningTabs( this, mainFactory );
	}
}

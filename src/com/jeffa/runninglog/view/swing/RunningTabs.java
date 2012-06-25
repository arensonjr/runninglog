package com.jeffa.runninglog.view.swing;

import java.util.Set;

import javax.swing.JTabbedPane;

import com.jeffa.runninglog.storage.IWorkoutFactory;

/**
 * The main JTabbedFrame instance for a RunningGui.
 * 
 * 
 * 
 * @author arensonjr
 *
 */
public class RunningTabs extends JTabbedPane
{
	private static final long serialVersionUID = 1L;
	
	// The Running GUI we're attached to
	private final RunningGui master;
	
	// The factory that generates new workouts for us
	private final IWorkoutFactory factory;
	
	/**
	 * Creates a new JTabbedPane for a running log.
	 * @param _master RunningGui that this pane should be attached to
	 */
	public RunningTabs( RunningGui _master, IWorkoutFactory _factory )
	{
		// Make sure we're still a valid JTabbedPane too
		super( JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT );
		
		master = _master;
		factory = _factory;
		// Setup our tabs/children
		setupChildren();
	}

	/**
	 * Creates / sets up the panels in this pane. 
	 */
	private void setupChildren()
	{
		addTab( "Add",       new AddPanel( master ) );
		addTab( "Search",    new SearchPanel( master ) );
		addTab( "Equipment", new EquipmentPanel( master ) ); // TODO: I'm going to need IEquipment etc. -- dynamically generated types?
		// addTab( "" ) ...
	}
}

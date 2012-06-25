package com.jeffa.runninglog.view.swing;

import javax.swing.JPanel;

public class EquipmentPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private final RunningGui master;

	public EquipmentPanel( RunningGui _master )
	{
		// Make sure we're still a valid JPanel
		super();
		
		master = _master;
	}
}

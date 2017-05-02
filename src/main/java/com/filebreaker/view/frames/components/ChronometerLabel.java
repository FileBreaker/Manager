package com.filebreaker.view.frames.components;

import javax.swing.JLabel;

import com.filebreaker.view.utils.TimeUtils;

public class ChronometerLabel extends JLabel implements ExecutionTimeListener {

	private static final long serialVersionUID = 1L;
	
	private ExecutionTimeState executionTimeState;

	public ChronometerLabel(ExecutionTimeState executionTimeState) {
		this.executionTimeState = executionTimeState;
		this.executionTimeState.attach(this);
		
		initGUI();
	}

	public void updateExecutionTime() {
		setText(TimeUtils.getDuration(executionTimeState.getExecutionTime()));
	}
	
	private void initGUI() {
		setFont(new java.awt.Font("Lucida Grande", 0, 24));
        setText(TimeUtils.getDuration(executionTimeState.getExecutionTime()));
	}

}

package com.filebreaker.view.frames.components;

import javax.swing.JLabel;

import com.filebreaker.view.frames.listeners.ExecutionTimeListener;
import com.filebreaker.view.i18n.I18n;

public class OscillationNumberLabel extends JLabel implements ExecutionTimeListener {

	private static final long serialVersionUID = 1L;
	
	private ExecutionTimeState executionTimeState;
	
	public OscillationNumberLabel(ExecutionTimeState executionTimeState){
		this.executionTimeState = executionTimeState;
		this.executionTimeState.attach(this);
		this.setOscillations(executionTimeState.getOscillations());
		
		initGUI();
	}

	public void setOscillations(int oscillations){
		setText(oscillations + " " + I18n.getInstance().getString("sample.oscillations"));
	}

	public void updateExecutionTime() {
		setOscillations(executionTimeState.getOscillations());
	}
	
	private void initGUI() {
		setFont(new java.awt.Font("Lucida Grande", 0, 24));
	}
}
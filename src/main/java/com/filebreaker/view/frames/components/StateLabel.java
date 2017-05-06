package com.filebreaker.view.frames.components;

import java.awt.Color;

import javax.swing.JLabel;

import com.filebreaker.view.frames.listeners.EngineStateListener;
import com.filebreaker.view.i18n.I18n;

public class StateLabel extends JLabel implements EngineStateListener {

	private static final long serialVersionUID = 1L;

	private EngineState fileBreakerState;
	
	public StateLabel(EngineState fileBreakerState){
		super();
		
		this.fileBreakerState = fileBreakerState;
		this.fileBreakerState.attach(this);
		initGUI();
		setStateOff();
	}

	public void updateSpeed() {
		if(fileBreakerState.isRunning()) {
			setStateOn();
			return;
		}
			
		setStateOff();
	}

	private void initGUI() {
        setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
	}

	private void setStateOn() {
		setText(I18n.getInstance().getString("sample.on"));
		setForeground(Color.GREEN);
	}

	private void setStateOff() {
		setText(I18n.getInstance().getString("sample.off"));
		setForeground(Color.RED);
	}
}
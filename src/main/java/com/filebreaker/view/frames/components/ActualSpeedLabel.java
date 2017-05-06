package com.filebreaker.view.frames.components;

import javax.swing.JLabel;

import com.filebreaker.view.frames.listeners.EngineStateListener;
import com.filebreaker.view.i18n.I18n;

public class ActualSpeedLabel extends JLabel implements EngineStateListener {

	private static final long serialVersionUID = 1L;

	private EngineState fileBreakerEngineState;
	
	public ActualSpeedLabel(EngineState engineState) {
		this.fileBreakerEngineState = engineState;
		this.fileBreakerEngineState.attach(this);
		
		initGUI();
	}

	public void updateSpeed() {
		setSpeedText(fileBreakerEngineState.getSpeedPercentage());
	}
	
	private void initGUI() {
		setFont(new java.awt.Font("Lucida Grande", 0, 24)); 
        setSpeedText(0);
	}

	private void setSpeedText(int speed) {
		setText(I18n.getInstance().getString("speed.actual") + " " + speed + "%");
	}

}
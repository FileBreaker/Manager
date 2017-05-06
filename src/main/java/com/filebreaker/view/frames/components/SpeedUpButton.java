package com.filebreaker.view.frames.components;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.filebreaker.view.frames.listeners.EngineStateListener;
import com.filebreaker.view.i18n.I18n;

public class SpeedUpButton extends JButton implements EngineStateListener {

	private static final long serialVersionUID = 1L;
	
	private EngineState fileBreakerEngineState;

	public SpeedUpButton(EngineState fileBreakerEngineState){
		this.fileBreakerEngineState = fileBreakerEngineState;
		this.fileBreakerEngineState.attach(this);
		
		initGUI();
	}

	private void initGUI() {
		setIcon(new ImageIcon(getClass().getResource("/speed-up.png")));
        setText(I18n.getInstance().getString("speed.up"));
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	fileBreakerEngineState.speedUp();
            }
        });
	}

	public void updateSpeed() {
		if(!fileBreakerEngineState.isAtFullSpeed()) {
			this.setEnabled(true);
			return;
		}
		
		this.setEnabled(false);	
	}
}

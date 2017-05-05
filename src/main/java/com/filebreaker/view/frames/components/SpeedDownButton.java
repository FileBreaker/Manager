package com.filebreaker.view.frames.components;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.filebreaker.view.i18n.I18n;

public class SpeedDownButton extends JButton implements EngineStateListener {

	private static final long serialVersionUID = 1L;
	
	private EngineState fileBreakerEngineState;

	public SpeedDownButton(EngineState fileBreakerEngineState){
		this.fileBreakerEngineState = fileBreakerEngineState;
		this.fileBreakerEngineState.attach(this);
		
		initGUI();
	}

	private void initGUI() {
		setIcon(new ImageIcon(getClass().getResource("/speed-down.png"))); // NOI18N
        setText(I18n.getInstance().getString("speed.down"));
        addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	fileBreakerEngineState.speedDown();
            }
        });
	}

	public void updateSpeed() {
		if(fileBreakerEngineState.isRunning()){
			this.setEnabled(true);
			return;
		}
		
		this.setEnabled(false);
	}
}

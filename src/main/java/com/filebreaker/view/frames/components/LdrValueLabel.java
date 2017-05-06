package com.filebreaker.view.frames.components;

import javax.swing.JLabel;

import com.filebreaker.view.frames.listeners.LdrStateListener;
import com.filebreaker.view.i18n.I18n;

public class LdrValueLabel extends JLabel implements LdrStateListener {

	private static final long serialVersionUID = 1L;

	private LdrState ldrState;
	
	public LdrValueLabel(LdrState ldrState){
		this.ldrState = ldrState;
		this.ldrState.attach(this);
		
		initGUI();
	}

	public void updateLdr() {
		if(ldrState.isFileBroken()){
			setText(I18n.getInstance().getString("file.broken"));
			return;
		}
		
		setLdrValue(ldrState.getValue());
	}
	
	private void initGUI() {
		setFont(new java.awt.Font("Lucida Grande", 0, 24));
	    setLdrValue(0);
	}
	
	private void setLdrValue(Integer ldrValue) {
		setText(I18n.getInstance().getString("ldr.value") + " " + ldrValue);
	}
}

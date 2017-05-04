package com.filebreaker.view.frames.components;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class SampleEditorSpinner extends JSpinner {

	private static final long serialVersionUID = 1L;

	public SampleEditorSpinner() {
		
	}
	
	public SampleEditorSpinner(SpinnerNumberModel spinnerNumberModel) {
		super(spinnerNumberModel);
	}

	@Override
	public void setValue(Object value) {
		if(value == null) return;
		super.setValue(value);
	}
	
}
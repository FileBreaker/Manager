package com.filebreaker.view.frames.components;

import javax.swing.SpinnerNumberModel;

public class PositiveIntegerSpinnerNumberModel extends SpinnerNumberModel {

	private static final long serialVersionUID = 1L;

	public PositiveIntegerSpinnerNumberModel(){
		super(0, 0, null, 1);
	}
}

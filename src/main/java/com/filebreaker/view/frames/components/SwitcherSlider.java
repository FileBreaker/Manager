package com.filebreaker.view.frames.components;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SwitcherSlider extends JSlider implements EngineStateListener {

	private static final long serialVersionUID = 1L;
	
	private EngineState fileBreakerEngineState;
	
	public SwitcherSlider(final EngineState fileBreakerEngineState){
		initGUI();
		this.fileBreakerEngineState = fileBreakerEngineState;
		this.fileBreakerEngineState.attach(this);

        addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				javax.swing.JSlider source = (javax.swing.JSlider)evt.getSource();
			    
				if (!source.getValueIsAdjusting()) {
					int value = source.getValue();
					
					if(value == 0){
						fileBreakerEngineState.stop();
					} else if(value == 1 && fileBreakerEngineState.isStopped()){
						fileBreakerEngineState.speedUp();
					}
			    }
			}
		});
	}

	public void updateSpeed() {
		if(!fileBreakerEngineState.isRunning()){
			this.setValue(0);
			return;
		}
		this.setValue(1);
	}
	
	public boolean isActivated(){
		return this.getValue() == 0;
	}
	
	public boolean isDeactivated(){
		return !isActivated();
	}
	
	private void initGUI() {
		setValue(0);
        setMinimum(0);
        setMaximum(1);
        setMajorTickSpacing(1);
        setMinorTickSpacing(1);
        setSnapToTicks(true);
	}
}
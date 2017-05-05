package com.filebreaker.view;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import com.filebreaker.communications.SerialConnection;
import com.filebreaker.experiments.ExperimentsController;
import com.filebreaker.samples.SamplesController;
import com.filebreaker.view.frames.experiments.ExperimentsFrameView;

public class Gui extends SingleFrameApplication {
    
	private SerialConnection serialConnection;
	
	private SamplesController samplesController;
	
	private ExperimentsController experimentsController;
	
	public void startup(SerialConnection serialConnection, SamplesController samplesController, ExperimentsController experimentsController){
		this.serialConnection = serialConnection;
		this.samplesController = samplesController;
		this.experimentsController = experimentsController;
		
		this.startup();
	}
	
	public static Gui getApplication() {
		return Application.getInstance(Gui.class);
	}
    
	@Override
	public void startup() {
        show(new ExperimentsFrameView(this, experimentsController, serialConnection, samplesController));
    }
    
    @Override
    protected void configureWindow(java.awt.Window root) {/* nothing to do */}

}
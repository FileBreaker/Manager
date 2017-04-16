package com.filebreaker.view;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import com.filebreaker.controllers.MainController;
import com.filebreaker.experiments.ExperimentsController;
import com.filebreaker.samples.SamplesController;
import com.filebreaker.view.frames.experiments.ExperimentsFrameView;

public class Gui extends SingleFrameApplication {
    
	private MainController mainController;
	
	private SamplesController samplesController;
	
	private ExperimentsController experimentsController;
	
	public void startup(MainController mainController, SamplesController samplesController, ExperimentsController experimentsController){
		this.mainController = mainController;
		this.samplesController = samplesController;
		this.experimentsController = experimentsController;
		
		this.startup();
	}
	
	public static Gui getApplication() {
		return Application.getInstance(Gui.class);
	}
    
	@Override
	public void startup() {
        show(new ExperimentsFrameView(this, experimentsController, mainController, samplesController));
    }
    
    @Override
    protected void configureWindow(java.awt.Window root) {/* nothing to do */}

}
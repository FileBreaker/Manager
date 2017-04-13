package com.filebreaker.manager.view;

import org.jdesktop.application.SingleFrameApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.filebreaker.manager.controllers.MainController;
import com.filebreaker.manager.experiments.ExperimentsController;
import com.filebreaker.manager.samples.SamplesController;
import com.filebreaker.manager.view.frames.experiments.ExperimentsFrameView;

@Component
public class Gui extends SingleFrameApplication {
    
	private MainController mainController;
	
	private SamplesController samplesController;
	
	private ExperimentsController experimentsController;
	
	@Autowired
	public Gui(MainController mainController, SamplesController samplesController, ExperimentsController experimentsController){
		this.mainController = mainController;
		this.samplesController = samplesController;
		this.experimentsController = experimentsController;
	}
	
    @Override
	public void startup() {
        show(new ExperimentsFrameView(this, experimentsController, mainController, samplesController));
    }
    
    @Override
    protected void configureWindow(java.awt.Window root) {/* nothing to do */}

}
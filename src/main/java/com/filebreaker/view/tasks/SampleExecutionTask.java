package com.filebreaker.view.tasks;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.filebreaker.controllers.MainController;
import com.filebreaker.samples.SamplesController;
import com.filebreaker.view.frames.components.SampleState;

public class SampleExecutionTask {
	
	private static final int DELAY_MS = 0;

	private static final int REFRESH_PERIOD_MS = 50;

	private SampleState sampleState;
	
	private SamplesController samplesController;
	
	private MainController mainController;
	
	public SampleExecutionTask(SamplesController samplesController, MainController mainController, SampleState sampleState){
		this.samplesController = samplesController;
		this.mainController = mainController;
		this.sampleState = sampleState;
	}
	
	public void execute() {
		TimerTask timerTask = new TimerTask(){ 
			long diff = 0L;
			long millisNow = (new Date()).getTime();
			
			public void run(){
				if(sampleState.isEngineRunning()){
	     			diff = this.scheduledExecutionTime() - millisNow;
	     			sampleState.setExecutionTime(diff);
	     			sampleState.setLdrValue(mainController.getLdrValue());
	     			sampleState.setFileBroken(mainController.isFileBroken());
	     		} 
				
				if(sampleState.isFileBroken()){
	     			samplesController.saveState(sampleState.getExperimentId(), sampleState.getSampleId(), diff, sampleState.getOscillations());
	     			cancel();
	     		}
	         } 
	     };
	     
	     Timer timer = new Timer();
	     timer.scheduleAtFixedRate(timerTask, DELAY_MS, REFRESH_PERIOD_MS);
	}
}
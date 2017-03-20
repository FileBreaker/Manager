package com.filebreaker.manager.tasks;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.filebreaker.manager.controllers.MainController;
import com.filebreaker.manager.gui.frames.samples.SampleJFrame;
import com.filebreaker.manager.gui.utils.TimeUtils;

public class SampleExecutionTask {
	
	private SampleJFrame sampleJFrame;
	
	private MainController mainController;
	
	private Integer sampleId;
	
	private Integer experimentId;
	
	public SampleExecutionTask(SampleJFrame view, MainController mainController, Integer experimentId, Integer sampleId){
		this.sampleJFrame = view;
		this.mainController = mainController;
		this.sampleId = sampleId;
		this.experimentId = experimentId;
	}
	
	public void execute() {
		TimerTask timerTask = new TimerTask(){ 
			long diff = 0L;
			int oscillations = 0;
			long millisNow = (new Date()).getTime();
			
			public void run(){
	        	if(sampleJFrame.getSwitcherSlider().getValue() == 1 && !mainController.isFileBroken()){
	     			diff = this.scheduledExecutionTime() - millisNow;
	     			oscillations = mainController.getOscillations();
	     			
	     			sampleJFrame.setTimeToChronometerLabel(TimeUtils.getDuration(diff));
	     		} else {
	     			mainController.saveState(experimentId, sampleId, diff, oscillations);
	     			cancel();
	     		}
	         } 
	     };
	     
	     Timer timer = new Timer();
	     timer.scheduleAtFixedRate(timerTask, 0, 5);
	}
}
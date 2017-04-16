package com.filebreaker.view.tasks;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.filebreaker.controllers.MainController;
import com.filebreaker.samples.SamplesController;
import com.filebreaker.view.frames.samples.SampleJFrame;
import com.filebreaker.view.i18n.I18n;
import com.filebreaker.view.utils.TimeUtils;

public class SampleExecutionTask {
	
	private SampleJFrame sampleJFrame;
	
	private SamplesController samplesController;
	
	private MainController mainController;
	
	private Integer sampleId;
	
	private Integer experimentId;
	
	public SampleExecutionTask(SampleJFrame view, SamplesController samplesController, MainController mainController, Integer experimentId, Integer sampleId){
		this.sampleJFrame = view;
		this.samplesController = samplesController;
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
	     			sampleJFrame.setLdrValue(mainController.getLdrValue().toString());
	     		} else {
	     			if(mainController.isFileBroken()){
	     				sampleJFrame.setLdrValue(I18n.getInstance().getString("file.broken"));
	     			}
	     			
	     			samplesController.saveState(experimentId, sampleId, diff, oscillations);
	     			cancel();
	     		}
	         } 
	     };
	     
	     Timer timer = new Timer();
	     timer.scheduleAtFixedRate(timerTask, 0, 5);
	}
}
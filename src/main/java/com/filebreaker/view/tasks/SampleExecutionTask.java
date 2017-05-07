package com.filebreaker.view.tasks;

import java.util.Timer;
import java.util.TimerTask;

import com.filebreaker.communications.BufferedSerialPortEventListener;
import com.filebreaker.communications.FileBrokenListener;
import com.filebreaker.communications.SerialConnection;
import com.filebreaker.samples.SamplesController;
import com.filebreaker.view.frames.components.SampleState;

public class SampleExecutionTask implements FileBrokenListener {
	
	private static final int DELAY_MS = 0;

	private static final int REFRESH_PERIOD_MS = 1;

	private SampleState sampleState;
	
	private SamplesController samplesController;
	
	private SerialConnection serialConnection;
	
	private BufferedSerialPortEventListener bufferedSerialPortEventListener;
	
	private long executionTime;
	
	public SampleExecutionTask(SamplesController samplesController, SerialConnection serialConnection, SampleState sampleState){
		this.samplesController = samplesController;
		this.serialConnection = serialConnection;
		this.sampleState = sampleState;
		this.executionTime = sampleState.getExecutionTime();
		
		this.bufferedSerialPortEventListener = serialConnection.getBufferedSerialPortEventListener();
		
		if(this.bufferedSerialPortEventListener != null){
			this.bufferedSerialPortEventListener.attach(this);
		}
	}
	
	public void execute() {
		TimerTask timerTask = new TimerTask(){
			
			public void run(){
	     		refreshSampleState();
	     		saveSampleState();
				cancelExecution();
	        }

			private void refreshSampleState() {
				if(!sampleState.isEngineRunning()) return;
				
				executionTime += REFRESH_PERIOD_MS;
				sampleState.setExecutionTime(executionTime);
				sampleState.setLdrValue(serialConnection.getLdrValue());
			}
			
			private void saveSampleState() {
				if(sampleState.isEngineRunning()) return;
				saveState();
			}
			
			private void cancelExecution() {
				if(!sampleState.isFileBroken()) return;
				cancel();
			}
	     };
	     
	     Timer timer = new Timer();
	     timer.scheduleAtFixedRate(timerTask, DELAY_MS, REFRESH_PERIOD_MS);
	}

	public void update() {
		sampleState.setFileBroken(true);
	}

	public void closeTask(){
		saveState();
		closeConnection();
	}
	
	private void saveState() {
		samplesController.saveState(sampleState.getExperimentId(), sampleState.getSampleId(), executionTime, sampleState.getOscillations());
	}
	
	private void closeConnection(){
		serialConnection.closePort();
	}
}
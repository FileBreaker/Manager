package com.filebreaker.view.frames.components;

import com.filebreaker.controllers.MainController;
import com.filebreaker.samples.Sample;

public class SampleState {

	private EngineState engineState;
	
	private LdrState ldrState;
	
	private ExecutionTimeState executionTimeState;
	
	private Sample sample;
	
	public SampleState(Sample sample, MainController mainController, ExecutionTimeState executionTimeState, LdrState ldrState, EngineState engineState){
		this.sample = sample;
		this.executionTimeState = executionTimeState;
        this.ldrState = ldrState;
        this.engineState = engineState;
	}

	public int getSampleId(){
		return sample.getId();
	}
	
	public int getExperimentId(){
		return sample.getExperimentId();
	}
	
	public Integer getOscillations() {
		return executionTimeState.getOscillations();
	}

	public boolean isFileBroken(){
		return ldrState.isFileBroken();
	}
	
	public void setFileBroken(boolean isFileBroken){
		ldrState.setIsFileBroken(isFileBroken);
	}
	
	public boolean isEngineRunning(){
		return engineState.isRunning();
	}
	
	public void setExecutionTime(long millis){
		executionTimeState.setExecutionTime(millis);
	}
	
	public void setLdrValue(Integer value){
		ldrState.setValue(value);
	}
}
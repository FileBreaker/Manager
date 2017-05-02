package com.filebreaker.view.frames.components;

import java.util.ArrayList;
import java.util.List;

public class ExecutionTimeState {

	private static final int MILLIS_PER_MINUTE = 60000;

	private List<ExecutionTimeListener> observers = new ArrayList<ExecutionTimeListener>();

	private long executionMillis;
	
	private int oscillations;
	
	private Integer engineAngularSpeed;
	
	public ExecutionTimeState(Integer engineAngularSpeed){
		this.executionMillis = 0L;
		this.engineAngularSpeed = engineAngularSpeed;
	}
	
	public void setExecutionTime(long millis){
		this.executionMillis = millis;
		computeOscillations();
		notifyAllObservers();
	}
	
	public long getExecutionTime(){
		return executionMillis;
	}
	
	public int getOscillations(){
		return oscillations;
	}
	
	public void attach(ExecutionTimeListener observer) {
		observers.add(observer);
	}
	
	private void notifyAllObservers() {
		for (ExecutionTimeListener observer : observers) {
			observer.updateExecutionTime();
		}
	}
	
	private void computeOscillations() {
		if(engineAngularSpeed == null || engineAngularSpeed <= 0){
			oscillations = 0;
			return;
		}
		oscillations = (int) ((executionMillis / engineAngularSpeed) * MILLIS_PER_MINUTE);
	}
}

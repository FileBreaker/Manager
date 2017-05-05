package com.filebreaker.view.frames.components;

import java.util.ArrayList;
import java.util.List;

import com.filebreaker.communications.SerialConnection;
import com.filebreaker.view.frames.components.EngineSpeeds.EngineSpeedValue;

public class EngineState implements LdrStateListener {
	
	private List<EngineStateListener> observers = new ArrayList<EngineStateListener>();
	
	private EngineSpeedValue engineSpeed;
	
	private SerialConnection serialConnection;
	
	private LdrState ldrState;
	
	public EngineState(SerialConnection serialConnection, LdrState ldrState){
		this.serialConnection = serialConnection;
		
		this.ldrState = ldrState;
		ldrState.attach(this);
		
		this.engineSpeed = EngineSpeedValue.STOPPED;
	}

	public boolean isRunning() {
		return engineSpeed != EngineSpeedValue.STOPPED;
	}
	
	public boolean isStopped() {
		return !isRunning();
	}
	
	public boolean isAtFullSpeed() {
		return engineSpeed == EngineSpeedValue.FULL_SPEED;
	}
	
	public void speedUp() {
		setSpeed(this.engineSpeed.speedUp());
	}
	
	public void speedDown() {
		setSpeed(this.engineSpeed.speedDown());
	}
	
	public void stop(){
		setSpeed(EngineSpeedValue.STOPPED);
	}
	
	public void attach(EngineStateListener observer) {
		observers.add(observer);
	}
	
	public int getSpeedPercentage(){
		return engineSpeed.getSpeedPercentage();
	}

	public void updateLdr() {
		if(!ldrState.isFileBroken()) return;
		stop();
	}
	
	private void notifyAllObservers() {
		for (EngineStateListener observer : observers) {
			observer.updateSpeed();
		}
		
		serialConnection.setSpeed(engineSpeed.getSpeedPercentage());
	}

	private void setSpeed(EngineSpeedValue engineSpeed){
		if(engineSpeed == null) return;
		
		this.engineSpeed = engineSpeed;		
		notifyAllObservers();
	}
}
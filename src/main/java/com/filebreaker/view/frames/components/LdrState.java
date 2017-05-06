package com.filebreaker.view.frames.components;

import java.util.ArrayList;
import java.util.List;

import com.filebreaker.view.frames.listeners.LdrStateListener;

public class LdrState {

	private List<LdrStateListener> observers = new ArrayList<LdrStateListener>();

	private Integer value;
	
	private boolean isFileBroken;
	
	public LdrState(){
		value = 0;
		isFileBroken = false;
	}
	
	public void setIsFileBroken(boolean isFileBroken){
		this.isFileBroken = isFileBroken;
		notifyAllObservers();
	}
	
	public boolean isFileBroken(){
		return isFileBroken;
	}
	
	public void setValue(Integer value){
		if(value == null || isFileBroken()) return;
		this.value = value;
		
		notifyAllObservers();
	}
	
	public void attach(LdrStateListener observer) {
		observers.add(observer);
	}
	
	public Integer getValue() {
		return value;
	}
	
	private void notifyAllObservers() {
		for (LdrStateListener observer : observers) {
			observer.updateLdr();
		}
	}
	
}
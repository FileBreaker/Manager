package com.filebreaker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.filebreaker.communications.SerialConnectionManager;

import jssc.SerialPortException;

@Controller
public class MainController {
	
	private SerialConnectionManager serialConnectionManager;
	
	@Autowired
	public MainController(SerialConnectionManager serialConnectionManager){
		this.serialConnectionManager = serialConnectionManager;
	}

	public boolean isFileBroken() {
		return serialConnectionManager.isFileBroken();
	}

	public int getOscillations() {
		return 0;
	}

	public void setSpeed(int speedPercentage) throws SerialPortException{
		serialConnectionManager.setSpeed(speedPercentage);
	}
}
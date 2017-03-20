package com.filebreaker.manager.managers.serial;

import jssc.SerialPortException;

public interface SerialConnectionManager {

	void setSpeed(Integer speedPercentage) throws SerialPortException;
	
	boolean isFileBroken();
	
}
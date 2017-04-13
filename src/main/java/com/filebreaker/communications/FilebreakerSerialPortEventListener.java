package com.filebreaker.communications;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

class FilebreakerSerialPortEventListener implements SerialPortEventListener {

	private boolean stringComplete;
	
	private String inputString;
	
	private boolean isFileBroken;
	
	private SerialPort serialPort;
	
	public FilebreakerSerialPortEventListener(SerialPort serialPort){
		this.stringComplete = false;
		this.serialPort = serialPort;
		this.inputString = "";
		this.isFileBroken = false;
	}
	
	public void serialEvent(SerialPortEvent serialPortEvent) {
		if(!serialPortEvent.isRXCHAR()) return;
		
		try {
			char receivedChar = (char)serialPort.readBytes(1)[0];
			
			if(receivedChar == '\n') {
				stringComplete = true;
			} else if (receivedChar == 'r' || receivedChar == 'R') {
				System.out.println("ROTURA!!!!!!!!!: " + receivedChar);
				isFileBroken = true;
			}
			else {
				inputString = new StringBuilder(inputString).append(receivedChar).toString();
			}
			
			if (stringComplete) {
				System.out.println(inputString);
			    
			    // clear the string:
			    inputString = "";
			    stringComplete = false;
			  }
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isFileBroken(){
		return isFileBroken;
	}
}

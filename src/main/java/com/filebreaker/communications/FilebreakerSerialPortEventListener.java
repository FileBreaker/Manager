package com.filebreaker.communications;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

class FilebreakerSerialPortEventListener implements SerialPortEventListener {
	
	private String ldrValueAggregated;
	
	private boolean isFileBroken;
	
	private SerialPort serialPort;
	
	private Integer ldrValue;
	
	public FilebreakerSerialPortEventListener(SerialPort serialPort){
		this.ldrValueAggregated = "";
		this.isFileBroken = false;
		this.serialPort = serialPort;
		this.ldrValue = 0;
	}
	
	public void serialEvent(SerialPortEvent serialPortEvent) {
		if(!serialPortEvent.isRXCHAR()) return;
		
		try {
			char receivedChar = readChar();
			
			isFileBroken = detectFileBrokenSignal(receivedChar);
			readLdrValue(receivedChar);
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	public boolean isFileBroken(){
		return isFileBroken;
	}
	
	public Integer getLdrValue(){
		return ldrValue;
	}
	
	private void readLdrValue(char receivedChar) {
		if(isFileBroken) return;
		
		if(detectLineFeedSignal(receivedChar)) {
			ldrValue = new Integer(ldrValueAggregated);
		    ldrValueAggregated = "";
		} else {
			ldrValueAggregated = new StringBuilder(ldrValueAggregated).append(receivedChar).toString();
		}
	}
	
	private char readChar() throws SerialPortException {
		return (char)serialPort.readBytes(1)[0];
	}
	
	private boolean detectLineFeedSignal(char receivedChar) {
		return receivedChar == '\n';
	}
	
	private boolean detectFileBrokenSignal(char receivedChar){
		return Character.toLowerCase(receivedChar) == 'r';
	}
}
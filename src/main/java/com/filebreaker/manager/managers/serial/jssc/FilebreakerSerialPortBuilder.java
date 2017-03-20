package com.filebreaker.manager.managers.serial.jssc;

import jssc.SerialPort;
import jssc.SerialPortException;

class FilebreakerSerialPortBuilder {

	private static final int BAUDRATE = SerialPort.BAUDRATE_9600;
	
	private static final int DATABITS = SerialPort.DATABITS_8;
	
	private static final int STOPBITS = SerialPort.STOPBITS_1;
	
	private static final int PARITY = SerialPort.PARITY_NONE;
	
	private static final boolean RTS = false;

	private static final boolean DTR = true;
	
	private static final int FLOWCONTROL = SerialPort.FLOWCONTROL_NONE;
	
	public static SerialPort buildOpenedSerialPort(String detectedPortName) throws SerialPortException{
		SerialPort serialPort = new SerialPort(detectedPortName);
		serialPort.openPort();	
		serialPort.setParams(BAUDRATE, DATABITS, STOPBITS, PARITY, RTS, DTR);
		serialPort.setFlowControlMode(FLOWCONTROL);
		
		return serialPort;
	}
	
}
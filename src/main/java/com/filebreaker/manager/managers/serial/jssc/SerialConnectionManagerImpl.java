package com.filebreaker.manager.managers.serial.jssc;

import java.nio.charset.Charset;

import com.filebreaker.manager.managers.serial.SerialConnectionManager;

import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialConnectionManagerImpl implements SerialConnectionManager {
	
	private static final String US_ASCII_CHARSET_NAME = "US-ASCII";

	private SerialPort serialPort;

	private FilebreakerSerialPortEventListener filebreakerSerialPortEventListener;
	
	public SerialConnectionManagerImpl() throws FilebreakerNotConnectedException, SerialPortException {
		this.serialPort = new FilebreakerSerialPortDetector().getSOSpecificSerialPort();
		this.filebreakerSerialPortEventListener = new FilebreakerSerialPortEventListener(serialPort);
		
		serialPort.addEventListener(filebreakerSerialPortEventListener);
	}

	public void setSpeed(Integer speedPercentage) throws SerialPortException {
		String hexSpeed = Integer.toHexString(speedPercentage/10);
		serialPort.writeBytes(hexSpeed.getBytes(Charset.forName(US_ASCII_CHARSET_NAME)));
	}
	
	public boolean isFileBroken(){
		return filebreakerSerialPortEventListener.isFileBroken();
	}
}

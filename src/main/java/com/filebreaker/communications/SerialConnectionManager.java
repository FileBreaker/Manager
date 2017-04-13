package com.filebreaker.communications;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jssc.SerialPort;
import jssc.SerialPortException;

@Component
public class SerialConnectionManager {
	
	private static final String US_ASCII_CHARSET_NAME = "US-ASCII";

	private SerialPort serialPort;

	private FilebreakerSerialPortEventListener filebreakerSerialPortEventListener;
	
	private FilebreakerSerialPortDetector filebreakerSerialPortDetector;
	
	@Autowired
	public SerialConnectionManager(FilebreakerSerialPortDetector filebreakerSerialPortDetector) throws FilebreakerNotConnectedException, SerialPortException {
		this.filebreakerSerialPortDetector = filebreakerSerialPortDetector;
		
		this.serialPort = buildSerialPort();
	}

	private SerialPort buildSerialPort() throws FilebreakerNotConnectedException, SerialPortException {
		try {
			SerialPort serialPort = this.filebreakerSerialPortDetector.getSOSpecificSerialPort();
			this.filebreakerSerialPortEventListener = new FilebreakerSerialPortEventListener(serialPort);
			
			serialPort.addEventListener(filebreakerSerialPortEventListener);
			
			return serialPort;
		} catch(Exception e){
			return null;
		}
	}

	public void setSpeed(Integer speedPercentage) throws SerialPortException {
		String hexSpeed = Integer.toHexString(speedPercentage/10);
		serialPort.writeBytes(hexSpeed.getBytes(Charset.forName(US_ASCII_CHARSET_NAME)));
	}
	
	public boolean isFileBroken(){
		return filebreakerSerialPortEventListener.isFileBroken();
	}
}

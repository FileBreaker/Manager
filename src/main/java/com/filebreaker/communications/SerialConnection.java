package com.filebreaker.communications;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jssc.SerialPort;
import jssc.SerialPortException;

@Component
public class SerialConnection {
	
	private static final String US_ASCII_CHARSET_NAME = "US-ASCII";

	private SerialPort serialPort;

	private BufferedSerialPortEventListener bufferedSerialPortEventListener;
	
	private FilebreakerSerialPortOpener filebreakerSerialPortDetector;
	
	@Autowired
	public SerialConnection(FilebreakerSerialPortOpener filebreakerSerialPortDetector) throws FilebreakerNotConnectedException, SerialPortException {
		this.filebreakerSerialPortDetector = filebreakerSerialPortDetector;
		
		this.serialPort = buildSerialPort();
	}

	private SerialPort buildSerialPort() throws FilebreakerNotConnectedException, SerialPortException {
		try {
			SerialPort serialPort = this.filebreakerSerialPortDetector.getSOSpecificSerialPort();
			this.bufferedSerialPortEventListener = new BufferedSerialPortEventListener(serialPort);
			
			serialPort.addEventListener(bufferedSerialPortEventListener);
			
			return serialPort;
		} catch(Exception e){
			System.err.println("No conectado: " + e.getMessage());
			return null;
		}
	}

	public void setSpeed(Integer speedPercentage) {
		try {
			String hexSpeed = Integer.toHexString(speedPercentage/10);
			serialPort.writeBytes(hexSpeed.getBytes(Charset.forName(US_ASCII_CHARSET_NAME)));
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	public Integer getLdrValue() {
		return bufferedSerialPortEventListener.getLdrValue();
	}

	public BufferedSerialPortEventListener getBufferedSerialPortEventListener() {
		return bufferedSerialPortEventListener;
	}
	
}
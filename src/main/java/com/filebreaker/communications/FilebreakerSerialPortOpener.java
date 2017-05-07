package com.filebreaker.communications;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

@Component
class FilebreakerSerialPortOpener {

	private static final String MAC_OS_X_SERIAL_PORT_NAME = "/dev/tty.usbmodem1411";
	
	private static final String LINUX_SERIAL_PORT_NAME = "/dev/ttyUSB0";
	
	private static final String WINDOWS_SERIAL_PORT_NAME = "COM3";
	
	private static final Set<String> SERIAL_PORT_NAMES = new HashSet<String>(Arrays.asList(new String[] { 
			MAC_OS_X_SERIAL_PORT_NAME, 
			LINUX_SERIAL_PORT_NAME, 
			WINDOWS_SERIAL_PORT_NAME
	}));
	
	public SerialPort getSOSpecificSerialPort() throws FilebreakerNotConnectedException, SerialPortException {
		String [] detectedPortNames = SerialPortList.getPortNames();
		
		for (String detectedPortName : detectedPortNames) {
			if(SERIAL_PORT_NAMES.contains(detectedPortName)){
				return FilebreakerSerialPortFactory.buildOpenedSerialPort(detectedPortName);
			}
		}
		
		throw new FilebreakerNotConnectedException("cannot detect arduino in default serial ports");
	}
	
}
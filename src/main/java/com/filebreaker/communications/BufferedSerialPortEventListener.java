package com.filebreaker.communications;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUtils;
import org.apache.commons.collections.buffer.BoundedFifoBuffer;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

@SuppressWarnings("unchecked")
public class BufferedSerialPortEventListener implements SerialPortEventListener {

	private static final int BUFFER_SIZE = 8192;
	
	private List<FileBrokenListener> observers = new ArrayList<FileBrokenListener>();

	private LDRReader ldrReader;
	
	private SerialPort serialPort;
	
	private Buffer buffer;
	
	public BufferedSerialPortEventListener(SerialPort serialPort){
		this.serialPort = serialPort;
		this.ldrReader = new LDRReader();
		this.buffer = BufferUtils.synchronizedBuffer(new BoundedFifoBuffer(BUFFER_SIZE));
		
		Runnable readBufferRunnable = new Runnable(){
			public void run() {
				while(true){
					if(buffer.size() > 0){
						char signal = (Character)buffer.remove();
						
						if(FileBrokenDetector.isFileBroken(signal)){
							notifyAllObservers();
							buffer.clear();
						} else {
							ldrReader.append(signal);
						}
					}
				}
			}
		};
		
		new Thread(readBufferRunnable).start();
	}
	
	public void serialEvent(SerialPortEvent serialPortEvent) {
		if(!serialPortEvent.isRXCHAR()) return;
		
		try {
			for(byte b : readBuffer()){
				buffer.add((char)b);
			}
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	public Integer getLdrValue(){
		return ldrReader.getValue();
	}

	public void attach(FileBrokenListener observer) {
		observers.add(observer);
	}
	
	private byte[] readBuffer() throws SerialPortException {
		return serialPort.readBytes(serialPort.getInputBufferBytesCount());
	}
	
	private void notifyAllObservers() {
		for (FileBrokenListener observer : observers) {
			observer.update();
		}
	}
}
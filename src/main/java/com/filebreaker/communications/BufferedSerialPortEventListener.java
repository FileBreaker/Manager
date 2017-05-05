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

	private List<FileBrokenListener> observers = new ArrayList<FileBrokenListener>();
	
	private static final int BUFFER_SIZE = 8192;

	private LDRReader ldrReader;
	
	private FileBrokenDetector detector;
	
	private SerialPort serialPort;
	
	private Buffer buffer;
	
	public BufferedSerialPortEventListener(SerialPort serialPort){
		this.serialPort = serialPort;
		
		this.ldrReader = new LDRReader();
		this.detector = new FileBrokenDetector();
		
		this.buffer = BufferUtils.synchronizedBuffer(new BoundedFifoBuffer(BUFFER_SIZE));
		
		Runnable readBufferRunnable = new Runnable(){
			public void run() {
				while(true){
					if(buffer.size() > 0){
						char signal = (Character)buffer.remove();
						
						detector.detect(signal);
						
						if(detector.isNotBroken()){
							ldrReader.append(signal);
						} else {
							notifyAllObservers();
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
	
	public boolean isFileBroken(){
		return detector.isBroken();
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
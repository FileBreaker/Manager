package com.filebreaker.communications;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferUtils;
import org.apache.commons.collections.buffer.BoundedFifoBuffer;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

@SuppressWarnings("unchecked")
class FilebreakerSerialPortEventListener implements SerialPortEventListener {

	private static final int BUFFER_SIZE = 4096;

	private LDRReader ldrReader;
	
	private FileBrokenDetector detector;
	
	private SerialPort serialPort;
	
	private Buffer fifo;
	
	public FilebreakerSerialPortEventListener(SerialPort serialPort){
		this.serialPort = serialPort;
		
		this.ldrReader = new LDRReader();
		this.detector = new FileBrokenDetector();
		
		this.fifo = BufferUtils.synchronizedBuffer(new BoundedFifoBuffer(BUFFER_SIZE));
		
		Runnable readBufferRunnable = new Runnable(){
			public void run() {
				while(true){
					if(fifo.size() > 0){
						char signal = (Character)fifo.remove();
						
						detector.detect(signal);
						
						if(detector.isNotBroken()){
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
				fifo.add((char)b);
			}
		} catch (SerialPortException e) {
			e.printStackTrace();
		}
	}

	public boolean isFileBroken(){
		return detector.isBroken();
	}
	
	public Integer getLdrValue(){
		return ldrReader.getValue();
	}
	
	private byte[] readBuffer() throws SerialPortException {
		return serialPort.readBytes(serialPort.getInputBufferBytesCount());
	}
}
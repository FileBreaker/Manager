package com.filebreaker.communications;

import org.apache.commons.lang3.StringUtils;

class LDRReader {

	private static final char CARRIAGE_RETURN_CHAR = '\r';

	private static final char LINE_FEED_CHAR = '\n';
	
	private String valueBuffer;

	private Integer value;
	
	public LDRReader(){
		this.value = 0;
		this.valueBuffer = "";
	}
	
	public Integer getValue(){
		return value;
	}
	
	public void append(char digit) {
		if(detectLineFeedSignal(digit)) {
			if(StringUtils.isNotBlank(valueBuffer)){
				value = new Integer(valueBuffer);
			}
		    valueBuffer = "";
		} else {
			valueBuffer = new StringBuilder(valueBuffer).append(digit).toString();
		}
	}
	
	private boolean detectLineFeedSignal(char receivedChar) {
		return receivedChar == LINE_FEED_CHAR || receivedChar == CARRIAGE_RETURN_CHAR;
	}
	
}
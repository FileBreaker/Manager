package com.filebreaker.communications;

class FileBrokenDetector {

	private static char FILE_BROKEN_SIGNAL_CHAR = 'r';
	
	private boolean isFileBroken;
	
	public FileBrokenDetector(){
		this.isFileBroken = false;
	}
	
	public boolean detect(char signal){
		return isFileBroken = isFileBroken || isFileBrokenSignal(signal);
	}

	public boolean isBroken(){
		return isFileBroken;
	}
	
	public boolean isNotBroken(){
		return !isFileBroken;
	}
	
	private boolean isFileBrokenSignal(char signal) {
		return Character.toLowerCase(signal) == FILE_BROKEN_SIGNAL_CHAR;
	}

}
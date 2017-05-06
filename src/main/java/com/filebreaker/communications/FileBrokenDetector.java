package com.filebreaker.communications;

class FileBrokenDetector {

	private static char FILE_BROKEN_SIGNAL_CHAR = 'r';
	
	public static boolean isFileBroken(char signal){
		return Character.toLowerCase(signal) == FILE_BROKEN_SIGNAL_CHAR;
	}

}
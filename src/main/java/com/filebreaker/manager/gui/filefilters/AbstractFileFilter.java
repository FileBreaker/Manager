package com.filebreaker.manager.gui.filefilters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractFileFilter extends FileFilter {

	protected static String description;
	
	protected static String [] patterns;
	
	private static final String COMMA_DELIMITER = ",";
	
	private static final String OPEN_PARENTHESIS = "(";
	
	private static final String CLOSE_PARENTHESIS = ")";
	
	/**
	 * Constructor de la clase AbstractFileFilter
	 */
	public AbstractFileFilter(){
		initializePatterns();
	}
	
	/**
	 * Inicia los patrones para filtrar los archivos
	 */
	public abstract void initializePatterns();
	
	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File file) {
		if(file.isDirectory()){
			return true;
		}
		
		return this.matchExtensionPattern(file.getName());
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {
		return description + " " + this.obtainStringExtensions();
	}


	private boolean matchExtensionPattern(String filename){		
		for(String pattern : patterns){
			if(filename.endsWith(pattern)){
				return true;
			}
		}
		
		return false;
	}

	private String obtainStringExtensions(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(OPEN_PARENTHESIS);
		sb.append(StringUtils.join(patterns, COMMA_DELIMITER));
		sb.append(CLOSE_PARENTHESIS);
		
		return sb.toString();
	}
}
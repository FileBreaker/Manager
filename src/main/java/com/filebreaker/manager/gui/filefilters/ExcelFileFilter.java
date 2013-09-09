package com.filebreaker.manager.gui.filefilters;

public class ExcelFileFilter extends AbstractFileFilter {
	
	public ExcelFileFilter(){
		description = "Tipos de archivo soportados";
	}

	/* (non-Javadoc)
	 * @see desktopapplication1.filechooser.filefilter.AbstractFileFilter#initializePatterns()
	 */
	@Override
	public void initializePatterns() {
		String [] extensionPatterns = {".xls"};
		patterns = extensionPatterns;
	}
}

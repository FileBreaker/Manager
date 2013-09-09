package com.filebreaker.manager.gui.utils;

import javax.swing.JFileChooser;

import com.filebreaker.manager.gui.filefilters.ExcelFileFilter;

public class ExportFileChooserFactory {

	public static JFileChooser createFileChooser(){
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setFileFilter(new ExcelFileFilter());
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		return fileChooser;
	}
}

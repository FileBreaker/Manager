package com.filebreaker.view.frames;

import javax.swing.JFileChooser;

import com.filebreaker.view.filefilters.ExcelFileFilter;

public class ExportFileChooserFactory {

	public static JFileChooser createFileChooser(){
		JFileChooser fileChooser = new JFileChooser();
		
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setFileFilter(new ExcelFileFilter());
		fileChooser.setAcceptAllFileFilterUsed(false);
		
		return fileChooser;
	}
}

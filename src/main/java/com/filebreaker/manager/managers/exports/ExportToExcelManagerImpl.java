package com.filebreaker.manager.managers.exports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;

import com.filebreaker.manager.beans.Experiment;
import com.filebreaker.manager.beans.Sample;
import com.filebreaker.manager.gui.utils.TimeUtils;
import com.filebreaker.manager.managers.experiments.ExperimentsManager;
import com.filebreaker.manager.managers.experiments.ExperimentsManagerImpl;

public class ExportToExcelManagerImpl implements ExportsManager {

	private ExperimentsManager experimentsManager;
	
	public ExportToExcelManagerImpl(){
		experimentsManager = new ExperimentsManagerImpl();
	}
	
	public void exportExperiment(Integer experimentId, File file) throws IOException {
		Experiment experiment = experimentsManager.findExperiment(experimentId);
		List<Sample> samples = experimentsManager.findSamples(experimentId);
		
		writeWorkbook(file, experiment, samples);
	}


	public void exportSamples(Set<Integer> sampleIds, Integer experimentId, File file) throws IOException {
		Experiment experiment = experimentsManager.findExperiment(experimentId);
		List<Sample> samples = experimentsManager.findSamples(experimentId, sampleIds);
		
		writeWorkbook(file, experiment, samples);
	}
	
	private void writeWorkbook(File file, Experiment experiment, List<Sample> samples) throws FileNotFoundException, IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet experimentSheet = workbook.createSheet(experiment.getName());
		
		createTitlesRow(workbook, experimentSheet);
		
		int rowNumber = 1;
		
		for(Sample sample : samples){
			Row sampleRow = experimentSheet.createRow(rowNumber++);
			CellStyle cellStyle = getValueCellStyle(workbook);
			
			Cell sampleNumberCell = sampleRow.createCell(0);
			sampleNumberCell.setCellValue(sample.getId());
			sampleNumberCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell studyGroupCell = sampleRow.createCell(1);
			studyGroupCell.setCellValue(sample.getStudyGroup());
			studyGroupCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(1);
			
			Cell oscillationsNumberCell = sampleRow.createCell(2);
			oscillationsNumberCell.setCellValue(sample.getOscillations());
			oscillationsNumberCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(2);
			
			Cell sampleTimeCell = sampleRow.createCell(3);
			sampleTimeCell.setCellValue(TimeUtils.getDuration(sample.getDurationMillis()));
			sampleTimeCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(3);
		}
		
		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		fileOut.close();
	}
	
	private void createTitlesRow(HSSFWorkbook workbook, HSSFSheet experimentSheet){
		Row titleRow = experimentSheet.createRow(0);
		CellStyle cellStyle = getTitleCellStyle(workbook);
		
		Cell sampleNumberTitleCell = titleRow.createCell(0);
		sampleNumberTitleCell.setCellValue("Nº de muestra");
		sampleNumberTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell studyGroupTitleCell = titleRow.createCell(1);
		studyGroupTitleCell.setCellValue("Grupo de estudio");
		studyGroupTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(1);
		
		Cell oscillationsNumberTitleCell = titleRow.createCell(2);
		oscillationsNumberTitleCell.setCellValue("Nº de oscilaciones");
		oscillationsNumberTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(2);
		
		Cell sampleTimeTitleCell = titleRow.createCell(3);
		sampleTimeTitleCell.setCellValue("Tiempo");
		sampleTimeTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(3);
	}

	private CellStyle getTitleCellStyle(HSSFWorkbook workbook) {
		CellStyle cellStyle = workbook.createCellStyle();
		
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		return cellStyle;
	}
	
	private CellStyle getValueCellStyle(HSSFWorkbook workbook){
		CellStyle cellStyle = workbook.createCellStyle();
		
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		
		return cellStyle;
	}
}
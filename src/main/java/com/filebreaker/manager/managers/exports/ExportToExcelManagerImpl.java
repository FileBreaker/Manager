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
			
			Cell helixAngleTitleCell = sampleRow.createCell(1);
			helixAngleTitleCell.setCellValue(sample.getHelixAngle());
			helixAngleTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell distanceBetweenTurnsTitleCell = sampleRow.createCell(2);
			distanceBetweenTurnsTitleCell.setCellValue(sample.getDistanceBetweenTurns());
			distanceBetweenTurnsTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell curveAngleTitleCell = sampleRow.createCell(3);
			curveAngleTitleCell.setCellValue(sample.getCurvatureAngle());
			curveAngleTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell curveRadiusTitleCell = sampleRow.createCell(4);
			curveRadiusTitleCell.setCellValue(sample.getCurvatureRadius());
			curveRadiusTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell usesTitleCell = sampleRow.createCell(5);
			usesTitleCell.setCellValue(sample.getUses());
			usesTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell sterilizationsTitleCell = sampleRow.createCell(6);
			sterilizationsTitleCell.setCellValue(sample.getSterilizations());
			sterilizationsTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell fileTypeTitleCell = sampleRow.createCell(7);
			fileTypeTitleCell.setCellValue(sample.getFileType());
			fileTypeTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell apicalDiameterTurnsTitleCell = sampleRow.createCell(8);
			apicalDiameterTurnsTitleCell.setCellValue(sample.getApicalDiameter().doubleValue());
			apicalDiameterTurnsTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell engineAngularSpeedTitleCell = sampleRow.createCell(9);
			engineAngularSpeedTitleCell.setCellValue(sample.getEngineAngularSpeed());
			engineAngularSpeedTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell engineTorqueTitleCell = sampleRow.createCell(10);
			engineTorqueTitleCell.setCellValue(sample.getEngineTorque().doubleValue());
			engineTorqueTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell ductSpeedTitleCell = sampleRow.createCell(11);
			ductSpeedTitleCell.setCellValue(sample.getDuctSpeed());
			ductSpeedTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell movementTypeTitleCell = sampleRow.createCell(12);
			movementTypeTitleCell.setCellValue(sample.getMovementTypeId());
			movementTypeTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell studyTypeTitleCell = sampleRow.createCell(13);
			studyTypeTitleCell.setCellValue(sample.getStudyTypeId());
			studyTypeTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell studyGroupTitleCell = sampleRow.createCell(14);
			studyGroupTitleCell.setCellValue(sample.getStudyGroup());
			studyGroupTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell experimentIdTitleCell = sampleRow.createCell(15);
			experimentIdTitleCell.setCellValue(sample.getExperimentId());
			experimentIdTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell metalCompositionTitleCell = sampleRow.createCell(16);
			metalCompositionTitleCell.setCellValue(sample.getMetalCompositionId());
			metalCompositionTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell creationDateTitleCell = sampleRow.createCell(17);
			creationDateTitleCell.setCellValue(sample.getCreationDate());
			creationDateTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell modificationDateTitleCell = sampleRow.createCell(18);
			modificationDateTitleCell.setCellValue(sample.getModificationDate());
			modificationDateTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell conicityTitleCell = sampleRow.createCell(19);
			conicityTitleCell.setCellValue(sample.getConicity().doubleValue());
			conicityTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell sectionTitleCell = sampleRow.createCell(20);
			sectionTitleCell.setCellValue(sample.getSection().doubleValue());
			sectionTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell oscillationsTitleCell = sampleRow.createCell(21);
			oscillationsTitleCell.setCellValue(sample.getOscillations());
			oscillationsTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
			
			Cell durationTitleCell = sampleRow.createCell(23);
			durationTitleCell.setCellValue(sample.getDurationMillis());
			durationTitleCell.setCellStyle(cellStyle);
			experimentSheet.autoSizeColumn(0);
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
		
		Cell helixAngleTitleCell = titleRow.createCell(1);
		helixAngleTitleCell.setCellValue("Ángulo helicoidal");
		helixAngleTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell distanceBetweenTurnsTitleCell = titleRow.createCell(2);
		distanceBetweenTurnsTitleCell.setCellValue("Distancia entre espiras");
		distanceBetweenTurnsTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell curveAngleTitleCell = titleRow.createCell(3);
		curveAngleTitleCell.setCellValue("Ángulo de curvatura");
		curveAngleTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell curveRadiusTitleCell = titleRow.createCell(4);
		curveRadiusTitleCell.setCellValue("Radio de curvatura");
		curveRadiusTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell usesTitleCell = titleRow.createCell(5);
		usesTitleCell.setCellValue("Usos");
		usesTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell sterilizationsTitleCell = titleRow.createCell(6);
		sterilizationsTitleCell.setCellValue("Esterilizaciones");
		sterilizationsTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell fileTypeTitleCell = titleRow.createCell(7);
		fileTypeTitleCell.setCellValue("Tipo de lima");
		fileTypeTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell apicalDiameterTurnsTitleCell = titleRow.createCell(8);
		apicalDiameterTurnsTitleCell.setCellValue("Diametro apical");
		apicalDiameterTurnsTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell engineAngularSpeedTitleCell = titleRow.createCell(9);
		engineAngularSpeedTitleCell.setCellValue("Velocidad angular del motor");
		engineAngularSpeedTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell engineTorqueTitleCell = titleRow.createCell(10);
		engineTorqueTitleCell.setCellValue("Torque del motor");
		engineTorqueTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell ductSpeedTitleCell = titleRow.createCell(11);
		ductSpeedTitleCell.setCellValue("Velocidad del conducto");
		ductSpeedTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell movementTypeTitleCell = titleRow.createCell(12);
		movementTypeTitleCell.setCellValue("Tipo de movimiento");
		movementTypeTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell studyTypeTitleCell = titleRow.createCell(13);
		studyTypeTitleCell.setCellValue("Tipo de estudio");
		studyTypeTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell studyGroupTitleCell = titleRow.createCell(14);
		studyGroupTitleCell.setCellValue("Grupo de estudio");
		studyGroupTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell experimentIdTitleCell = titleRow.createCell(15);
		experimentIdTitleCell.setCellValue("Nº de Experimento");
		experimentIdTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell metalCompositionTitleCell = titleRow.createCell(16);
		metalCompositionTitleCell.setCellValue("Composición del metal");
		metalCompositionTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell creationDateTitleCell = titleRow.createCell(17);
		creationDateTitleCell.setCellValue("Fecha de creación");
		creationDateTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell modificationDateTitleCell = titleRow.createCell(18);
		modificationDateTitleCell.setCellValue("Fecha de modificación");
		modificationDateTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell conicityTitleCell = titleRow.createCell(19);
		conicityTitleCell.setCellValue("Conicidad");
		conicityTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell sectionTitleCell = titleRow.createCell(20);
		sectionTitleCell.setCellValue("Sección de la lima");
		sectionTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell oscillationsTitleCell = titleRow.createCell(21);
		oscillationsTitleCell.setCellValue("Nº de oscilaciones");
		oscillationsTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
		
		Cell durationTitleCell = titleRow.createCell(22);
		durationTitleCell.setCellValue("Duración");
		durationTitleCell.setCellStyle(cellStyle);
		experimentSheet.autoSizeColumn(0);
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
package com.filebreaker.experiments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.filebreaker.samples.ExportToExcelManager;

@Controller
public class ExperimentsController {

	private ExperimentsManager experimentsManager;
	
	private ExportToExcelManager exportsManager;
	
	@Autowired
	public ExperimentsController(ExperimentsManager experimentsManager, ExportToExcelManager exportsManager){
		this.experimentsManager = experimentsManager;
		this.exportsManager = exportsManager;
	}
	
	public List<Experiment> getExperiments(){
		return experimentsManager.findExperiments();
	}
	
	public Experiment getExperiment(Integer experimentId) {
		return experimentsManager.findExperiment(experimentId);
	}
	
	public void createExperiment(String experimentName, Integer samplesNumber) {
		experimentsManager.createExperiment(experimentName, samplesNumber);
	}
	
	public void exportExperiment(Integer experimentId, File file) throws FileNotFoundException, IOException {
		exportsManager.exportExperiment(experimentId, file);
	}

	public void deleteExperiment(Integer selectedExperimentId) {
		experimentsManager.deleteExperiment(selectedExperimentId);
	}
	
	public void editExperiment(Experiment experiment){
		experimentsManager.updateExperiment(experiment);
	}
}
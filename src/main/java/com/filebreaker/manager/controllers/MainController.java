package com.filebreaker.manager.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.filebreaker.manager.beans.Experiment;
import com.filebreaker.manager.beans.Sample;
import com.filebreaker.manager.managers.experiments.ExperimentsManager;
import com.filebreaker.manager.managers.experiments.ExperimentsManagerImpl;
import com.filebreaker.manager.managers.exports.ExportToExcelManagerImpl;
import com.filebreaker.manager.managers.exports.ExportsManager;

public class MainController {

	private ExperimentsManager experimentsManager;
	
	private ExportsManager exportsManager;
	
	public MainController(){
		experimentsManager = new ExperimentsManagerImpl();
		exportsManager = new ExportToExcelManagerImpl();
	}
	
	// Experiments
	public List<Experiment> getExperiments(){
		List<Experiment> experiments = experimentsManager.findExperiments();
		return experiments;
	}
	
	public Experiment getExperiment(Integer experimentId) {
		Experiment experiment = experimentsManager.findExperiment(experimentId);
		return experiment;
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
	
	// Samples
	public List<Sample> getSamples(Integer experimentId){
		List<Sample> samples = experimentsManager.findSamples(experimentId);
		return samples;
	}
	
	public Sample getSample(Integer experimentId, Integer sampleId){
		Sample sample = experimentsManager.findSample(experimentId, sampleId);
		return sample;
	}
	
	public void createSample(Sample sample) {
		experimentsManager.createSample(sample);
	}

	public void updateSample(Sample sample) {
		experimentsManager.updateSample(sample);
	}
	
	public void deleteSample(Integer experimentId, Integer sampleId) {
		experimentsManager.deleteSample(experimentId, sampleId);
	}
	
	public void deleteSamples(Integer experimentId, Set<Integer> samplesId) {
		experimentsManager.deleteSamples(experimentId, samplesId);
	}

	public void exportSamples(Set<Integer> selectedSamplesId, Integer experimentId, File saveFile) throws IOException {
		exportsManager.exportSamples(selectedSamplesId, experimentId, saveFile);
	}
	
	public void saveState(Integer experimentId, Integer sampleId, long duration, int oscillations) {
		experimentsManager.saveState(experimentId, sampleId, duration, oscillations);	
	}
	
	public void duplicateSamples(Integer sampleId, Integer experimentId, Integer samplesNumber) {
		experimentsManager.duplicateSample(sampleId, experimentId, samplesNumber);
	}

	public boolean isFileBroken() {
		return false;
	}

	public int getOscillations() {
		return 0;
	}

}
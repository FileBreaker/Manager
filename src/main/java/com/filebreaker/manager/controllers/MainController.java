package com.filebreaker.manager.controllers;

import java.util.List;

import com.filebreaker.manager.beans.Experiment;
import com.filebreaker.manager.beans.Sample;
import com.filebreaker.manager.managers.ExperimentsManager;
import com.filebreaker.manager.managers.ExperimentsManagerImpl;

public class MainController {

	private ExperimentsManager experimentsManager;
	
	public MainController(){
		experimentsManager = new ExperimentsManagerImpl();
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
	
	public void exportExperiment() {
		
	}

	public void deleteExperiment(Integer selectedExperimentId) {
		experimentsManager.deleteExperiment(selectedExperimentId);
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
}
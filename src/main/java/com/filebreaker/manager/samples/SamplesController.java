package com.filebreaker.manager.samples;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.filebreaker.manager.experiments.ExperimentsManager;

@Controller
public class SamplesController {

	private ExperimentsManager experimentsManager;
	
	private ExportToExcelManager exportToExcelManager;
	
	@Autowired
	public SamplesController(ExperimentsManager experimentsManager, ExportToExcelManager exportToExcelManager){
		this.experimentsManager = experimentsManager;
		this.exportToExcelManager = exportToExcelManager;
	}
	
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
		exportToExcelManager.exportSamples(selectedSamplesId, experimentId, saveFile);
	}
	
	public void saveState(Integer experimentId, Integer sampleId, long duration, int oscillations) {
		experimentsManager.saveState(experimentId, sampleId, duration, oscillations);	
	}
	
	public void duplicateSamples(Integer sampleId, Integer experimentId, Integer samplesNumber) {
		experimentsManager.duplicateSample(sampleId, experimentId, samplesNumber);
	}

}

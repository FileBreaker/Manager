package com.filebreaker.experiments;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.filebreaker.samples.Sample;
import com.filebreaker.samples.SamplesDAO;

@Component
public class ExperimentsManager {

	@Autowired
	private ExperimentsDAO experimentsDAO;
	
	@Autowired
	private SamplesDAO samplesDAO;
	
	public List<Experiment> findExperiments() {
		return experimentsDAO.findExperiments();
	}
	
	public Experiment findExperiment(Integer experimentId) {
		return experimentsDAO.findExperiment(experimentId);
	}

	public void createExperiment(String experimentName, Integer sampleNumber) {
		Experiment experiment = new Experiment();
		
		experiment.setName(experimentName);
		experiment.setCreationDate(new Date());
		
		Integer experimentId = experimentsDAO.createExperiment(experiment);
		
		for(int i = 0; i < sampleNumber; i++){
			Sample sample = new Sample();
			sample.setExperimentId(experimentId);
			sample.setCreationDate(new Date());
			
			samplesDAO.saveSample(sample);
		}
	}
	
	public void deleteExperiment(Integer experimentId) {
		samplesDAO.deleteSamples(experimentId);
		experimentsDAO.deleteExperiment(experimentId);
	}
	
	public void updateExperiment(Experiment experiment) {
		experiment.setModificationDate(new Date());
		experimentsDAO.updateExperiment(experiment);
	}

	public List<Sample> findSamples(Integer experimentId) {
		return samplesDAO.findSamples(experimentId);
	}

	public List<Sample> findSamples(Integer experimentId, Set<Integer> sampleIds) {
		return samplesDAO.findSamples(experimentId, sampleIds);
	}
	
	public Sample findSample(Integer experimentId, Integer sampleId) {
		return samplesDAO.findSample(experimentId, sampleId);
	}

	public void createSample(Sample sample) {
		sample.setCreationDate(new Date());
		samplesDAO.saveSample(sample);
	}

	public void updateSample(Sample sample) {
		Sample oldSample = findSample(sample.getExperimentId(), sample.getId());
		sample.setDurationMillis(oldSample.getDurationMillis());
		sample.setOscillations(oldSample.getOscillations());
		sample.setModificationDate(new Date());
		
		samplesDAO.updateSample(sample);
	}

	public void deleteSample(Integer experimentId, Integer sampleId) {
		samplesDAO.deleteSample(experimentId, sampleId);
	}

	public void deleteSamples(Integer experimentId, Set<Integer> samplesId) {
		samplesDAO.deleteSamples(experimentId, samplesId);
	}

	public void duplicateSample(Integer sampleId, Integer experimentId, Integer samplesNumber) {
		Sample sample = samplesDAO.findSample(experimentId, sampleId);
		sample.setId(null);
		
		for(int i = 0; i < samplesNumber; i++){
			samplesDAO.saveSample(sample);
		}
	}
	
	public void saveState(Integer experimentId, Integer sampleId, long duration, int oscillations) {
		Sample sample = samplesDAO.findSample(experimentId, sampleId);
		sample.setModificationDate(new Date());
		sample.setOscillations(oscillations);
		sample.setDurationMillis(duration);
		samplesDAO.updateSample(sample);
		
		Experiment experiment = experimentsDAO.findExperiment(experimentId);
		experiment.setModificationDate(new Date());
		experimentsDAO.updateExperiment(experiment);
	}
}
package com.filebreaker.manager.managers.experiments;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.filebreaker.manager.beans.Experiment;
import com.filebreaker.manager.beans.Sample;
import com.filebreaker.manager.persistence.ExperimentsDAO;
import com.filebreaker.manager.persistence.SampleDAO;
import com.filebreaker.manager.persistence.h2.ExperimentsDAOImpl;
import com.filebreaker.manager.persistence.h2.SampleDAOImpl;

public class ExperimentsManagerImpl implements ExperimentsManager {

	private ExperimentsDAO experimentsDAO;
	
	private SampleDAO sampleDAO;
	
	public ExperimentsManagerImpl(){
		this.experimentsDAO = new ExperimentsDAOImpl();
		this.sampleDAO = new SampleDAOImpl();
	}
	
	public List<Experiment> findExperiments() {
		List<Experiment> experiments = experimentsDAO.findExperiments();
		return experiments;
	}
	
	public Experiment findExperiment(Integer experimentId) {
		Experiment experiment = experimentsDAO.findExperiment(experimentId);
		return experiment;
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
			
			sampleDAO.saveSample(sample);
		}
	}
	
	public void deleteExperiment(Integer experimentId) {
		sampleDAO.deleteSamples(experimentId);
		experimentsDAO.deleteExperiment(experimentId);
	}
	
	public void updateExperiment(Experiment experiment) {
		experiment.setModificationDate(new Date());
		experimentsDAO.updateExperiment(experiment);
	}

	public List<Sample> findSamples(Integer experimentId) {
		List<Sample> samples = sampleDAO.findSamples(experimentId);
		return samples;
	}

	public List<Sample> findSamples(Integer experimentId, Set<Integer> sampleIds) {
		List<Sample> samples = sampleDAO.findSamples(experimentId, sampleIds);
		return samples;
	}
	
	public Sample findSample(Integer experimentId, Integer sampleId) {
		Sample sample = sampleDAO.findSample(experimentId, sampleId);
		return sample;
	}

	public void createSample(Sample sample) {
		sample.setCreationDate(new Date());
		sampleDAO.saveSample(sample);
	}

	public void updateSample(Sample sample) {
		sample.setModificationDate(new Date());
		sampleDAO.updateSample(sample);
	}

	public void deleteSample(Integer experimentId, Integer sampleId) {
		sampleDAO.deleteSample(experimentId, sampleId);
	}

	public void deleteSamples(Integer experimentId, Set<Integer> samplesId) {
		sampleDAO.deleteSamples(experimentId, samplesId);
	}

	public void duplicateSample(Integer sampleId, Integer experimentId, Integer samplesNumber) {
		Sample sample = sampleDAO.findSample(experimentId, sampleId);
		sample.setId(null);
		
		for(int i = 0; i < samplesNumber; i++){
			sampleDAO.saveSample(sample);
		}
	}
	
	public void saveState(Integer experimentId, Integer sampleId, long duration, int oscillations) {
		Sample sample = sampleDAO.findSample(experimentId, sampleId);
		sample.setModificationDate(new Date());
		sample.setOscillations(oscillations);
		sample.setDurationMillis(duration);
		sampleDAO.updateSample(sample);
		
		Experiment experiment = experimentsDAO.findExperiment(experimentId);
		experiment.setModificationDate(new Date());
		experimentsDAO.updateExperiment(experiment);
	}
}
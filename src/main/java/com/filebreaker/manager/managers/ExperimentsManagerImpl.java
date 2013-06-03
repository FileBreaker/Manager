package com.filebreaker.manager.managers;

import java.util.Date;
import java.util.List;

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

	public void createExperiment(String experimentName, int sampleNumber) {
		Experiment experiment = new Experiment();
		
		experiment.setName(experimentName);
		experiment.setCreationDate(new Date());
		
		Integer experimentId = experimentsDAO.createExperiment(experiment);
		
		for(int i = 0; i < sampleNumber; i++){
			Sample sample = new Sample();
			sample.setExperimentId(experimentId);
			sample.setCreationDate(new Date());
			
			sampleDAO.createSample(sample);
		}
	}
}

package com.filebreaker.manager.controllers;

import java.util.List;

import com.filebreaker.manager.beans.Experiment;
import com.filebreaker.manager.managers.ExperimentsManager;
import com.filebreaker.manager.managers.ExperimentsManagerImpl;

public class MainController {

	private ExperimentsManager experimentsManager;
	
	public MainController(){
		experimentsManager = new ExperimentsManagerImpl();
	}
	
	public Object [][] getExperiments(){
		List<Experiment> experiments = experimentsManager.findExperiments();
		Object [][] result = null;
		
		if(experiments != null && !experiments.isEmpty()){
			result = new Object[experiments.size()][3];
		
			for(int i = 0; i < experiments.size(); i++){
				Experiment experiment = experiments.get(i);
			
				result[i][0] = experiment.getName();
				result[i][1] = experiment.getCreationDate();
				result[i][2] = experiment.getModificationDate();
			}
		}
		
		return result;
	}
	
	public void createExperiment(String experimentName, Integer samplesNumber) {
		experimentsManager.createExperiment(experimentName, samplesNumber);
	}
	
	public void exportExperiment() {
		
	}

	public void deleteExperiment() {
		
	}

}

package com.filebreaker.manager.managers;

import java.util.List;

import com.filebreaker.manager.beans.Experiment;

public interface ExperimentsManager {

	void createExperiment(String experimentName, int sampleNumber);
	
	List<Experiment> findExperiments();
}

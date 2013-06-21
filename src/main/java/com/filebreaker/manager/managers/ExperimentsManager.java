package com.filebreaker.manager.managers;

import java.util.List;

import com.filebreaker.manager.beans.Experiment;
import com.filebreaker.manager.beans.Sample;

public interface ExperimentsManager {

	void createExperiment(String experimentName, Integer sampleNumber);
	
	List<Experiment> findExperiments();
	
	Experiment findExperiment(Integer experimentId);
	
	void deleteExperiment(Integer experimentId);
	
	void createSample(Sample sample);
	
	List<Sample> findSamples(Integer experimentId);

	Sample findSample(Integer experimentId, Integer sampleId);

	void updateSample(Sample sample);

	void deleteSample(Integer experimentId, Integer sampleId);
}

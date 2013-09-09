package com.filebreaker.manager.managers.experiments;

import java.util.List;
import java.util.Set;

import com.filebreaker.manager.beans.Experiment;
import com.filebreaker.manager.beans.Sample;

public interface ExperimentsManager {

	void createExperiment(String experimentName, Integer sampleNumber);
	
	List<Experiment> findExperiments();
	
	Experiment findExperiment(Integer experimentId);
	
	void deleteExperiment(Integer experimentId);
	
	void updateExperiment(Experiment experiment);
	
	void createSample(Sample sample);
	
	List<Sample> findSamples(Integer experimentId);
	
	List<Sample> findSamples(Integer experimentId, Set<Integer> sampleIds);

	Sample findSample(Integer experimentId, Integer sampleId);

	void updateSample(Sample sample);

	void deleteSample(Integer experimentId, Integer sampleId);

	void deleteSamples(Integer experimentId, Set<Integer> samplesId);

	void saveState(Integer experimentId, Integer sampleId, long duration, int oscillations);

}
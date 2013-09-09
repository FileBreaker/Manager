package com.filebreaker.manager.persistence;

import java.util.List;
import java.util.Set;

import com.filebreaker.manager.beans.Sample;

public interface SampleDAO {

	void saveSample(Sample sample);

	List<Sample> findSamples(Integer experimentId);

	List<Sample> findSamples(Integer experimentId, Set<Integer> sampleIds);
	
	void deleteSamples(Integer experimentId);

	Sample findSample(Integer experimentId, Integer sampleId);

	void updateSample(Sample sample);

	void deleteSample(Integer experimentId, Integer sampleId);

	void deleteSamples(Integer experimentId, Set<Integer> sampleIds);

}

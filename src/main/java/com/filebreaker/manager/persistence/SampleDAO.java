package com.filebreaker.manager.persistence;

import java.util.List;

import com.filebreaker.manager.beans.Sample;

public interface SampleDAO {

	void saveSample(Sample sample);

	List<Sample> findSamples(Integer experimentId);

	void deleteSamples(Integer experimentId);

	Sample findSample(Integer experimentId, Integer sampleId);

	void updateSample(Sample sample);

	void deleteSample(Integer experimentId, Integer sampleId);
}

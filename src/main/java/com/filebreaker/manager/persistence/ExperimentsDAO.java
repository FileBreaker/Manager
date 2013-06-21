package com.filebreaker.manager.persistence;

import java.util.List;

import com.filebreaker.manager.beans.Experiment;

public interface ExperimentsDAO {

	List<Experiment> findExperiments();

	Experiment findExperiment(Integer experimentId);
	
	Integer createExperiment(Experiment experiment);

	void deleteExperiment(Integer experimentId);
	
}

package com.filebreaker.manager.persistence;

import java.util.List;

import com.filebreaker.manager.beans.Experiment;

public interface ExperimentsDAO {

	List<Experiment> findExperiments();

	Integer createExperiment(Experiment experiment);
	
}

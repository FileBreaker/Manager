package com.filebreaker.manager.managers.exports;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public interface ExportsManager {
	
	void exportExperiment(Integer experimentId, File file) throws FileNotFoundException, IOException;
	
	void exportSamples(Set<Integer> sampleId, Integer experimentId, File file) throws IOException;

}

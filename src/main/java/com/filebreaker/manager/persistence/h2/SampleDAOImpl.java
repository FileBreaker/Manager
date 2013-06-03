package com.filebreaker.manager.persistence.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import com.filebreaker.manager.beans.Sample;
import com.filebreaker.manager.persistence.SampleDAO;

public class SampleDAOImpl extends H2DAO implements SampleDAO {

	public void createSample(Sample sample) {
		try {
			Connection con = getConnection();
			
			//prepared statement
			PreparedStatement prep = con.prepareStatement("INSERT INTO SAMPLES (EXPERIMENT_ID, CURVATURE_ANGLE, CURVATURE_RADIUS, USES, FILE_TYPE, APICAL_DIAMETER, ENGINE_ANGULAR_SPEED, ENGINE_TORQUE, DUCT_SPEED, MOVEMENT_TYPE_ID, STUDY_TYPE_ID, CREATION_DATE, MODIFICATION_DATE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
	
			prep.setInt(1, sample.getExperimentId());
			prep.setInt(2, sample.getCurvatureAngle());
			prep.setInt(3, sample.getUses());
			prep.setString(4, sample.getFileType());
			prep.setInt(5, sample.getApicalDiameter());
			prep.setInt(6, sample.getEngineAngularSpeed());
			prep.setInt(7, sample.getEngineTorque());
			prep.setInt(8, sample.getDuctSpeed());
			prep.setInt(9, sample.getMovementTypeId());
			prep.setInt(10, sample.getStudyTypeId());
			prep.setTimestamp(11, new Timestamp(sample.getCreationDate().getTime()));
			prep.setTimestamp(12, new Timestamp(sample.getModificationDate().getTime()));
			
			//batch insert
			prep.addBatch();
			
			con.setAutoCommit(false);
			prep.executeBatch();
			con.setAutoCommit(true);
		} catch (Exception e){
			// TODO: LOG
			e.printStackTrace();
		}
	}
}

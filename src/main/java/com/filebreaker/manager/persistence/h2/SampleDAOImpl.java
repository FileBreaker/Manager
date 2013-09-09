package com.filebreaker.manager.persistence.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.filebreaker.manager.beans.Sample;
import com.filebreaker.manager.persistence.SampleDAO;

public class SampleDAOImpl extends H2DAO implements SampleDAO {

	public void saveSample(Sample sample) {
		try {
			Connection con = getConnection();
			
			//prepared statement
			PreparedStatement prep = con.prepareStatement("INSERT INTO SAMPLES (EXPERIMENT_ID, CURVATURE_ANGLE, CURVATURE_RADIUS, USES, FILE_TYPE, APICAL_DIAMETER, ENGINE_ANGULAR_SPEED, ENGINE_TORQUE, DUCT_SPEED, MOVEMENT_TYPE_ID, STUDY_TYPE_ID, STUDY_GROUP, METAL_COMPOSITION_ID, CREATION_DATE, OSCILLATIONS, DURATION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	
			prep.setInt(1, sample.getExperimentId());		
			setNullSafeValue(prep, 2, sample.getCurvatureAngle(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 3, sample.getCurvatureRadius(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 4, sample.getUses(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 5, sample.getFileType(), java.sql.Types.VARCHAR);
			setNullSafeValue(prep, 6, sample.getApicalDiameter(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 7, sample.getEngineAngularSpeed(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 8, sample.getEngineTorque(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 9, sample.getDuctSpeed(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 10, sample.getMovementTypeId(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 11, sample.getStudyTypeId(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 12, sample.getStudyGroup(), java.sql.Types.VARCHAR);
			setNullSafeValue(prep, 13, sample.getMetalCompositionId(), java.sql.Types.INTEGER);
			prep.setTimestamp(14, new Timestamp(sample.getCreationDate().getTime()));
			prep.setInt(15, sample.getOscillations());
			prep.setLong(16, sample.getDurationMillis());
			
			//batch insert
			prep.addBatch();
		
			prep.executeBatch();
		} catch (Exception e){
			// TODO: LOG
			e.printStackTrace();
		}
	}

	public List<Sample> findSamples(Integer experimentId) {
		List<Sample> result = new ArrayList<Sample>();
		
		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * FROM SAMPLES WHERE EXPERIMENT_ID = " + experimentId);
			
			while(rs.next()){
				Sample sample = new Sample();
				
				sample.setApicalDiameter(rs.getInt("APICAL_DIAMETER"));
				sample.setCreationDate(rs.getDate("CREATION_DATE"));
				sample.setCurvatureAngle(rs.getInt("CURVATURE_ANGLE"));
				sample.setCurvatureRadius(rs.getInt("CURVATURE_RADIUS"));
				sample.setDuctSpeed(rs.getInt("DUCT_SPEED"));
				sample.setEngineAngularSpeed(rs.getInt("ENGINE_ANGULAR_SPEED"));
				sample.setEngineTorque(rs.getInt("ENGINE_TORQUE"));
				sample.setExperimentId(rs.getInt("EXPERIMENT_ID"));
				sample.setFileType(rs.getString("FILE_TYPE"));
				sample.setId(rs.getInt("ID"));
				sample.setModificationDate(rs.getDate("MODIFICATION_DATE"));
				sample.setMovementTypeId(rs.getInt("MOVEMENT_TYPE_ID"));
				sample.setStudyTypeId(rs.getInt("STUDY_TYPE_ID"));
				sample.setStudyGroup(rs.getString("STUDY_GROUP"));
				sample.setUses(rs.getInt("USES"));
				sample.setMetalCompositionId(rs.getInt("METAL_COMPOSITION_ID"));
				sample.setOscillations(rs.getInt("OSCILLATIONS"));
				sample.setDurationMillis(rs.getLong("DURATION"));
				
				result.add(sample);
			}
		} catch(Exception e){
			// TODO: LOG
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<Sample> findSamples(Integer experimentId, Set<Integer> sampleIds) {
		List<Sample> result = new ArrayList<Sample>();
		
		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM SAMPLES WHERE EXPERIMENT_ID = " + experimentId + " AND ID IN (" + StringUtils.join(sampleIds, ",") + ")");
			
			while(rs.next()){
				Sample sample = new Sample();
				
				sample.setApicalDiameter(rs.getInt("APICAL_DIAMETER"));
				sample.setCreationDate(rs.getDate("CREATION_DATE"));
				sample.setCurvatureAngle(rs.getInt("CURVATURE_ANGLE"));
				sample.setCurvatureRadius(rs.getInt("CURVATURE_RADIUS"));
				sample.setDuctSpeed(rs.getInt("DUCT_SPEED"));
				sample.setEngineAngularSpeed(rs.getInt("ENGINE_ANGULAR_SPEED"));
				sample.setEngineTorque(rs.getInt("ENGINE_TORQUE"));
				sample.setExperimentId(rs.getInt("EXPERIMENT_ID"));
				sample.setFileType(rs.getString("FILE_TYPE"));
				sample.setId(rs.getInt("ID"));
				sample.setModificationDate(rs.getDate("MODIFICATION_DATE"));
				sample.setMovementTypeId(rs.getInt("MOVEMENT_TYPE_ID"));
				sample.setStudyTypeId(rs.getInt("STUDY_TYPE_ID"));
				sample.setStudyGroup(rs.getString("STUDY_GROUP"));
				sample.setUses(rs.getInt("USES"));
				sample.setMetalCompositionId(rs.getInt("METAL_COMPOSITION_ID"));
				sample.setOscillations(rs.getInt("OSCILLATIONS"));
				sample.setDurationMillis(rs.getLong("DURATION"));
				
				result.add(sample);
			}
		} catch(Exception e){
			// TODO: LOG
			e.printStackTrace();
		}
		
		return result;
	}

	public void deleteSamples(Integer experimentId) {
		try {
			Connection con = getConnection();
			
			//prepared statement
			PreparedStatement stat = con.prepareStatement("DELETE FROM SAMPLES WHERE EXPERIMENT_ID=?");
			stat.setInt(1, experimentId);
			
			stat.addBatch();
			stat.executeBatch();
			con.commit();
		} catch(Exception e) {
			// TODO: LOG
			e.printStackTrace();
		}
	}

	public Sample findSample(Integer experimentId, Integer sampleId) {
		Sample result = null;
		
		try {
			Connection con = getConnection();
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * FROM SAMPLES WHERE EXPERIMENT_ID = " + experimentId + " AND ID = " + sampleId);
			
			if(rs.first()){
				result = new Sample();
				
				result.setApicalDiameter(rs.getInt("APICAL_DIAMETER"));
				result.setCreationDate(rs.getDate("CREATION_DATE"));
				result.setCurvatureAngle(rs.getInt("CURVATURE_ANGLE"));
				result.setCurvatureRadius(rs.getInt("CURVATURE_RADIUS"));
				result.setDuctSpeed(rs.getInt("DUCT_SPEED"));
				result.setEngineAngularSpeed(rs.getInt("ENGINE_ANGULAR_SPEED"));
				result.setEngineTorque(rs.getInt("ENGINE_TORQUE"));
				result.setExperimentId(rs.getInt("EXPERIMENT_ID"));
				result.setFileType(rs.getString("FILE_TYPE"));
				result.setId(rs.getInt("ID"));
				result.setModificationDate(rs.getDate("MODIFICATION_DATE"));
				result.setMovementTypeId(rs.getInt("MOVEMENT_TYPE_ID"));
				result.setStudyTypeId(rs.getInt("STUDY_TYPE_ID"));
				result.setStudyGroup(rs.getString("STUDY_GROUP"));
				result.setUses(rs.getInt("USES"));
				result.setMetalCompositionId(rs.getInt("METAL_COMPOSITION_ID"));
				result.setOscillations(rs.getInt("OSCILLATIONS"));
				result.setDurationMillis(rs.getLong("DURATION"));
			}
		} catch(Exception e){
			// TODO: LOG
			e.printStackTrace();
		}
		
		return result;
	}

	public void updateSample(Sample sample) {
		try {
			Connection con = getConnection();
			
			//prepared statement
			PreparedStatement prep = con.prepareStatement("UPDATE SAMPLES SET CURVATURE_ANGLE=?, CURVATURE_RADIUS=?, USES=?, FILE_TYPE=?, APICAL_DIAMETER=?, ENGINE_ANGULAR_SPEED=?, ENGINE_TORQUE=?, DUCT_SPEED=?, MOVEMENT_TYPE_ID=?, STUDY_TYPE_ID=?, STUDY_GROUP=?, METAL_COMPOSITION_ID=?,MODIFICATION_DATE=?, OSCILLATIONS=?, DURATION=? WHERE ID=? AND EXPERIMENT_ID=?");
	
			prep.setInt(1, sample.getCurvatureAngle());
			prep.setInt(2, sample.getCurvatureRadius());
			prep.setInt(3, sample.getUses());
			prep.setString(4, sample.getFileType());
			prep.setInt(5, sample.getApicalDiameter());
			prep.setInt(6, sample.getEngineAngularSpeed());
			prep.setInt(7, sample.getEngineTorque());
			prep.setInt(8, sample.getDuctSpeed());
			prep.setInt(9, sample.getMovementTypeId());
			prep.setInt(10, sample.getStudyTypeId());
			prep.setString(11, sample.getStudyGroup());
			prep.setInt(12, sample.getMetalCompositionId());
			prep.setTimestamp(13, new Timestamp(sample.getModificationDate().getTime()));
			prep.setInt(14, sample.getOscillations());
			prep.setLong(15, sample.getDurationMillis());
			prep.setInt(16, sample.getId());
			prep.setInt(17, sample.getExperimentId());
			
			//batch insert
			prep.addBatch();
		
			prep.executeBatch();
		} catch (Exception e){
			// TODO: LOG
			e.printStackTrace();
		}
	}

	public void deleteSample(Integer experimentId, Integer sampleId) {
		try {
			Connection con = getConnection();
			
			//prepared statement
			PreparedStatement stat = con.prepareStatement("DELETE FROM SAMPLES WHERE EXPERIMENT_ID=? AND ID=?");
			stat.setInt(1, experimentId);
			stat.setInt(2, sampleId);
			
			stat.addBatch();
			stat.executeBatch();
			con.commit();
		} catch(Exception e) {
			// TODO: LOG
			e.printStackTrace();
		}
	}

	public void deleteSamples(Integer experimentId, Set<Integer> sampleIds) {
		try {
			Connection con = getConnection();
			
			//prepared statement
			PreparedStatement stat = con.prepareStatement("DELETE FROM SAMPLES WHERE EXPERIMENT_ID= " + experimentId + " AND ID IN (" + StringUtils.join(sampleIds, ",") + ")");
			
			stat.addBatch();
			stat.executeBatch();
			con.commit();
		} catch(Exception e) {
			// TODO: LOG
			e.printStackTrace();
		}
	}
}

package com.filebreaker.samples;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.filebreaker.persistence.H2DAO;

@Repository
public class SamplesDAO {

	private H2DAO h2dao;
	
	@Autowired
	public SamplesDAO(H2DAO h2dao){
		this.h2dao = h2dao;
	}
	
	public void saveSample(Sample sample) {
		try {
			Connection con = h2dao.getConnection();
			
			//prepared statement
			PreparedStatement prep = con.prepareStatement("INSERT INTO SAMPLES (EXPERIMENT_ID, " +
																				"HELIX_ANGLE, " +
																				"DISTANCE_BETWEEN_TURNS, " +
																				"CURVATURE_ANGLE, " +
																				"CURVATURE_RADIUS, " +
																				"USES, " +
																				"STERILIZATIONS, " +
																				"FILE_TYPE, " +
																				"APICAL_DIAMETER, " +
																				"CONICITY, " +
																				"SECTION, " +
																				"ENGINE_ANGULAR_SPEED, " +
																				"ENGINE_TORQUE, " +
																				"DUCT_SPEED, " +
																				"MOVEMENT_TYPE_ID, " +
																				"STUDY_TYPE_ID, " +
																				"STUDY_GROUP, " +
																				"METAL_COMPOSITION_ID, " +
																				"CREATION_DATE, " +
																				"OSCILLATIONS, " +
																				"DURATION) " +
																				"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
	
			prep.setInt(1, sample.getExperimentId());
			setNullSafeValue(prep, 2, sample.getHelixAngle(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 3, sample.getDistanceBetweenTurns(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 4, sample.getCurvatureAngle(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 5, sample.getCurvatureRadius(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 6, sample.getUses(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 7, sample.getSterilizations(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 8, sample.getFileType(), java.sql.Types.VARCHAR);
			setNullSafeValue(prep, 9, sample.getApicalDiameter(), java.sql.Types.DECIMAL);
			setNullSafeValue(prep, 10, sample.getConicity(), java.sql.Types.DECIMAL);
			setNullSafeValue(prep, 11, sample.getSection(), java.sql.Types.DECIMAL);
			setNullSafeValue(prep, 12, sample.getEngineAngularSpeed(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 13, sample.getEngineTorque(), java.sql.Types.DECIMAL);
			setNullSafeValue(prep, 14, sample.getDuctSpeed(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 15, sample.getMovementTypeId(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 16, sample.getStudyTypeId(), java.sql.Types.INTEGER);
			setNullSafeValue(prep, 17, sample.getStudyGroup(), java.sql.Types.VARCHAR);
			setNullSafeValue(prep, 18, sample.getMetalCompositionId(), java.sql.Types.INTEGER);
			prep.setTimestamp(19, new Timestamp(sample.getCreationDate().getTime()));
			prep.setInt(20, sample.getOscillations());
			prep.setLong(21, sample.getDurationMillis());
			
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
			Connection con = h2dao.getConnection();
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * FROM SAMPLES WHERE EXPERIMENT_ID = " + experimentId);
			
			while(rs.next()){
				Sample sample = new Sample();
				getSampleFromResultSet(rs, sample);
				
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
			Connection con = h2dao.getConnection();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM SAMPLES WHERE EXPERIMENT_ID = " + experimentId + " AND ID IN (" + StringUtils.join(sampleIds, ",") + ")");
			
			while(rs.next()){
				Sample sample = new Sample();
				getSampleFromResultSet(rs, sample);
				
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
			Connection con = h2dao.getConnection();
			
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
		Sample sample = null;
		
		try {
			Connection con = h2dao.getConnection();
			Statement statement = con.createStatement();
			
			ResultSet rs = statement.executeQuery("SELECT * FROM SAMPLES WHERE EXPERIMENT_ID = " + experimentId + " AND ID = " + sampleId);
			
			if(rs.first()){
				sample = new Sample();
				getSampleFromResultSet(rs, sample);
			}
		} catch(Exception e){
			// TODO: LOG
			e.printStackTrace();
		}
		
		return sample;
	}

	public void updateSample(Sample sample) {
		try {
			Connection con = h2dao.getConnection();
			
			//prepared statement
			PreparedStatement prep = con.prepareStatement("UPDATE SAMPLES SET HELIX_ANGLE=?, " +
					"DISTANCE_BETWEEN_TURNS=?, " +
					"CURVATURE_ANGLE=?, " +
					"CURVATURE_RADIUS=?, " +
					"USES=?, " +
					"STERILIZATIONS=?, " +
					"FILE_TYPE=?, " +
					"APICAL_DIAMETER=?, " +
					"CONICITY=?, " +
					"SECTION=?, " + 
					"ENGINE_ANGULAR_SPEED=?, " +
					"ENGINE_TORQUE=?, " +
					"DUCT_SPEED=?, " +
					"MOVEMENT_TYPE_ID=?, " +
					"STUDY_TYPE_ID=?, " +
					"STUDY_GROUP=?, " +
					"METAL_COMPOSITION_ID=?, " +
					"MODIFICATION_DATE=?, " +
					"OSCILLATIONS=?, " +
					"DURATION=? " +
					"WHERE ID=? AND EXPERIMENT_ID=?");
	
			prep.setInt(1, sample.getHelixAngle());
			prep.setInt(2, sample.getDistanceBetweenTurns());
			prep.setInt(3, sample.getCurvatureAngle());
			prep.setInt(4, sample.getCurvatureRadius());
			prep.setInt(5, sample.getUses());
			prep.setInt(6, sample.getSterilizations());
			prep.setString(7, sample.getFileType());
			prep.setBigDecimal(8, sample.getApicalDiameter());
			prep.setBigDecimal(9, sample.getConicity());
			prep.setBigDecimal(10, sample.getSection());
			prep.setInt(11, sample.getEngineAngularSpeed());
			prep.setBigDecimal(12, sample.getEngineTorque());
			prep.setInt(13, sample.getDuctSpeed());
			prep.setInt(14, sample.getMovementTypeId());
			prep.setInt(15, sample.getStudyTypeId());
			prep.setString(16, sample.getStudyGroup());
			prep.setInt(17, sample.getMetalCompositionId());
			prep.setTimestamp(18, new Timestamp(sample.getModificationDate().getTime()));
			prep.setInt(19, sample.getOscillations());
			prep.setLong(20, sample.getDurationMillis());
			prep.setInt(21, sample.getId());
			prep.setInt(22, sample.getExperimentId());
			
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
			Connection con = h2dao.getConnection();
			
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
			Connection con = h2dao.getConnection();
			
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
	
	private void getSampleFromResultSet(ResultSet rs, Sample sample) throws SQLException {
		sample.setHelixAngle(rs.getInt("HELIX_ANGLE"));
		sample.setDistanceBetweenTurns(rs.getInt("DISTANCE_BETWEEN_TURNS"));
		sample.setApicalDiameter(rs.getBigDecimal("APICAL_DIAMETER"));
		sample.setCreationDate(rs.getDate("CREATION_DATE"));
		sample.setCurvatureAngle(rs.getInt("CURVATURE_ANGLE"));
		sample.setCurvatureRadius(rs.getInt("CURVATURE_RADIUS"));
		sample.setDuctSpeed(rs.getInt("DUCT_SPEED"));
		sample.setEngineAngularSpeed(rs.getInt("ENGINE_ANGULAR_SPEED"));
		sample.setEngineTorque(rs.getBigDecimal("ENGINE_TORQUE"));
		sample.setExperimentId(rs.getInt("EXPERIMENT_ID"));
		sample.setFileType(rs.getString("FILE_TYPE"));
		sample.setId(rs.getInt("ID"));
		sample.setModificationDate(rs.getDate("MODIFICATION_DATE"));
		sample.setMovementTypeId(rs.getInt("MOVEMENT_TYPE_ID"));
		sample.setStudyTypeId(rs.getInt("STUDY_TYPE_ID"));
		sample.setStudyGroup(rs.getString("STUDY_GROUP"));
		sample.setUses(rs.getInt("USES"));
		sample.setSterilizations(rs.getInt("STERILIZATIONS"));
		sample.setConicity(rs.getBigDecimal("CONICITY"));
		sample.setMetalCompositionId(rs.getInt("METAL_COMPOSITION_ID"));
		sample.setOscillations(rs.getInt("OSCILLATIONS"));
		sample.setDurationMillis(rs.getLong("DURATION"));
		sample.setSection(rs.getBigDecimal("SECTION"));
	}
	
	private void setNullSafeValue(PreparedStatement ps, int index, Object value, int sqlType) throws SQLException {
		if(value == null){
			ps.setNull(index, sqlType);
		} else {
			setValueToPreparedStatement(ps, index, value, sqlType);
		}
	}

	private void setValueToPreparedStatement(PreparedStatement ps, int index, Object value, int sqlType) throws SQLException {
		switch(sqlType){
			case java.sql.Types.INTEGER:
				ps.setInt(index, (Integer) value);
				break;
			case java.sql.Types.DECIMAL:
				ps.setBigDecimal(index, (BigDecimal)value);
				break;
			case java.sql.Types.VARCHAR:
				ps.setString(index, (String) value);
				break;
			case java.sql.Types.TIMESTAMP:
				ps.setTimestamp(index, (Timestamp) value);
				break;
			default:
				throw new SQLException("unrecognized SQL Type");
		}
	}
}

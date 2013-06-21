package com.filebreaker.manager.persistence.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.filebreaker.manager.beans.Experiment;
import com.filebreaker.manager.persistence.ExperimentsDAO;

public class ExperimentsDAOImpl extends H2DAO implements ExperimentsDAO {

	public List<Experiment> findExperiments(){
		List<Experiment> result = new ArrayList<Experiment>();
		
		try {
			Connection conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM EXPERIMENTS");
			
			while(rs.next()){
				Experiment experiment = new Experiment();
				
				experiment.setId(rs.getInt("ID"));
				experiment.setName(rs.getString("NAME"));
				experiment.setCreationDate(rs.getDate("CREATION_DATE"));
				experiment.setModificationDate(rs.getDate("MODIFICATION_DATE"));
				
				result.add(experiment);
			}
			
			conn.close();
		} catch (Exception e){
			// TODO: LOG
			e.printStackTrace();
		}
		
		return result;
	}
	
	public Experiment findExperiment(Integer experimentId) {
		Experiment experiment = null;
		
		try {
			Connection conn = getConnection();
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM EXPERIMENTS WHERE ID = " + experimentId);
			
			if(rs.first()){
				experiment = new Experiment();
				
				experiment.setId(rs.getInt("ID"));
				experiment.setName(rs.getString("NAME"));
				experiment.setCreationDate(rs.getDate("CREATION_DATE"));
				experiment.setModificationDate(rs.getDate("MODIFICATION_DATE"));
			}
		} catch(Exception e) {
			// TODO: LOG
			e.printStackTrace();
		}
		
		return experiment;
	}

	public Integer createExperiment(Experiment experiment) {
		Integer experimentId = null;
		
		try {
			Connection con = getConnection();
			
			//prepared statement
			PreparedStatement stat = con.prepareStatement("INSERT INTO EXPERIMENTS (NAME, CREATION_DATE) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			stat.setString(1, experiment.getName());
			stat.setTimestamp(2, new Timestamp(experiment.getCreationDate().getTime()));
			stat.addBatch();
			
			stat.executeBatch();
			
			ResultSet rsKeys = stat.getGeneratedKeys();
			rsKeys.next();
			experimentId = rsKeys.getInt(1);
			con.commit();
		} catch (Exception e){
			// TODO: LOG
			e.printStackTrace();
		}

		return experimentId;
	}

	public void deleteExperiment(Integer experimentId) {
		try {
			Connection con = getConnection();
			
			//prepared statement
			PreparedStatement stat = con.prepareStatement("DELETE FROM EXPERIMENTS WHERE ID=?");
			stat.setInt(1, experimentId);
			
			stat.addBatch();
			stat.executeBatch();
			con.commit();
		} catch(Exception e) {
			// TODO: LOG
			e.printStackTrace();
		}
	}
}

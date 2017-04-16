package com.filebreaker.experiments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.filebreaker.persistence.h2.H2DAO;

@Repository
public class ExperimentsDAO {

	private H2DAO h2dao;
	
	@Autowired
	public ExperimentsDAO(H2DAO h2dao){
		this.h2dao = h2dao;
	}
	
	public List<Experiment> findExperiments(){
		List<Experiment> result = new ArrayList<Experiment>();
		
		try {
			Connection conn = h2dao.getConnection();
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
			Connection conn = h2dao.getConnection();
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
			Connection con = h2dao.getConnection();
			
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
			Connection con = h2dao.getConnection();
			
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

	public void updateExperiment(Experiment experiment) {
		try {
			Connection con = h2dao.getConnection();
			
			//prepared statement
			PreparedStatement prep = con.prepareStatement("UPDATE EXPERIMENTS SET CREATION_DATE=?, MODIFICATION_DATE=?, NAME=? WHERE ID=?");
	
			prep.setTimestamp(1, new Timestamp(experiment.getCreationDate().getTime()));
			prep.setTimestamp(2, new Timestamp(experiment.getModificationDate().getTime()));
			prep.setString(3, experiment.getName());
			prep.setInt(4, experiment.getId());
			
			//batch insert
			prep.addBatch();
		
			prep.executeBatch();
		} catch (Exception e){
			// TODO: LOG
			e.printStackTrace();
		}
	}
}

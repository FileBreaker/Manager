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

	public Integer createExperiment(Experiment experiment) {
		Integer experimentId = null;
		
		try {
			Connection con = getConnection();
			
			//prepared statement
			Statement stat = con.createStatement();
			
			//con.cre
			
			//stat.
			//stat.setString(1, experiment.getName());
			//prep.setTimestamp(2, new Timestamp(experiment.getCreationDate().getTime()));
		
			stat.executeUpdate("INSERT INTO EXPERIMENTS (NAME, CREATION_DATE) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
			con.commit();
			
			experimentId = stat.getGeneratedKeys().getInt(1);
		} catch (Exception e){
			// TODO: LOG
			e.printStackTrace();
		}

		return experimentId;
	}

}

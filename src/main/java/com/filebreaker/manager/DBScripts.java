package com.filebreaker.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class DBScripts {
	
	public static void main(String [] args){
		boolean developmentMode = true;
		
		try {
			if(developmentMode){
				dropAllObjects();
			}
			
			createTableExperiments();
			createTableSamples();
			
			if(developmentMode){
				createDummyExperiments();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void dropAllObjects() throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		Statement stat = con.createStatement();	
		
		//create table
		stat.execute("DROP ALL OBJECTS");

		//close connection
		con.close();
	}
	
	private static void createTableExperiments() throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		Statement stat = con.createStatement();	
		
		//create table
		stat.execute("CREATE TABLE EXPERIMENTS (ID BIGINT AUTO_INCREMENT, " +
												"NAME VARCHAR(200), " +
												"CREATION_DATE datetime, " +
												"MODIFICATION_DATE datetime, " +
												"PRIMARY KEY (ID))");

		//close connection
		con.close();
	}
	
	private static void createTableSamples() throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		Statement stat = con.createStatement();	
		
		//create table
		stat.execute("CREATE TABLE SAMPLES (ID BIGINT AUTO_INCREMENT, " +
											"EXPERIMENT_ID BIGINT NOT NULL, " +
											"CURVATURE_ANGLE INT, " +
											"CURVATURE_RADIUS INT, " +
											"USES INT, " +
											"STERILIZATIONS INT, " +
											"FILE_TYPE VARCHAR(200), " +
											"APICAL_DIAMETER DECIMAL(20, 2), " +
											"CONICITY DECIMAL(20, 2)," +
											"ENGINE_ANGULAR_SPEED INT, " +
											"ENGINE_TORQUE DECIMAL(20, 2), " +
											"SECTION DECIMAL(20, 2), " +
											"DUCT_SPEED INT, " +
											"METAL_COMPOSITION_ID TINYINT, " +
											"MOVEMENT_TYPE_ID TINYINT, " +
											"STUDY_TYPE_ID TINYINT, " +
											"STUDY_GROUP VARCHAR(200), " +
											"CREATION_DATE DATETIME, " +
											"MODIFICATION_DATE DATETIME, " +
											"OSCILLATIONS BIGINT, " +
											"DURATION LONG, " +
											"PRIMARY KEY (ID), " +
											"FOREIGN KEY(EXPERIMENT_ID) REFERENCES EXPERIMENTS(ID))");

		//close connection
		con.close();
	}
	
	private static void createDummyExperiments() throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		
		//prepared statement
		PreparedStatement prep = con.prepareStatement("INSERT INTO EXPERIMENTS (NAME, CREATION_DATE, MODIFICATION_DATE) VALUES (?,?,?)");

		//insert 10 row data
		for (int i = 0; i < 10; i++){
			prep.setString(1, "Experiment-" + i);
			prep.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			prep.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

			//batch insert
			prep.addBatch();
		}
		
		con.setAutoCommit(false);
		prep.executeBatch();
		con.setAutoCommit(true);
		
		//close connection
		con.close();
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		Connection con = DriverManager.getConnection("jdbc:h2:~/test","sa","");
		
		return con;
	}
}

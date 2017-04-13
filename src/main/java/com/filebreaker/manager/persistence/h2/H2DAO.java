package com.filebreaker.manager.persistence.h2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

@Component
public class H2DAO {

	private static final String H2_DRIVER_CLASSNAME = "org.h2.Driver";
	
	private static final String JDBC_H2_URL = "jdbc:h2:~/test;INIT=RUNSCRIPT FROM 'classpath:scripts/init.sql'";

	private static final String H2_USER = "sa";
	
	private static final String H2_PASSWORD = "";

	public H2DAO() throws ClassNotFoundException, SQLException, IOException {
		Class.forName(H2_DRIVER_CLASSNAME);
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_H2_URL, H2_USER, H2_PASSWORD);		
	}
}
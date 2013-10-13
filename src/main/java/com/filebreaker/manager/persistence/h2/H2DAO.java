package com.filebreaker.manager.persistence.h2;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public abstract class H2DAO {

	protected Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		
		//Connection conn = DriverManager.getConnection("jdbc:h2:file:C:\\test", "sa", "");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		
		return conn;
	}

	protected void setNullSafeValue(PreparedStatement ps, int index, Object value, int sqlType) throws SQLException {
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

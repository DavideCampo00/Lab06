package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

public class ConnectDB {
	
	static private HikariDataSource ds= null;

	static private final String jdbcUrl = "jdbc:mysql://localhost/meteo";

	public static Connection getConnection() {

		if(ds==null) {//Singleton
			//crea nuova data source
			ds=new HikariDataSource();
			ds.setJdbcUrl(jdbcUrl);
			ds.setUsername("root");
			ds.setPassword("casavalente00");
		}
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}

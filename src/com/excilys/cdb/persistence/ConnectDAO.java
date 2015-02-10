package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum ConnectDAO {

	instance;

	// private static Connection connect;
	private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private static final String USER = "admincdb";
	private static final String PASSWD = "qwerty1234";

	private ConnectDAO() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection connect = null;

		try {
			connect = DriverManager.getConnection(URL, USER, PASSWD);

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return connect;
	}
	
	public static void close(Connection connect){
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

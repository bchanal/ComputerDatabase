package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDAO {

	private static Connection connect;

	private ConnectDAO() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Statement state = null;
			String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
			String user = "admincdb";
			String passwd = "qwerty1234";

			try {
				connect = DriverManager.getConnection(url, user, passwd);

			} catch (SQLException e) {
				e.printStackTrace();
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public static synchronized Connection getInstance() {

		if (connect == null) {
			new ConnectDAO();
		}
		return connect;
	}
}

package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.ConnectionException;
import com.excilys.cdb.util.PropertyValue;

/**
 * ConnectDAO : class to have a connection to the DB
 * 
 * @author berangere
 *
 */
public enum ConnectDao {

	instance;

	private final static Logger LOGGER = LoggerFactory
			.getLogger(ConnectDao.class);

	// private static Connection connect;
	private static final String URL = "jdbc:mysql://localhost:3306/"
			+ PropertyValue.INSTANCE.getDbName()
			+ "?zeroDateTimeBehavior=convertToNull"; // computer-database-db
	private static final String USER = "admincdb";
	private static final String PASSWD = "qwerty1234";

	private ConnectDao() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ConnectionException();
		}
	}

	/**
	 * create a new connection to the DB when the function is requested
	 * 
	 * @return connect the connection to the DB.
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		Connection connect = null;

		try {
			connect = DriverManager.getConnection(URL, USER, PASSWD);

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());

			throw new ConnectionException();

		}

		return connect;
	}

	/**
	 * close
	 * 
	 * @param connect
	 *            the connection to close
	 */
	public static void close(Connection connect) {
		try {
			connect.close();
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new ConnectionException();

		}
	}
}

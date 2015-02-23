package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.ConnectionException;
import com.excilys.cdb.util.PropertyValue;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

/**
 * ConnectDAO : class to have a connection to the DB
 * 
 * @author berangere
 *
 */
public class ConnectDao {

	private final static Logger LOGGER = LoggerFactory.getLogger(ConnectDao.class);
	
	private static final String URL =PropertyValue.instance.getUrl();
	private static final String USER =PropertyValue.instance.getUser();
	private static final String PASSWD =PropertyValue.instance.getPasswd();
	private static final int MINCONNECTIONS =PropertyValue.instance.getMin();
	private static final int MAXCONNECTIONS = PropertyValue.instance.getMax();
	private static final int PARTITIONCOUNT =PropertyValue.instance.getPartitionCount();
	private static BoneCP CONNECTIONPOOL;

	/**
	 * initialize connection
	 */
	static {
		System.out.println("salut");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ConnectionException();
		}

		BoneCPConfig config = new BoneCPConfig();
		config.setJdbcUrl(URL);
		config.setUsername(USER);
		config.setPassword(PASSWD);
		config.setMinConnectionsPerPartition(MINCONNECTIONS);
		config.setMaxConnectionsPerPartition(MAXCONNECTIONS);
		config.setPartitionCount(PARTITIONCOUNT);

		try {
			CONNECTIONPOOL = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new ConnectionException();
		}
	}

	/**
	 * create a new connection to the DB when the function is requested
	 * 
	 * @return connect the connection to the DB.
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection connect = null;

		try {
			// connect = DriverManager.getConnection(URL, USER, PASSWD);
			connect = CONNECTIONPOOL.getConnection();

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

package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.ConnectionException;
import com.excilys.cdb.util.PropertyValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/**
 * ConnectDAO : class to have a connection to the DB
 * 
 * @author berangere
 *
 */
@Component
public class ConnectDao {

	private final static Logger LOGGER = LoggerFactory.getLogger(ConnectDao.class);
	
	@Autowired
	private static PropertyValue pv;
	
	private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";//pv.getUrl();
	private static final String USER = "admincdb"; //pv.getUser();
	private static final String PASSWD = "querty1234"; //pv.getPasswd();
	// private static final int MINCONNECTIONS = pv.getMin();
	// private static final int MAXCONNECTIONS = pv.getMax();
	// private static final int PARTITIONCOUNT = pv.getPartitionCount();
	// private static BoneCP CONNECTIONPOOL;
	private static DriverManagerDataSource dataSource;
	@Autowired
	private static ThreadLocal<Connection> connectThread = new ThreadLocal<Connection>();

	/**
	 * initialize connection
	 */
	static {
		dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl(URL);
		dataSource.setUsername(USER);
		dataSource.setPassword(PASSWD);

		// try {
		// Class.forName("com.mysql.jdbc.Driver");
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// throw new ConnectionException();
		// }

		// BoneCPConfig config = new BoneCPConfig();
		// config.setJdbcUrl(URL);
		// config.setUsername(USER);
		// config.setPassword(PASSWD);
		// config.setMinConnectionsPerPartition(MINCONNECTIONS);
		// config.setMaxConnectionsPerPartition(MAXCONNECTIONS);
		// config.setPartitionCount(PARTITIONCOUNT);

		// try {
		// CONNECTIONPOOL = new BoneCP(config);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// LOGGER.error(e.getMessage());
		// throw new ConnectionException();
		// }
	}

	/**
	 * create a new connection to the DB when the function is requested
	 * 
	 * @return connect the connection to the DB.
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {

		if (connectThread.get() == null) {
			// connectThread.set(CONNECTIONPOOL.getConnection());
			connectThread.set(dataSource.getConnection());
		}

		if (connectThread.get() != null) {
			LOGGER.debug("get a connection from the threadlocal "
					+ connectThread.get().hashCode());
			return connectThread.get();
		}
		return connectThread.get();
	}
	
	public static DriverManagerDataSource getDataSource() {
			return dataSource;
	}

	public static void initTransaction() {
		Connection connect;
		try {
			// connect = CONNECTIONPOOL.getConnection();
			connect = dataSource.getConnection();
			connect.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new ConnectionException();
		}
		connectThread.set(connect);
	}

	/**
	 * close connection
	 * 
	 */
	public static void close() {
		try {
			Connection connect = connectThread.get();
			if (connect.getAutoCommit()) {
				connect.close();
				connectThread.remove();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new ConnectionException();

		}
	}

	public static void closeTransaction() {
		try {
			Connection connect = connectThread.get();
			connect.close();
			connectThread.remove();
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new ConnectionException();

		}
	}
}

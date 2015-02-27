package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.ConnectionException;
import com.excilys.cdb.util.PropertyValue;

import org.springframework.beans.factory.annotation.Autowired;
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
	private  PropertyValue pv;
	private  DataSource dataSource;
	
	private  ThreadLocal<Connection> connectThread = new ThreadLocal<Connection>();

	/**
	 * create a new connection to the DB when the function is requested
	 * 
	 * @return connect the connection to the DB.
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {

		if (connectThread.get() == null) {
			connectThread.set(dataSource.getConnection());
		}

		if (connectThread.get() != null) {
			LOGGER.debug("get a connection from the threadlocal "
					+ connectThread.get().hashCode());
			return connectThread.get();
		}
		return connectThread.get();
	}
	
	
	public DataSource getDataSource() {
			return dataSource;
	}

	public void initTransaction() {
		Connection connect;
		try {
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
	public void close() {
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

	public void closeTransaction() {
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

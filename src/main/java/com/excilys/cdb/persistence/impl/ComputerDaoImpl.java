package com.excilys.cdb.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.dto.DtoMapper;
import com.excilys.cdb.model.*;
import com.excilys.cdb.persistence.ConnectDao;
import com.excilys.cdb.persistence.util;
import com.excilys.cdb.persistence.mapper.ComputerMapper;

/**
 * ComputerDAO contains all the requests concerning the computers
 * 
 * @author berangere
 * 
 */
public enum ComputerDaoImpl {

	instance;

	private final static Logger LOGGER = LoggerFactory
			.getLogger(ComputerDaoImpl.class);

	private ComputerDaoImpl() {
	}

	/**
	 * get All computers and return them in a list
	 * 
	 * @return list the list of all computers
	 */
	public static List<Computer> getAll(boolean isTransaction) {
		ResultSet result = null;
		Connection connect = null;
		List<Computer> list = null;
		Statement state = null;
		try {
			connect = ConnectDao.getConnection();
			state = connect.createStatement();
			result = state.executeQuery("SELECT * FROM computer");
			list = ComputerMapper.instance.toList(result);

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		} finally {
			try {
				result.close();
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
			}
			ConnectDao.close(connect);
		}
		return list;
	}

	/**
	 * get All computers and return them in a list
	 * 
	 * @param index
	 *            the first id requested
	 * @param nb
	 *            the number of computers we want
	 * @param name
	 *            the name searched
	 * @return list the list of computers
	 */
	public Page getAPage(int index, int nb, String name, boolean isTransaction) {

		String query = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY computer.id LIMIT ? , ?;";
		// String query =
		// "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id > AND computer.id < ?;";
		Connection connect = null;
		ResultSet result = null;
		PreparedStatement prep1 = null;
		List<Computer> list = null;
		List<ComputerDto> listdto = new ArrayList<ComputerDto>();

		Page page;

		try {
			connect = ConnectDao.getConnection();

			prep1 = connect.prepareStatement(query);

			prep1.setString(1, "%" + name + "%");
			prep1.setString(2, "%" + name + "%");

			prep1.setInt(3, index);
			prep1.setInt(4, nb);

			result = prep1.executeQuery();

			list = ComputerMapper.instance.toList(result);

			// TODO : faire ces trois lignes en Stream
			for (Computer c : list) {
				listdto.add(DtoMapper.computerToDto(c));
			}

			page = new Page(index, nb, listdto, 1000);

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		} finally {
			try {
				if (result != null)
					result.close();
				if (prep1 != null)
					prep1.close();

			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
			}
			if (!isTransaction)
				ConnectDao.close(connect);

		}

		return page;
	}

	/**
	 * get the number of computers for a request
	 * 
	 * @param name
	 *            the name searched
	 * @return size the number of results
	 */
	public int getNbComputers(String name, boolean isTransaction) {

		String query = "SELECT COUNT(*) " + "FROM computer "
				+ "LEFT JOIN company ON computer.company_id = company.id "
				+ "WHERE computer.name LIKE ? OR company.name LIKE ?;";
		Connection connect = null;
		ResultSet result = null;
		PreparedStatement prep1 = null;
		int size = 0;

		try {
			connect = ConnectDao.getConnection();
			prep1 = connect.prepareStatement(query);

			prep1.setString(1, "%" + name + "%");
			prep1.setString(2, "%" + name + "%");

			result = prep1.executeQuery();
			result.next();

			size = result.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		} finally {
			try {
				result.close();
				prep1.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
			}

			if (!isTransaction)
				ConnectDao.close(connect);
		}

		return size;
	}

	/**
	 * get a computer by id
	 * 
	 * @param id
	 *            the id to get
	 * @return comp the Computer requested
	 */
	public Computer getById(int id, boolean isTransaction) {
		Computer comp = null;
		ResultSet result = null;
		Connection connect = null;
		PreparedStatement prep1 = null;
		String query = "SELECT * FROM computer WHERE id= ?";
		try {
			connect = ConnectDao.getConnection();
			prep1 = connect.prepareStatement(query);

			prep1.setInt(1, id);

			result = prep1.executeQuery();
			result.next();
			comp = ComputerMapper.instance.toObject(result);

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();
		} finally {
			try {
				result.close();
				prep1.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
			}
			if (!isTransaction)
				ConnectDao.close(connect);
		}

		return comp;
	}

	/**
	 * get a computer by name
	 * 
	 * @param name
	 *            the name searched
	 * @return comp the Computer requested
	 */
	public List<Computer> getByName(String name, boolean isTransaction) {

		String query = "SELECT * " + "FROM computer "
				+ "LEFT JOIN company ON computer.company_id = company.id "
				+ "WHERE computer.name LIKE ? OR company.name LIKE ?;";
		List<Computer> comp = null;
		Connection connect = null;
		ResultSet result = null;
		PreparedStatement prep1 = null;

		try {
			connect = ConnectDao.getConnection();
			prep1 = connect.prepareStatement(query);

			prep1.setString(1, "%" + name + "%");
			prep1.setString(2, "%" + name + "%");

			result = prep1.executeQuery();
			result.next();
			comp = ComputerMapper.instance.toList(result);

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		} finally {
			try {
				result.close();
				prep1.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
			}

			if (!isTransaction)
				ConnectDao.close(connect);
		}

		return comp;

	}

	/**
	 * create a computer in the db
	 * 
	 * @param name
	 *            the name of the computer
	 * @param dateTime
	 *            the date it was introduced (if exists, null otherwise)
	 * @param dateTimeFin
	 *            the date it was discontinued (if exists, null otherwise)
	 * @param comp
	 *            the id of the computer's company (if exists, 0 otherwise)
	 */
	public void create(String name, LocalDateTime dateTime,
			LocalDateTime dateTimeFin, int comp, boolean isTransaction) {
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
		Connection connect = null;
		PreparedStatement prep1 = null;

		try {

			connect = ConnectDao.getConnection();
			prep1 = connect.prepareStatement(query);
			prep1.setString(1, name);

			if (dateTime != null) {
				prep1.setTimestamp(2, util.getTimestamp(dateTime));
			} else {
				prep1.setNull(2, java.sql.Types.TIMESTAMP);
			}

			if (dateTimeFin != null) {
				prep1.setTimestamp(3, util.getTimestamp(dateTimeFin));
			} else {
				prep1.setNull(3, java.sql.Types.TIMESTAMP);
			}

			if (comp != 0) {
				prep1.setInt(4, comp);
			} else {
				prep1.setNull(4, java.sql.Types.BIGINT);
			}
			int ok = prep1.executeUpdate();
			System.out.println(ok);

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		} finally {
			try {
				prep1.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
			}
			if (!isTransaction)
				ConnectDao.close(connect);
		}

	}

	/**
	 * delete a computer in the DB
	 * 
	 * @param id
	 *            the id of the computer to delete
	 */
	public synchronized static void delete(int id, boolean isTransaction) {
		Connection connect = null;
		PreparedStatement prep1 = null;
		String query = "DELETE FROM computer WHERE id= ?";

		try {

			connect = ConnectDao.getConnection();
			prep1 = connect.prepareStatement(query);

			prep1.setInt(1, id);
			prep1.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		} finally {
			try {
				prep1.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
			}
			if (!isTransaction)
				ConnectDao.close(connect);
		}
	}

	/**
	 * update a computer in the DB
	 * 
	 * @param computer
	 *            the computer to update
	 */
	public synchronized void update(Computer computer, boolean isTransaction) {
		Connection connect = null;
		PreparedStatement prep1 = null;
		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";

		try {
			connect = ConnectDao.getConnection();

			prep1 = connect.prepareStatement(query);
			prep1.setString(1, computer.getName());

			if (computer.getDateIntro() != null) {
				prep1.setTimestamp(2,util.getTimestamp(computer.getDateIntro()));
			} else {
				prep1.setNull(2, java.sql.Types.TIMESTAMP);
			}

			if (computer.getDateDiscontinued() != null) {
				prep1.setTimestamp(3,util.getTimestamp(computer.getDateDiscontinued()));
			} else {
				prep1.setNull(3, java.sql.Types.TIMESTAMP);
			}

			if (computer.getManufacturer().getId() != 0) {
				prep1.setInt(4, computer.getManufacturer().getId());
			} else {
				prep1.setNull(4, java.sql.Types.BIGINT);
			}

			prep1.setInt(5, computer.getId());

			prep1.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		} finally {
			try {
				prep1.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
			}
			if (!isTransaction)
				ConnectDao.close(connect);
		}

	}

}

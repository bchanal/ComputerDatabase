package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import com.excilys.cdb.model.*;

/**
 * 
 * @author berangere ComputerDAO contains all the requests concerning the
 *         computers
 */
public enum ComputerDAOImpl {

	instance;

	private ComputerDAOImpl() {
	}

	/**
	 * get All computers and return them in a list
	 * 
	 * @return list the list of computers
	 */
	public static List<Computer> getAll() {
		ResultSet result = null;
		Connection connect = null;
		List<Computer> list = null;
		Statement state;
		try {
			connect = ConnectDAO.instance.getConnection();
			state = connect.createStatement();
			result = state.executeQuery("SELECT * FROM computer");
			list = ComputerMapper.instance.toList(result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDAO.close(connect);
		}

		return list;
	}

	/**
	 * get a computer by id
	 * 
	 * @param id
	 * @return comp the Computer requested
	 */
	public static Computer getById(int id) {
		Computer comp = null;
		ResultSet result = null;
		Connection connect = null;
		Statement state = null;
		try {
			connect = ConnectDAO.instance.getConnection();
			state = connect.createStatement();

			result = state
					.executeQuery("SELECT * FROM computer WHERE id=" + id);
			comp = ComputerMapper.instance.toObject(result);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDAO.close(connect);
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
	public static void create(String name, LocalDateTime dateTime,
			LocalDateTime dateTimeFin, int comp) {
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
		Connection connect = null;
		try {
			connect = ConnectDAO.instance.getConnection();
			PreparedStatement prep1 = connect.prepareStatement(query);

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
		} finally {
			ConnectDAO.close(connect);
		}

	}

	/**
	 * delete a computer in the DB
	 * 
	 * @param id
	 *            the id of the computer to delete
	 */
	public synchronized static void delete(int id) {
		Connection connect = null;
		try {

			String query = "DELETE FROM computer WHERE id= ?";
			connect = ConnectDAO.instance.getConnection();
			PreparedStatement prep1 = connect.prepareStatement(query);

			prep1.setInt(1, id);
			prep1.executeUpdate();

			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * update a computer in the DB
	 * 
	 * @param computer
	 *            the computer to update
	 */
	public static synchronized void update(Computer computer) {
		Connection connect = null;
		try {

			String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
			connect = ConnectDAO.instance.getConnection();
			PreparedStatement prep1 = connect.prepareStatement(query);

			prep1.setString(1, computer.getName());

			if (computer.getDateIntro() != null) {
				prep1.setTimestamp(2,
						util.getTimestamp(computer.getDateIntro()));
			} else {
				prep1.setNull(2, java.sql.Types.TIMESTAMP);
			}

			if (computer.getDateDiscontinued() != null) {
				prep1.setTimestamp(3,
						util.getTimestamp(computer.getDateDiscontinued()));
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

			// state.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

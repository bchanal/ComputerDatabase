package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import com.excilys.cdb.model.*;

public enum ComputerDAO {

	instance;

	private ComputerDAO() {
	}

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

		/**
		 * while (result.next()) {
		 * 
		 * LocalDateTime t1 = null; if (result.getTimestamp("introduced") !=
		 * null) { t1 = result.getTimestamp("introduced").toLocalDateTime(); }
		 * 
		 * LocalDateTime t2 = null; if (result.getTimestamp("discontinued") !=
		 * null) { t1 = result.getTimestamp("discontinued").toLocalDateTime(); }
		 * 
		 * int c = 0; Company co = null; if (result.getInt("company_id") != 0) {
		 * c = result.getInt("company_id"); CompanyDAO cdao =
		 * CompanyDAO.instance; co = cdao.getById(c); } Computer comp = new
		 * Computer(result.getInt("id"), result.getString("name"), t1, t2, co);
		 * 
		 * listComputer.add(comp); }
		 * 
		 * // result.close(); // state.close(); // conn.close();
		 */
		return list;
	}

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

			/**
			 * result.next();
			 * 
			 * LocalDateTime t1 = null; if (result.getTimestamp("introduced") !=
			 * null) { t1 = result.getTimestamp("introduced").toLocalDateTime();
			 * }
			 * 
			 * LocalDateTime t2 = null; if (result.getTimestamp("discontinued")
			 * != null) { t1 =
			 * result.getTimestamp("discontinued").toLocalDateTime(); }
			 * 
			 * int c = 0; Company co = null; if (result.getInt("company_id") !=
			 * 0) { c = result.getInt("company_id"); CompanyDAO cdao =
			 * CompanyDAO.instance; co = cdao.getById(c); }
			 * 
			 * comp = new Computer(result.getInt("id"),
			 * result.getString("name"), t1, t2, co);
			 * 
			 * // result.close(); // state.close(); // conn.close();
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDAO.close(connect);
		}

		return comp;

	}

	public static void create(String name, LocalDateTime dateTime,
			LocalDateTime dateTimeFin, int comp) {
		Computer ordi = null;

		/**
		 * String query =
		 * "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES ('"
		 * + nom + "',"; // a changer en preparedRequest if (dateTime != null) {
		 * query += "'" + dateTime + "' ,"; } else { query += "null ,"; }
		 * 
		 * if (dateTimeFin != null) { query += "'" + dateTimeFin + "' ,"; } else
		 * { query += "null ,"; } query += comp + ");";
		 */

		String query = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES ?, ?, ?, ?";
		Connection connect = null;
		try {
			connect = ConnectDAO.instance.getConnection();
			PreparedStatement prep1 = connect.prepareStatement(query);

			prep1.setString(1, name);

			if (dateTime != null) {
				prep1.setTimestamp(2, util.getTimestamp(dateTime));
			} else {
				prep1.setTimestamp(2, null);
			}

			if (dateTimeFin != null) {
				prep1.setTimestamp(3, util.getTimestamp(dateTimeFin));
			} else {
				prep1.setTimestamp(3, null);
			}

			if (comp != 0) {
				prep1.setInt(4, comp);
			} else {
				prep1.setInt(4, 0);
			}
			prep1.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			ConnectDAO.close(connect);
		}

		// int result = state.executeUpdate(query);
	}

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
				prep1.setTimestamp(2, null);
			}

			if (computer.getDateDiscontinued() != null) {
				prep1.setTimestamp(3,
						util.getTimestamp(computer.getDateDiscontinued()));
			} else {
				prep1.setTimestamp(3, null);
			}

			if (computer.getManufacturer().getId() != 0) {
				prep1.setInt(4, computer.getManufacturer().getId());
			} else {
				prep1.setInt(4, 0);
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

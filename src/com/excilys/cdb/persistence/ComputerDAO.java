package com.excilys.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.*;

public class ComputerDAO {

	public ComputerDAO() {
	}

	public static List<Computer> getAllComputer() {
		List<Computer> listComputer = new ArrayList<Computer>();

		try {
			Statement state = ConnectDAO.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM computer");

			while (result.next()) {

				LocalDateTime t1 = null;
				if (result.getTimestamp("introduced") != null) {
					t1 = result.getTimestamp("introduced").toLocalDateTime();
				}

				LocalDateTime t2 = null;
				if (result.getTimestamp("discontinued") != null) {
					t1 = result.getTimestamp("discontinued").toLocalDateTime();
				}

				int c = 0;
				if (result.getInt("company_id") != 0) {
					c = result.getInt("company_id");
				}
				Computer comp = new Computer(result.getInt("id"),
						result.getString("name"), t1, t2, c);

				listComputer.add(comp);
			}

			//result.close();
			//state.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listComputer;
	}

	public static Computer getAComputer(int id) {
		Computer comp = null;
		try {
			Statement state = ConnectDAO.getInstance().createStatement();

			ResultSet result = state
					.executeQuery("SELECT * FROM computer WHERE id=" + id);

			result.next();

			LocalDateTime t1 = null;
			if (result.getTimestamp("introduced") != null) {
				t1 = result.getTimestamp("introduced").toLocalDateTime();
			}

			LocalDateTime t2 = null;
			if (result.getTimestamp("discontinued") != null) {
				t1 = result.getTimestamp("discontinued").toLocalDateTime();
			}

			int c = 0;
			if (result.getInt("company_id") != 0) {
				c = result.getInt("company_id");
			}

			comp = new Computer(result.getInt("id"), result.getString("name"),
					t1, t2, c);

			//result.close();
			//state.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comp;

	}

	public static Computer createComputer(String nom, LocalDateTime dateTime,
			LocalDateTime dateTimeFin, int comp) throws SQLException {
		Computer ordi = null;

		Statement state = ConnectDAO.getInstance().createStatement();
		String query = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES ('"
				+ nom + "',";
		// a changer en preparedRequest
		if (dateTime != null) {
			query += "'" + dateTime + "' ,";
		} else {
			query += "null ,";
		}

		if (dateTimeFin != null) {
			query += "'" + dateTimeFin + "' ,";
		} else {
			query += "null ,";
		}
		query += comp + ");";

		int result = state.executeUpdate(query);
		return ordi;
	}

	public static synchronized void deleteComputer(int id) {
		try {

			Statement state = ConnectDAO.getInstance().createStatement();

			@SuppressWarnings("unused")
			
			// a changer en preparedRequest

			
			int result = state.executeUpdate("DELETE FROM computer WHERE id="
					+ id);
			//state.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

		public static synchronized void updateComputer(Computer ordi) {
		try {

			Statement state = ConnectDAO.getInstance().createStatement();

			@SuppressWarnings("unused")
			String query = "UPDATE computer SET name = '" + ordi.getName() + "',";

			// a changer en preparedRequest

			
			if (ordi.getDateIntro() != null) {
				query += "introduced = '" + ordi.getDateIntro() + "' ,";
			} else {
				query += "introduced = null ,";
			}

			if (ordi.getDateDiscontinued() != null) {
				query += "discontinued = '" + ordi.getDateDiscontinued() + "' ,";
			} else {
				query += "discontinued = null ,";
			}
			query += "company_id = "+ordi.getManufacturer() ;
			query+=" WHERE id = "+ordi.getIdentifiant();
			System.out.println(query);
			int resultat = state.executeUpdate(query);

			//state.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

package com.excilys.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.cdb.model.*;

public class CompanyDAO {

	public CompanyDAO() {
	}

	public static ArrayList<Company> getAllCompany() throws SQLException {
		ArrayList<Company> listCompany = new ArrayList<Company>();

		try {

			Statement state = ConnectDAO.getInstance().createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM company");

			while (result.next()) {
				Company comp = new Company(result.getInt("id"),
						result.getString("name"));
				listCompany.add(comp);
			}
			//result.close();
			//state.close();
			//conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listCompany;
	}

	public static Company getACompany(int id) throws SQLException {
		Company comp = null;
		try {

			Statement state = ConnectDAO.getInstance().createStatement();
			ResultSet result = state
					.executeQuery("SELECT * FROM company WHERE id=" + id);
			
			result.next();
			comp = new Company(result.getInt("id"), result.getString("name"));

			//result.close();
			//state.close();
			//conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return comp;
	}

}

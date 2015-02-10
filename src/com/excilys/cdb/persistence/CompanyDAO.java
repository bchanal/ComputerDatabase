package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.*;

public enum CompanyDAO {

	instance;

	private CompanyDAO() {
	}

	public static List<Company> getAll() throws SQLException {
		List<Company> listCompany = new ArrayList<Company>();
		Connection connect = null;
		Statement state = null;
		ResultSet result = null;

		connect = ConnectDAO.instance.getConnection();
			state = connect.createStatement();
			result = state.executeQuery("SELECT * FROM company");
			listCompany = CompanyMapper.instance.toList(result);
			ConnectDAO.close(connect);

		return listCompany;
		
	}

	public Company getById(int id) throws SQLException {
		Company comp = null;
		Connection connect= null;
		try {

			String query = "SELECT * FROM company WHERE id= ?";
			connect = ConnectDAO.instance.getConnection();
			PreparedStatement prep1 = connect.prepareStatement(query);

			prep1.setInt(1, id);
			ResultSet result = prep1.executeQuery();

			result.next();
			comp = new Company(result.getInt("id"), result.getString("name"));

			// result.close();
			// state.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectDAO.close(connect);
		}

		return comp;
	}

}

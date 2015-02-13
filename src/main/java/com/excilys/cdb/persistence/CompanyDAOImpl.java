package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.*;

/**
 * CompanyDAO contains all the requests concerning companies
 * 
 * @author berangere
 *
 */
public enum CompanyDAOImpl implements CompanyDAO {

	instance;

	private CompanyDAOImpl() {
	}

	/**
	 * create a DAO, get the list of companies and return it in a ArrayList
	 * 
	 * @return listCompany : all the companies in a list
	 * @throws SQLException
	 */
	public List<Company> getAll() {
		List<Company> listCompany = new ArrayList<Company>();
		Connection connect = null;
		Statement state = null;
		ResultSet result = null;

		try {
			connect = ConnectDAO.instance.getConnection();
			state = connect.createStatement();
			result = state.executeQuery("SELECT * FROM company");
			listCompany = CompanyMapper.instance.toList(result);
			result.close();
			state.close();
			ConnectDAO.close(connect);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally{
			
		}

		return listCompany;

	}

	/**
	 * get a company by id.
	 * 
	 * @param id
	 *            : the id from the company requested
	 * @return comp the Computer requested
	 * @throws SQLException
	 */
	public Company getById(int id) throws SQLException {
		Company comp = null;
		Connection connect = null;
		ResultSet result = null;
		PreparedStatement prep1 = null;
		try {

			String query = "SELECT * FROM company WHERE id= ?";
			connect = ConnectDAO.instance.getConnection();
			prep1 = connect.prepareStatement(query);

			prep1.setInt(1, id);
			result = prep1.executeQuery();

			if (result.next()) {
				comp = new Company(result.getInt("id"),
						result.getString("name")); // mettre dans mapper
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();

		} finally {
			result.close();
			prep1.close();
			ConnectDAO.close(connect);
		}

		return comp;
	}

}

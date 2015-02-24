package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.*;

/**
 * CompanyDAO contains all the requests concerning companies
 * 
 * @author berangere
 *
 */
public enum CompanyDaoImpl implements CompanyDao {

	instance;

	private final static Logger LOGGER = LoggerFactory
			.getLogger(CompanyDaoImpl.class);

	private CompanyDaoImpl() {
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
			connect = ConnectDao.getConnection();
			state = connect.createStatement();
			result = state.executeQuery("SELECT * FROM company");
			listCompany = CompanyMapper.instance.toList(result);
			result.close();
			state.close();
			ConnectDao.close(connect);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();
		} finally {

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
			connect = ConnectDao.getConnection();
			prep1 = connect.prepareStatement(query);

			prep1.setInt(1, id);
			result = prep1.executeQuery();

			if (result.next()) {
				comp = new Company(result.getInt("id"),
						result.getString("name")); // mettre dans mapper
			}

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		} finally {
			result.close();
			prep1.close();
			ConnectDao.close(connect);
		}

		return comp;
	}
	/**
	 * delete 	 company and all the computers made by this company
	 * @param connect the connection
	 * @param id the id of the computer to delete
	 */
	public synchronized static void delete(Connection connect, int id) {
		PreparedStatement prep1 = null;
		PreparedStatement prep2 = null;
		try {
			String query = "DELETE FROM computer WHERE company_id= ?";
			String query2 = "DELETE FROM company WHERE id= ?";
			System.out.println(id);

			prep1 = connect.prepareStatement(query);
			prep1.setInt(1, id);
			prep1.executeUpdate();
			
			prep2 = connect.prepareStatement(query2);
			prep2.setInt(1, id);
			prep2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		} finally {
			try {
				prep1.close();
				prep2.close();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
			}
		}
	}

}

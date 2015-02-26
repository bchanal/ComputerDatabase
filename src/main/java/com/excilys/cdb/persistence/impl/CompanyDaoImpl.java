package com.excilys.cdb.persistence.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.*;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.ConnectDao;
import com.excilys.cdb.persistence.mapper.SpringCompanyMapper;

/**
 * CompanyDAO contains all the requests concerning companies
 * 
 * @author berangere
 *
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SpringCompanyMapper cmap; 

	private CompanyDaoImpl() {
		this.jdbcTemplate = new JdbcTemplate(ConnectDao.getDataSource());
	}

	/**
	 * create a DAO, get the list of companies and return it in a ArrayList
	 * 
	 * @return listCompany : all the companies in a list
	 */
	public List<Company> getAll() {
		List<Company> listCompany = new ArrayList<Company>();
//		Connection connect = null;
//		PreparedStatement prep1 = null;
//		ResultSet result = null;
		String query = "SELECT * FROM company";
		listCompany = jdbcTemplate.query(query, cmap);

//		try {
//			connect = ConnectDao.getConnection();
//			prep1 = connect.prepareStatement(query);
//			result = prep1.executeQuery();
//			listCompany = cmap.toList(result);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			LOGGER.error(e.getMessage());
//			throw new RuntimeException();
//		} finally {
//			try {
//				result.close();
//				prep1.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				LOGGER.error(e.getMessage());
//				throw new ConnectionException();
//			}
//			ConnectDao.close();
//		}
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
//		Connection connect = null;
//		ResultSet result = null;
//		PreparedStatement prep1 = null;
//		try {

			String query = "SELECT * FROM company WHERE id= ?";
			comp = jdbcTemplate.queryForObject(query, new Object[]{id}, cmap);
//			connect = ConnectDao.getConnection();
//			prep1 = connect.prepareStatement(query);
//
//			prep1.setInt(1, id);
//			result = prep1.executeQuery();
//
//			if (result.next()) {
//				comp = new Company(result.getInt("id"),
//						result.getString("name")); // mettre dans mapper
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			LOGGER.error(e.getMessage());
//			throw new RuntimeException();
//
//		} finally {
//			result.close();
//			prep1.close();
//			ConnectDao.close();
//		}

		return comp;
	}

	/**
	 * delete company and all the computers made by this company
	 * 
	 * @param connect
	 *            the connection
	 * @param id
	 *            the id of the computer to delete
	 */
	public synchronized void delete(Connection connect, int id) {
//		PreparedStatement prep1 = null;
//		PreparedStatement prep2 = null;
//		try {
			String query = "DELETE FROM computer WHERE company_id= ?";
			String query2 = "DELETE FROM company WHERE id= ?";
			jdbcTemplate.update(query, id);
			jdbcTemplate.update(query2, id);
			
			System.out.println(id);

//			prep1 = connect.prepareStatement(query);
//			prep1.setInt(1, id);
//			prep1.executeUpdate();
//
//			prep2 = connect.prepareStatement(query2);
//			prep2.setInt(1, id);
//			prep2.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//			LOGGER.error(e.getMessage());
//			throw new RuntimeException();
//
//		} finally {
//			try {
//				prep1.close();
//				prep2.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				LOGGER.error(e.getMessage());
//			}
//		}
	}

}

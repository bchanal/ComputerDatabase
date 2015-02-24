package com.excilys.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;

/**
 * CompanyMapper is used to display companies from ResultSets
 * 
 * @author berangere
 *
 *
 */
public enum CompanyMapper implements RowMapper<Company> {

	instance;

	private final static Logger LOGGER = LoggerFactory.getLogger(CompanyMapper.class);

	
	/**
	 * toObject return an object company from a resultset
	 * @param rs the resultset
	 * @return company the company
	 */

	@Override
	public Company toObject(ResultSet rs) {
		Company company = null;

		try {
			company = new Company(rs.getInt("id"), rs.getString("name"));
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();		}
		
		return company;

	}

}

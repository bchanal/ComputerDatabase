package com.excilys.cdb.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

/**
 * ComputerMapper is a mapper for the display of computers
 * 
 * @author berangere
 *
 */
public enum ComputerMapper implements RowMapper<Computer> {

	instance;

	private final static Logger LOGGER = LoggerFactory.getLogger(ComputerMapper.class);

	
	/**
	 * toObject return a computer contained in a ResulSet
	 * 
	 * @param result
	 *            the resultset
	 * @return comp the Computer in a the ResultSet
	 */
	public Computer toObject(ResultSet result) {

		Computer comp = null;
		LocalDateTime t1 = null;
		LocalDateTime t2 = null;
		Company co = null;
		
		if(result==null){
			return null;
		}
		
		try {
				if (result.getTimestamp("introduced") != null) {
					t1 = result.getTimestamp("introduced").toLocalDateTime();
				}

				if (result.getTimestamp("discontinued") != null) {
					t2 = result.getTimestamp("discontinued").toLocalDateTime();
				}

				if (result.getInt("company_id") != 0) {
					
					int coId = result.getInt("company.id");
					String coName = result.getString("company.name");
					co = new Company(coId, coName);
					
					//c = result.getInt("company_id");
				}
//					CompanyDaoImpl cdao = CompanyDaoImpl.instance;
//					co = cdao.getById(c);
					comp = new Computer(result.getInt("computer.id"),
							result.getString("computer.name"), t1, t2, co);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();
		}
		return comp;
	}
}
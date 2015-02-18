package com.excilys.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

	private final static Logger LOGGER = LoggerFactory
			.getLogger(ComputerMapper.class);

	/**
	 * toList returns a ResulSet a list of computers contained in a ResultSet
	 * 
	 * @param result
	 *            the ResultSet to display
	 * @return listComputer the list of computers from the resulSet.
	 */
	public List<Computer> toList(ResultSet result) {
		List<Computer> listComputer = new ArrayList<Computer>();

		try {
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
				Company co = null;
				if (result.getInt("company_id") != 0) {
					c = result.getInt("company_id");
					CompanyDaoImpl cdao = CompanyDaoImpl.instance;
					co = cdao.getById(c);
				}

				Computer comp = new Computer(result.getInt("id"),
						result.getString("name"), t1, t2, co);

				listComputer.add(comp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		}

		// result.close();
		// state.close();
		// conn.close();

		return listComputer;

	}

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

		try {
			while (result.next()) {

				if (result.getTimestamp("introduced") != null) {
					t1 = result.getTimestamp("introduced").toLocalDateTime();
				}

				if (result.getTimestamp("discontinued") != null) {
					t1 = result.getTimestamp("discontinued").toLocalDateTime();
				}

				int c = 0;
				if (result.getInt("company_id") != 0) {
					c = result.getInt("company_id");
					CompanyDaoImpl cdao = CompanyDaoImpl.instance;
					co = cdao.getById(c);

					comp = new Computer(result.getInt("id"),
							result.getString("name"), t1, t2, co);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		}

		return comp;

	}
}

package com.excilys.cdb.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

@Component
public class SpringComputerMapper implements RowMapper<Computer> {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(SpringComputerMapper.class);

	public SpringComputerMapper() {
	}

	@Override
	public Computer mapRow(ResultSet result, int arg1) throws SQLException {
		Computer comp = null;
		LocalDateTime t1 = null;
		LocalDateTime t2 = null;
		Company co = null;

		if (result == null) {
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

				int coId = result.getInt("company_id");
				String coName = result.getString("company.name");
				co = new Company(coId, coName);
			}

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

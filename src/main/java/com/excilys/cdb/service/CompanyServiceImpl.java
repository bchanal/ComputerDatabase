package com.excilys.cdb.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDaoImpl;
import com.excilys.cdb.persistence.ConnectDao;

public enum CompanyServiceImpl implements CompanyService {

	instance;

	private final static Logger LOGGER = LoggerFactory
			.getLogger(CompanyServiceImpl.class);
	private CompanyDaoImpl cdao = CompanyDaoImpl.instance;

	private CompanyServiceImpl() {
	}

	@Override
	public List<Company> getAll() throws SQLException {
		return cdao.getAll();
	}

	@Override
	public Company getById(int id) throws SQLException {
		return cdao.getById(id);
	}

	@Override
	public void delete(int id) {
		Connection connect = null;
		try {
			connect = ConnectDao.getConnection();
			connect.setAutoCommit(false);
			cdao.delete(connect, id);
			connect.commit();
		} catch (SQLException e) {
			if (connect != null) {
				try {
					connect.rollback();
				} catch (SQLException e1) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
					throw new RuntimeException();
				}
			}
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		} finally {

			ConnectDao.close(connect);
		}
	}

}
package com.excilys.cdb.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.ConnectDao;
import com.excilys.cdb.persistence.impl.CompanyDaoImpl;
import com.excilys.cdb.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final static Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	@Autowired
	private CompanyDaoImpl cdao;
	@Autowired
	private ConnectDao codao;

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
			connect = codao.getConnection();
			codao.initTransaction();
			cdao.delete(connect, id);
			connect.commit();
		} catch (SQLException e) {
			if (connect != null) {
				try {
					connect.rollback();
				} catch (SQLException e1) {
					e.printStackTrace();
					LOGGER.error(e.getMessage());
					throw new ServiceException();
				}
			}
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();

		} finally {

			codao.closeTransaction();
		}
	}

}

package com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDaoImpl;

public enum CompanyServiceImpl implements CompanyService {
	
	instance;

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

}

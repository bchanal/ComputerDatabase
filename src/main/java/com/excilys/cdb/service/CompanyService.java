package com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;

public interface CompanyService {

	public List<Company> getAll() throws SQLException;

	public Company getById(int id) throws SQLException;

}

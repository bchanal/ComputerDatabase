package com.excilys.cdb.persistence;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;

public interface CompanyDao {

	public List<Company> getAll(boolean isTransaction) throws SQLException;

	public Company getById(int id, boolean isTransaction) throws SQLException;

}

package com.excilys.cdb.persistence;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;

public interface CompanyDAO {
	
	public List<Company> getAll() throws SQLException;
	
	public Company getById(int id) throws SQLException;	
	

}
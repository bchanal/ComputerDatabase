package com.excilys.cdb.service;

import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.model.Company;

public interface CompanyService {
/**
 * get all the companies
 * @return the list of all companies
 * @throws SQLException
 */
    public List<Company> getAll() throws SQLException;
/**
 * get a company by its id
 * @param id the id
 * @return the company
 * @throws SQLException
 */
    public Company getById(int id) throws SQLException;
/**
 * delete a company by id
 * @param id the id to delete
 */
    public void delete(int id);

}

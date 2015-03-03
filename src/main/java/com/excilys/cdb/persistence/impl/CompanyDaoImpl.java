package com.excilys.cdb.persistence.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.model.*;
import com.excilys.cdb.persistence.CompanyDao;
import com.excilys.cdb.persistence.ConnectDao;
import com.excilys.cdb.persistence.mapper.SpringCompanyMapper;

/**
 * CompanyDAO contains all the requests concerning companies
 * 
 * @author berangere
 *
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {
  @Autowired
  private JdbcTemplate        jdbcTemplate;

  @Autowired
  private SpringCompanyMapper cmap;
  @Autowired
  private ConnectDao          codao;

  private CompanyDaoImpl() {}

  /**
   * create a DAO, get the list of companies and return it in a ArrayList
   * 
   * @return listCompany : all the companies in a list
   */
  public List<Company> getAll() {
    List<Company> listCompany = new ArrayList<Company>();
    String query = "SELECT * FROM company";
    listCompany = jdbcTemplate.query(query, cmap);

    return listCompany;
  }

  /**
   * get a company by id.
   * 
   * @param id
   *            : the id from the company requested
   * @return comp the Computer requested
   * @throws SQLException
   */
  public Company getById(int id) throws SQLException {
    Company comp = null;

    String query = "SELECT * FROM company WHERE id= ?";
    comp = jdbcTemplate.queryForObject(query, new Object[] { id }, cmap);

    return comp;
  }

  /**
   * delete company and all the computers made by this company
   * 
   * @param connect
   *            the connection
   * @param id
   *            the id of the computer to delete
   */
  public synchronized void delete(Connection connect, int id) {

    String query = "DELETE FROM computer WHERE company_id= ?";
    String query2 = "DELETE FROM company WHERE id= ?";
    jdbcTemplate.update(query, id);
    jdbcTemplate.update(query2, id);

    System.out.println(id);

  }

}

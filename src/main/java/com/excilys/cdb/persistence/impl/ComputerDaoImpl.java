package com.excilys.cdb.persistence.impl;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.dto.DtoMapper;
import com.excilys.cdb.model.*;
import com.excilys.cdb.persistence.ConnectDao;
import com.excilys.cdb.persistence.util;
import com.excilys.cdb.persistence.mapper.SpringComputerMapper;

/**
 * ComputerDAO contains all the requests concerning the computers
 * 
 * @author berangere
 * 
 */
@Repository
public class ComputerDaoImpl {
@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SpringComputerMapper cmap;
	@Autowired
	private ConnectDao codao;

	public ComputerDaoImpl() {
	}

	/**
	 * get All computers and return them in a list
	 * 
	 * @return list the list of all computers
	 */
	public List<Computer> getAll() {

		List<Computer> list = null;
		String query = "SELECT * FROM computer";
		list = jdbcTemplate.query(query, cmap);

		return list;
	}

	/**
	 * get All computers and return them in a list
	 * 
	 * @param index
	 *            the first id requested
	 * @param nb
	 *            the number of computers we want
	 * @param name
	 *            the name searched
	 * @return list the list of computers
	 */
	public Page getAPage(int index, int nb, String name) {

		String query = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY computer.id LIMIT ? , ?;";
		String nameSearch = "%" + name + "%";

		List<Computer> list = this.jdbcTemplate.query(query, new Object[]{nameSearch, nameSearch, index, nb }, cmap);
		
		List<ComputerDto> listdto = new ArrayList<ComputerDto>();
		for (Computer c : list) {
			listdto.add(DtoMapper.computerToDto(c));
		}

		Page page = new Page(index, nb, listdto, 1000);
		return page;
	}

	/**
	 * get the number of computers for a request
	 * 
	 * @param name
	 *            the name searched
	 * @return size the number of results
	 */
	public int getNbComputers(String name) {

		String query = "SELECT COUNT(*) " + "FROM computer "
				+ "LEFT JOIN company ON computer.company_id = company.id "
				+ "WHERE computer.name LIKE ? OR company.name LIKE ?;";
		
		String nameC= "%"+name+"%";

		int size = jdbcTemplate.queryForObject(query, new Object[] {nameC, nameC},Integer.class);

		return size;
	}

	/**
	 * get a computer by id
	 * 
	 * @param id
	 *            the id to get
	 * @return comp the Computer requested
	 */
	public Computer getById(int id) {

		String query = "SELECT * FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id= ?";
		Computer comp = jdbcTemplate.queryForObject(query, new Object[] { id },
				cmap);

		return comp;
	}

	/**
	 * get a computer by name
	 * 
	 * @param name
	 *            the name searched
	 * @return comp the Computer requested
	 */
	public List<Computer> getByName(String name) {

		String query = "SELECT * " + "FROM computer "
				+ "LEFT JOIN company ON computer.company_id = company.id "
				+ "WHERE computer.name LIKE ? OR company.name LIKE ?;";
		String nameSearch = "%" + name + "%";

		List<Computer> comp = jdbcTemplate.query(query, new Object[] {
				nameSearch, nameSearch }, cmap);

		return comp;

	}

	/**
	 * create a computer in the db
	 * 
	 * @param name
	 *            the name of the computer
	 * @param dateTime
	 *            the date it was introduced (if exists, null otherwise)
	 * @param dateTimeFin
	 *            the date it was discontinued (if exists, null otherwise)
	 * @param comp
	 *            the id of the computer's company (if exists, 0 otherwise)
	 */
	public void create(String name, LocalDateTime dateTime,
			LocalDateTime dateTimeFin, int comp) {
		Timestamp date = null;
		Timestamp date2 = null;
		int company = 0;
		String query = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";

		if (dateTime != null) {
			date = util.getTimestamp(dateTime);
		}

		if (dateTimeFin != null) {
			date2 = util.getTimestamp(dateTimeFin);
		}

		if (comp != 0) {
			company = comp;
		}
		jdbcTemplate.update(query, name, date, date2, company);

	}

	/**
	 * delete a computer in the DB
	 * 
	 * @param id
	 *            the id of the computer to delete
	 */
	public synchronized void delete(int id) {

		String query = "DELETE FROM computer WHERE id= ?";
		jdbcTemplate.update(query, id);

	}

	/**
	 * update a computer in the DB
	 * 
	 * @param computer
	 *            the computer to update
	 */
	public synchronized void update(Computer computer) {

		String query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";

		String name = computer.getName();
		Timestamp date1 = null;
		Timestamp date2 = null;
		int company = 0;

		if (computer.getDateIntro() != null) {
			date1 = util.getTimestamp(computer.getDateIntro());
		}
		if (computer.getDateDiscontinued() != null) {
			date2 = util.getTimestamp(computer.getDateDiscontinued());
		}
		if (computer.getManufacturer().getId() != 0) {
			company = computer.getManufacturer().getId();
		}
		int id = computer.getId();

		jdbcTemplate.update(query, name, date1, date2, company, id);

	}

}

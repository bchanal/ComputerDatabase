package com.excilys.cdb.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.ComputerDaoImpl;

public class ComputerServiceImpl implements ComputerService {

	private ComputerDaoImpl cdao = ComputerDaoImpl.instance;

	public ComputerServiceImpl() {
	}

	@Override
	public void create(String name, LocalDateTime dateTime,
			LocalDateTime dateTimeFin, int comp) {
		cdao.create(name, dateTime, dateTimeFin, comp);
	}

	@Override
	public List<Computer> getAll() {
		return cdao.getAll();
	}

	// @Override
	// public Page getByPage(int index, int nbEntityByPage, String name) {
	// return cdao.getByPage(index, nbEntityByPage, name);
	// }

	@Override
	public void deleteById(int computerId) {
		cdao.delete(computerId);
	}

	@Override
	public void update(Computer computer) {
		cdao.update(computer);
	}

	@Override
	public Computer getByID(int computerId) {
		return cdao.getById(computerId);
	}

	@Override
	public List<Computer> getByName(String name) {
		return cdao.getByName(name);
	}

}

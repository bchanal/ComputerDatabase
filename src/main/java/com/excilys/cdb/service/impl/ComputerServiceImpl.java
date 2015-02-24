package com.excilys.cdb.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.impl.ComputerDaoImpl;
import com.excilys.cdb.service.ComputerService;

public enum ComputerServiceImpl implements ComputerService {
	
	instance;

	private ComputerDaoImpl cdao = ComputerDaoImpl.instance;

	private ComputerServiceImpl() {
	}

	@Override
	public void create(String name, LocalDateTime dateTime,	LocalDateTime dateTimeFin, int comp) {
		cdao.create(name, dateTime, dateTimeFin, comp);
	}

	@Override
	public List<Computer> getAll() {
		return ComputerDaoImpl.getAll();
	}

	 @Override
	 public Page getAPage(int index, int nb, String name) {
		 Page p = cdao.getAPage(index, nb, name);

		 return p;
	 }
	 

	@Override
	public void delete(int computerId) {
		ComputerDaoImpl.delete(computerId);
	}

	@Override
	public void update(Computer computer) {
		cdao.update(computer);
	}

	@Override
	public Computer getById(int computerId) {
		return cdao.getById(computerId);
	}

	@Override
	public List<Computer> getByName(String name) {
		return cdao.getByName(name);
	}

}

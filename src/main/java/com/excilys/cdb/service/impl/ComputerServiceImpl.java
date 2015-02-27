package com.excilys.cdb.service.impl;

import java.time.LocalDateTime;
import java.util.List;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.impl.ComputerDaoImpl;
import com.excilys.cdb.service.ComputerService;

@Service
public class ComputerServiceImpl implements ComputerService {

	@Autowired
	private ComputerDaoImpl cdao;

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

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Page getAPage(int index, int nb, String name) {

		Page p = null;

		p = cdao.getAPage(index, nb, name);
		int nbTotal = cdao.getNbComputers(name);
		p.setNbTotalComputer(nbTotal);

		return p;
	}

	@Override
	public void delete(int computerId) {
		cdao.delete(computerId);
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

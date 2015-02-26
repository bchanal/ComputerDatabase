package com.excilys.cdb.service.impl;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.impl.ComputerDaoImpl;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.persistence.ConnectDao;
@Service
public class ComputerServiceImpl implements ComputerService {

	@Autowired
	private ComputerDaoImpl cdao; 

	private ComputerServiceImpl() {
	}

	@Override
	public void create(String name, LocalDateTime dateTime,
			LocalDateTime dateTimeFin, int comp) {
		cdao.create(name, dateTime, dateTimeFin, comp);
	}

	@Override
	public List<Computer> getAll() {
		return ComputerDaoImpl.getAll();
	}

	@Override
	public Page getAPage(int index, int nb, String name) {

		Connection connect = null;
		Page p=null;

		try {
//			connect = ConnectDao.getConnection();
			ConnectDao.initTransaction();
			p = cdao.getAPage(index, nb, name);
			int nbTotal = cdao.getNbComputers(name);
			p.setNbTotalComputer(nbTotal);
			connect = ConnectDao.getConnection();
			connect.commit();

		} catch (Exception e) {

		} finally {
			ConnectDao.closeTransaction();
		}

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

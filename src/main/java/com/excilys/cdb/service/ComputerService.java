package com.excilys.cdb.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;

public interface ComputerService {

	public void create(String name, LocalDateTime dateTime,
			LocalDateTime dateTimeFin, int comp);

	public List<Computer> getAll();

	// public Page getByPage(int index, int nbEntityByPage, String name);

	public void deleteById(int computerId);

	public void update(Computer computer);

	public Computer getByID(int computerId);

	public List<Computer> getByName(String name);

}

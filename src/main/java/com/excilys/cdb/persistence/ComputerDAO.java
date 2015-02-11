package com.excilys.cdb.persistence;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.cdb.model.Computer;

public interface ComputerDAO {

	public List<Computer> getAll();

	public Computer getById(int id);

	public void create(String name, LocalDateTime dateTime,
			LocalDateTime dateTimeFin, int comp);

	public void delete(int id);

	public void update(Computer computer);

}

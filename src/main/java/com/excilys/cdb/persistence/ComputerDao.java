package com.excilys.cdb.persistence;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;

public interface ComputerDao {

    public List<Computer> getAll();

    public Computer getById(int id);

    public void create(String name, LocalDateTime dateTime, LocalDateTime dateTimeFin, int comp);
    
    public Page getAPage(int index, int nb, String name, int column);
    
    public int getNbComputers(String name);
    
    public List<Computer> getByName(String name);
    
    public void delete(int id);
    
    public void update(Computer computer);

}


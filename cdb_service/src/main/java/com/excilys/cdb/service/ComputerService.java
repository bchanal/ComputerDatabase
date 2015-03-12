package com.excilys.cdb.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.dto.PageDto;

public interface ComputerService {

    public void create(String name, LocalDateTime dateTime, LocalDateTime dateTimeFin, int comp);

    public List<Computer> getAll();

    public PageDto getAPage(int index, int nbEntityByPage, String name, int column);

    public void delete(int computerId);

    public void update(Computer computer);

    public Computer getById(int computerId);

    public List<Computer> getByName(String name);
    
    public void create(Computer c);


}
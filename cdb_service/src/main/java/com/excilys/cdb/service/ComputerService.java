package com.excilys.cdb.service;

import java.time.LocalDateTime;
import java.util.List;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.dto.PageDto;

public interface ComputerService {
/**
 * create a computer
 * @param name the name of the computer
 * @param dateTime the date it was introduced
 * @param dateTimeFin the date it was discontinued
 * @param comp the company
 */
    public void create(String name, LocalDateTime dateTime, LocalDateTime dateTimeFin, int comp);
/**
 * get all the computers
 * @return the list of all computers
 */
    public List<Computer> getAll();
/**
 * get a page of computers
 * @param index the index
 * @param nbEntityByPage the number of entity per pages to display
 * @param name the name searched
 * @param column the name of the column to sort the results
 * @return a page
 */
    public PageDto getAPage(int index, int nbEntityByPage, String name, int column);
/**
 * delete a computer by id
 * @param computerId the id to delete
 */
    public void delete(int computerId);
/**
 * update a computer
 * @param computer the computer
 */
    public void update(Computer computer);
/**
 * get a computer by id
 * @param computerId the id
 * @return a computer
 */
    public Computer getById(int computerId);
/**
 * get computers by name
 * @param name the name searched
 * @return a list of computers
 */
    public List<Computer> getByName(String name);
    /**
     * create a computer in the db
     * @param c the computer to add in the db
     */
    public void create(Computer c);


}

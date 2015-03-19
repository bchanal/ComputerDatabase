package com.excilys.cdb.web.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.jws.WebService;

import com.excilys.cdb.dto.PageDto;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.web.ComputerWS;

@WebService(endpointInterface = "com.excilys.cdb.web.ComputerWS")
public class ComputerWSImpl implements ComputerWS {

    private ComputerService cserv;
    
    public ComputerWSImpl(ComputerService computerService){
        this.cserv = computerService;
    }

    /**
     * create a computer
     * @param name the name
     * @param dateTime the date it was introduced
     * @param dateTimeFin the date it was discontinued
     * @param comp the company
     */
    public void create(String name, LocalDateTime dateTime, LocalDateTime dateTimeFin, int comp) {
        cserv.create(name, dateTime, dateTimeFin, comp);
    }

    /**
     * get all the computers
     * @return the list of all computers
     */
    public List<Computer> getAll() {
        return cserv.getAll();
    }

    /**
     * get a page of computers
     * @param index the index
     * @param nbEntityByPage the number of entity per pages to display
     * @param name the name searched
     * @param column the name of the column to sort the results
     * @return a page
     */
    public PageDto getAPage(int index, int nbEntityByPage, String name, int column) {
        return cserv.getAPage(index, nbEntityByPage, name, column);
    }

    /**
     * delete a computer by id
     * @param computerId the id to delete
     */
    public void delete(int computerId) {
        cserv.delete(computerId);
    }

    /**
     * update a computer
     * @param computer the computer
     */
    public void update(Computer computer) {
        cserv.update(computer);
    }

    /**
     * get a computer by id
     * @param computerId the id
     * @return a computer
     */
    public Computer getById(int computerId) {
        return cserv.getById(computerId);
    }

    /**
     * get computers by name
     * @param name the name searched
     * @return a list of computers
     */
    public List<Computer> getByName(String name) {
        return cserv.getByName(name);
    }

    /**
     * create a computer in the db
     * @param c the computer to add in the db
     */
    public void create(Computer c) {
        cserv.create(c);
    }

}

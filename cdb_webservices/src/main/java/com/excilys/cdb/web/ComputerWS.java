package com.excilys.cdb.web;

import java.time.LocalDateTime;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.excilys.cdb.dto.PageDto;
import com.excilys.cdb.model.Computer;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ComputerWS {
    @WebMethod 
    public String getHelloWorldAsString(String name);

    @WebMethod 
    public void create(String name, LocalDateTime dateTime, LocalDateTime dateTimeFin, int comp);

    @WebMethod 
    public List<Computer> getAll();

    @WebMethod 
    public PageDto getAPage(int index, int nbEntityByPage, String name, int column);

    @WebMethod 
    public void delete(int computerId);

    @WebMethod 
    public void update(Computer computer);

    @WebMethod 
    public Computer getById(int computerId);

    @WebMethod 
    public List<Computer> getByName(String name);

    @WebMethod 
    public void create(Computer c);

}

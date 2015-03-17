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
@SOAPBinding(style = Style.DOCUMENT)
public interface ComputerWS {
    @WebMethod
    String getHelloWorldAsString(String name);

    public void create(String name, LocalDateTime dateTime, LocalDateTime dateTimeFin, int comp);

    public List<Computer> getAll();

    public PageDto getAPage(int index, int nbEntityByPage, String name, int column);

    public void delete(int computerId);

    public void update(Computer computer);

    public Computer getById(int computerId);

    public List<Computer> getByName(String name);

    public void create(Computer c);

}

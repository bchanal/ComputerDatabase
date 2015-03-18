package com.excilys.cdb.web;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.excilys.cdb.model.Company;

@WebService
@SOAPBinding(style = Style.RPC)
public interface CompanyWS {

    @WebMethod 
    public List<Company> getAll();

    @WebMethod 
    public void delete(int id);

    @WebMethod 
    public Company getById(int computerId);

}

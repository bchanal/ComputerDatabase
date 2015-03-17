package com.excilys.cdb.web;

import java.util.List;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import com.excilys.cdb.model.Company;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface CompanyWS {

    public List<Company> getAll();

    public void delete(int id);

    public Company getById(int computerId);

}

package com.excilys.cdb.web.impl;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebService;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.web.CompanyWS;

@WebService(endpointInterface = "com.excilys.cdb.web.CompanyWS")
public class CompanyWSImpl implements CompanyWS {

    private CompanyService cserv;
    
    public CompanyWSImpl(CompanyService companyService){
        this.cserv = companyService;
    }
    

    @Override
    public List<Company> getAll() throws SQLException {
        return cserv.getAll();
    }

    @Override
    public void delete(int id) {
        cserv.delete(id);
    }

    @Override
    public Company getById(int computerId) throws SQLException{
        return cserv.getById(computerId);
    }

}

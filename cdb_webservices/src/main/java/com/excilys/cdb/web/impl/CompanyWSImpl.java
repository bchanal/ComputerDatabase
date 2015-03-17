package com.excilys.cdb.web.impl;

import java.util.List;

import javax.jws.WebService;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.web.CompanyWS;

@WebService(endpointInterface = "com.excilys.cdb.web.CompanyWS")
public class CompanyWSImpl implements CompanyWS {

    private CompanyServiceImpl cserv;

    @Override
    public List<Company> getAll() {
        return cserv.getAll();
    }

    @Override
    public void delete(int id) {
        cserv.delete(id);
    }

    @Override
    public Company getById(int computerId) {
        return cserv.getById(computerId);
    }

}

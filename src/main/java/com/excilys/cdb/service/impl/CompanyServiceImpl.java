package com.excilys.cdb.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.exception.ConnectionException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.repository.CompanyRepository;
import com.excilys.cdb.persistence.repository.ComputerRepository;
import com.excilys.cdb.service.CompanyService;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyrep;
    
    @Autowired
    private ComputerRepository computerrep;

    public CompanyServiceImpl() {}

    @Override
    public List<Company> getAll() throws SQLException {
        return (List<Company>) companyrep.findAll();
    }

    @Override
    public Company getById(int id) throws SQLException {
        return companyrep.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = ConnectionException.class)
    public void delete(int id) {

        computerrep.deleteByCompany(id);
        companyrep.delete(id);
        
    }

}

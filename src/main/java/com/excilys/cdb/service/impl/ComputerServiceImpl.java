package com.excilys.cdb.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.dto.DtoMapper;
import com.excilys.cdb.exception.ConnectionException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.repository.CompanyRepository;
import com.excilys.cdb.persistence.repository.ComputerRepository;
import com.excilys.cdb.service.ComputerService;

@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    public ComputerRepository computerRep;
    @Autowired
    public CompanyRepository companyRep;


    @Autowired
    public DtoMapper          dtomap;

    public ComputerServiceImpl() {}

    @Override
    public void create(String name, LocalDateTime dateTime, LocalDateTime dateTimeFin, int comp) {
        Company company = companyRep.findOne(comp);
        Computer c = new Computer(name, dateTime, dateTimeFin, company);
        computerRep.save(c);
    }

    @Override
    public void create(Computer c) {

        computerRep.save(c);

    }

    @Override
    public List<Computer> getAll() {
        return (List<Computer>) computerRep.findAll();
    }

    @Transactional(rollbackFor = ConnectionException.class)
    public Page getAPage(int index, int nb, String name, int column) {

        Page p = null;
        int nbTotal = 0;

        List<Computer> list = computerRep.getAPage(index, nb, name, column);
        List<ComputerDto> listDto = new ArrayList<ComputerDto>();
        for (Computer c : list) {
            listDto.add(dtomap.computerToDto(c));
        }

        p = new Page(index, nb, listDto, nbTotal);
        nbTotal = computerRep.getCountComputers(name);

        p.setNbTotalComputer(nbTotal);

        return p;
    }

    @Override
    public void delete(int computerId) {
        computerRep.delete(computerId);

    }

    @Override
    public void update(Computer computer) {
        computerRep.save(computer);
    }

    @Override
    public Computer getById(int computerId) {
        return computerRep.findOne(computerId);
    }

    @Override
    public List<Computer> getByName(String name) {
        return computerRep.findByNameContainingOrCompanyNameContaining(name, name);
    }

}

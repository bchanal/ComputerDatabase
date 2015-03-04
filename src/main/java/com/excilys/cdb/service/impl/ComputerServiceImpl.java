package com.excilys.cdb.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.exception.ConnectionException;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.ComputerDao;
import com.excilys.cdb.service.ComputerService;

@Service
public class ComputerServiceImpl implements ComputerService {

    @Autowired
    public ComputerDao cdao;

    public ComputerServiceImpl() {}

    @Override
    public void create(String name, LocalDateTime dateTime, LocalDateTime dateTimeFin, int comp) {
        cdao.create(name, dateTime, dateTimeFin, comp);
    }

    public void create(Computer c) {
        System.out.println(c.toString());
        int idcompany;
        if (c.getManufacturer() == null)
            idcompany = 0;
        else
            idcompany = c.getManufacturer().getId();
        cdao.create(c.getName(), c.getDateIntro(), c.getDateDiscontinued(), idcompany);
    }

    @Override
    public List<Computer> getAll() {
        return cdao.getAll();
    }

   
    @Transactional(rollbackFor = ConnectionException.class)
    public Page getAPage(int index, int nb, String name, int column) {

        Page p = null;
        int nbTotal = 0;
        try {
            p = cdao.getAPage(index, nb, name, column);
            nbTotal = cdao.getNbComputers(name);
        } catch (ConnectionException e) {
            throw new ServiceException(e);
        }
        p.setNbTotalComputer(nbTotal);

        return p;
    }

    @Override
    public void delete(int computerId) {
        try {
            cdao.delete(computerId);
        } catch (ConnectionException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void update(Computer computer) {
        cdao.update(computer);
    }

    @Override
    public Computer getById(int computerId) {
        return cdao.getById(computerId);
    }

    @Override
    public List<Computer> getByName(String name) {
        return cdao.getByName(name);
    }

}

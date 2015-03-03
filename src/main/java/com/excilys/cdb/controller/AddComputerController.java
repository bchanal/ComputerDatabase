package com.excilys.cdb.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.dto.DtoMapper;
import com.excilys.cdb.model.Company;

import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.servlets.AddComputer;

@Controller
@RequestMapping("/add-computer")
public class AddComputerController {

  private final static Logger        LOGGER   = LoggerFactory.getLogger(AddComputer.class);

  @Autowired
  private ComputerServiceImpl        ctdao;
  @Autowired
  private CompanyServiceImpl         cndao;
  @Autowired
  private DtoMapper                  dtoMap;

  @RequestMapping(method = RequestMethod.GET)
  protected String doGet(ModelMap map) throws SQLException {

    List<Company> listCompanies = cndao.getAll();

    ComputerDto computerDto = new ComputerDto();
    map.addAttribute("computer", computerDto);
    map.addAttribute("listCompanies", listCompanies);

    return "addComputer";
  }

  @RequestMapping(method = RequestMethod.POST)
  protected String doPost(@ModelAttribute("computer") @Valid ComputerDto compDto,
      BindingResult res, ModelMap map, @RequestParam("companyId") int companyId)
      throws SQLException {

    if (res.hasErrors()) {
      List<Company> listcompanies = cndao.getAll();
      map.addAttribute("listcompanies", listcompanies);
      LOGGER.info("error, computer not added");
      return "addComputer";
    }
    Company company = cndao.getById(companyId);
    compDto.setCompany(company);
    
    System.out.println(compDto.toString());
    ctdao.create(dtoMap.dtoToComputer(compDto));
    LOGGER.info("add the computer");

    return "redirect:/dashboard";
  }

}

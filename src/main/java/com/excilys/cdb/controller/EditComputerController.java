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
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;

@Controller
@RequestMapping("/edit-computer")
public class EditComputerController {

    private final static Logger LOGGER = LoggerFactory.getLogger(EditComputerController.class);

    @Autowired
    private ComputerServiceImpl ctdao;
    @Autowired
    private CompanyServiceImpl  cndao;
    @Autowired
    private DtoMapper           dtoMap;

    @RequestMapping(method = RequestMethod.GET)
    protected String doGet(@RequestParam("id") int idEdit, ModelMap map) throws SQLException {

        List<Company> listCompanies = cndao.getAll();
        map.addAttribute("listCompanies", listCompanies);
        map.addAttribute("idEdit", idEdit);

        Computer computer = ctdao.getById(idEdit);
        ComputerDto cdto = dtoMap.computerToDto(computer);

        map.addAttribute("computer", cdto);

        return "editComputer";
    }

    @RequestMapping(method = RequestMethod.POST)
    protected String doPost(ModelMap map, @ModelAttribute("computer") @Valid ComputerDto compDto,
            BindingResult res, @RequestParam("companyId") int companyId,
            @RequestParam("lang") String lang) throws SQLException {

        if (res.hasErrors()) {
            List<Company> companies = cndao.getAll();
            map.addAttribute("companies", companies);
            LOGGER.info("error ! try again, the computer could not have been modified");
            return "editComputer";
        }
        Company company = cndao.getById(companyId);
        compDto.setCompany(company);

        Computer comp = dtoMap.dtoToComputer(compDto);
        this.ctdao.update(comp);
        LOGGER.info("computer modified");
        return "redirect:/dashboard";
    }

}

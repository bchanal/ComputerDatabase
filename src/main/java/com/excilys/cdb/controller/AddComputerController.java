package com.excilys.cdb.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.dto.DtoMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.servlets.AddComputer;
import com.excilys.cdb.validation.DtoValidation;

@Controller
@RequestMapping("/add-computer")
public class AddComputerController {

	private final static Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);

	private DtoValidation<ComputerDto> dtovalid = new DtoValidation<ComputerDto>();
	@Autowired
	private ComputerServiceImpl ctdao;
	@Autowired
	private CompanyServiceImpl cndao;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(ModelMap map) throws SQLException {

		List<Company> listCompanies = cndao.getAll();
		map.addAttribute("listCompanies", listCompanies);

		return "addComputer";
	}
	@RequestMapping(method = RequestMethod.POST)
	protected String doPost(@RequestParam("computerName") String name, 
			@RequestParam("introduced") String introduced, 
			@RequestParam("discontinued") String discontinued,
			@RequestParam("companyId") int companyId, ModelMap map){
		

		Company comp = null;
		try {
			comp = cndao.getById(companyId);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();
		}

		ComputerDto cdto = new ComputerDto(0, name, introduced, discontinued,
				comp);

		List<String> validationErrors = new ArrayList<String>();
		validationErrors = dtovalid.validate(cdto);

		if (validationErrors.size() == 0) {
			Computer c = DtoMapper.dtoToComputer(cdto);
			ctdao.create(c.getName(), c.getDateIntro(),
					c.getDateDiscontinued(), c.getManufacturer().getId());
			LOGGER.info("Computer added with success, redirecting to the Dashboard");
		} else {
			LOGGER.info("Wrong input, redirecting to the view");
		}
		return "redirect:/dashboard";
	}

}

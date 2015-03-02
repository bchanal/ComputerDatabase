package com.excilys.cdb.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.dto.DtoMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.validation.DtoValidation;

@Controller
@RequestMapping("/edit-computer")
public class EditComputerController {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(EditComputerController.class);

	private int idEdit;
	@Autowired
	private ComputerServiceImpl ctdao;
	@Autowired
	private CompanyServiceImpl cndao;

	@RequestMapping(method = RequestMethod.GET)
	protected String doGet(@RequestParam("id") int idEdit, ModelMap map)
			throws SQLException {

		List<Company> listCompanies = cndao.getAll();
		map.addAttribute("listCompanies", listCompanies);
		map.addAttribute("idEdit", idEdit);

		Computer computer = ctdao.getById(idEdit);
		ComputerDto cdto = DtoMapper.computerToDto(computer);

		map.addAttribute("computer", cdto);

		return "editComputer";
	}

	@RequestMapping(method = RequestMethod.POST)

	protected String doPost(ModelMap map,
			@RequestParam("computerName") String name,
			@RequestParam("introduced") String introduced,
			@RequestParam("discontinued") String discontinued,
			@RequestParam("companyId") int companyId) throws SQLException {

		DtoValidation<ComputerDto> dtoval = new DtoValidation<ComputerDto>();

		Company comp = null;
		if (companyId != 0) {
			comp = cndao.getById(companyId);
		}

		ComputerDto cdto = new ComputerDto(idEdit, name, introduced,
				discontinued, comp);

		List<String> validationErrors = new ArrayList<>();
		validationErrors = dtoval.validate(cdto);

		if (validationErrors.size() == 0) {
			Computer c = DtoMapper.dtoToComputer(cdto);
			ctdao.update(c);
			LOGGER.info("Computer added with success, redirecting to the Dashboard");
			return "redirect:/dashboard";

		} else {
			LOGGER.info("Wrong input, redirecting to the view");
			return "editComputer";

		}
	}

}

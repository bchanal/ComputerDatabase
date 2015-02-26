package com.excilys.cdb.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.dto.DtoMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.validation.DtoValidation;

@WebServlet("/add-computer")
public class AddComputer extends AbstractSpringHttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/static/views/addComputer.jsp";
	private static final String ATT_LISTCOMPANIES = "listCompanies";

	private final static Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);
	
	@Autowired
	ComputerServiceImpl ctdao;
	@Autowired
	CompanyServiceImpl cndao;

	public AddComputer() {
		super();
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		List<Company> listCompanies = getCompanies(request);
		request.setAttribute(ATT_LISTCOMPANIES, listCompanies);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		createComputer(request, response);

//		this.getServletContext().getRequestDispatcher("/static/views/dashboard.jsp").forward(request, response);

	}

	private void createComputer(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException{
		
		final String name = request.getParameter("computerName");
		final String introduced = request.getParameter("introduced");
		final String discontinued = request.getParameter("discontinued");
		final int companyId = Integer.parseInt(request.getParameter("companyId"));

		Company comp = null;
		try {
			comp = cndao.getById(companyId);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();
		}

		ComputerDto cdto = new ComputerDto(0, name, introduced, discontinued,comp);

		List<String> validationErrors = new ArrayList<String>();
		validationErrors = DtoValidation.validate(cdto);

		if (validationErrors.size() == 0) {
			Computer c = DtoMapper.dtoToComputer(cdto);
			ctdao.create(c.getName(), c.getDateIntro(), c.getDateDiscontinued(), c.getManufacturer().getId());
			LOGGER.info("Computer added with success, redirecting to the Dashboard");
			response.sendRedirect(request.getContextPath() + "/dashboard");
		} else {
			LOGGER.info("Wrong input, redirecting to the view");
			request.setAttribute("validationErrors", validationErrors);
			doGet(request, response);
		}

	}

	private List<Company> getCompanies(HttpServletRequest request) {
			try {
				return cndao.getAll();
			} catch (SQLException e) {
				e.printStackTrace();
				LOGGER.error(e.getMessage());
				throw new RuntimeException();
			}	

	}
}

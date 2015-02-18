package com.excilys.cdb.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.cli.util;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.CompanyDaoImpl;
import com.excilys.cdb.persistence.ComputerDaoImpl;

@WebServlet("/add-computer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/static/views/addComputer.jsp";
	private static final String ATT_LISTCOMPANIES = "listCompanies";

	private final static Logger LOGGER = LoggerFactory
			.getLogger(AddComputer.class);

	public AddComputer() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Company> listCompanies = getCompanies(request);
		request.setAttribute(ATT_LISTCOMPANIES, listCompanies);

		this.getServletContext().getRequestDispatcher(VUE)
				.forward(request, response);

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Company> listCompanies = getCompanies(request);
		request.setAttribute(ATT_LISTCOMPANIES, listCompanies);

		createComputer(request, response);

		this.getServletContext().getRequestDispatcher(VUE)
				.forward(request, response);

	}

	private void createComputer(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		LocalDateTime dateIntro;
		LocalDateTime dateDisc;
		int companyId;

		String name = request.getParameter("computerName");
		if (request.getParameter("introduced") != null
				&& !request.getParameter("introduced").equals("null")
				&& request.getParameter("introduced") != "") {
			dateIntro = util.checkDate(request.getParameter("introduced"));
		} else {
			dateIntro = null;
		}
		if (request.getParameter("discontinued") != null
				&& !request.getParameter("discontinued").equals("null")
				&& request.getParameter("discontinued") != "") {
			dateDisc = util.checkDate(request.getParameter("discontinued"));
		} else {
			dateDisc = null;
		}
		if (request.getParameter("companyId") != null
				&& !request.getParameter("companyId").equals("null")
				&& request.getParameter("companyId") != "") {
			companyId = Integer.parseInt(request.getParameter("companyId"));
		} else {
			companyId = 0;
		}

		// Company company = CompanyDAOImpl.instance.getById(companyId);

		// try {
		ComputerDaoImpl.instance.create(name, dateIntro, dateDisc, companyId);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// LOGGER.error(e.getMessage());
		// throw new RuntimeException();
		// }
	}

	private List<Company> getCompanies(HttpServletRequest request) {
		return CompanyDaoImpl.instance.getAll();

	}
}

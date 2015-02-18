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
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.CompanyDaoImpl;
import com.excilys.cdb.persistence.ComputerDaoImpl;

/**
 * Servlet implementation class editComputer
 */
@WebServlet("/edit-computer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE = "/static/views/editComputer.jsp";
	private static final String ATT_LISTCOMPANIES = "listCompanies";
	private static final String ATT_COMPUTER = "computer";
	private static final String ATT_IDEDIT = "idEdit";

	private final static Logger LOGGER = LoggerFactory
			.getLogger(EditComputer.class);

	private int idEdit;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputer() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Company> listCompanies = getCompanies(request);
		request.setAttribute(ATT_LISTCOMPANIES, listCompanies);

		this.idEdit = Integer.parseInt(request.getParameter("id"));
		request.setAttribute(ATT_IDEDIT, idEdit);

		Computer computer = ComputerDaoImpl.instance.getById(idEdit);

		request.setAttribute(ATT_COMPUTER, computer);

		this.getServletContext().getRequestDispatcher(VUE)
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Company> listCompanies = getCompanies(request);
		request.setAttribute(ATT_LISTCOMPANIES, listCompanies);

		updateComputer(request, response);

		this.getServletContext().getRequestDispatcher(VUE)
				.forward(request, response);
	}

	private List<Company> getCompanies(HttpServletRequest request) {
		return CompanyDaoImpl.instance.getAll();
	}

	private void updateComputer(HttpServletRequest request,
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

		Company company = null;
		try {
			company = CompanyDaoImpl.instance.getById(companyId);
		} catch (SQLException e) {
			e.printStackTrace();
			LOGGER.error(e.getMessage());
			throw new RuntimeException();
		}
		Computer computer = new Computer(idEdit, name, dateIntro, dateDisc,
				company);

		// try {
		ComputerDaoImpl.instance.update(computer);
		// } catch (SQLException e) {
		// e.printStackTrace();
		// LOGGER.error(e.getMessage());
		// throw new RuntimeException();
		// }
	}

}

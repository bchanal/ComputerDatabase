package com.excilys.cdb.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDAOImpl;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String ATT_MESSAGES = "listComputers";
	public static final String ATT_NBPAGES = "nbTotalComputer";
	public static final String VUE = "/static/views/dashboard.jsp";

	public Dashboard() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Computer> listComputers = null;

		listComputers = getComputers(request);
		int nbComputers = getNbPages(request);

		/* Enregistrement de la liste des ordis dans l'objet requête */
		request.setAttribute(ATT_MESSAGES, listComputers);
		request.setAttribute(ATT_NBPAGES, nbComputers);

		/* Transmission vers la page en charge de l'affichage des résultats */
		this.getServletContext().getRequestDispatcher(VUE)
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	protected List<Computer> getComputers(HttpServletRequest request) {

		List<Computer> list = new ArrayList<Computer>();
		list = ComputerDAOImpl.instance.getAPage(0, 100);

		return list;
	}
	
	protected int getNbPages(HttpServletRequest request){
		
		return ComputerDAOImpl.instance.getNbComputers();
	}

}

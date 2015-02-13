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
	public static final String VUE = "/WEB-INF/Dashboard.jsp";

	public Dashboard() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<String> listComputers = null;

		// listComputers = getComputers( request );

		/* Enregistrement de la liste des ordis dans l'objet requête */
		request.setAttribute(ATT_MESSAGES, listComputers);

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
		System.out.println("1");
		List<String> listComputers = null;
		System.out.println("2");

		// listComputers = getComputers( request );
		System.out.println("3");

		/* Enregistrement de la liste des ordis dans l'objet requête */
		request.setAttribute(ATT_MESSAGES, listComputers);

		/* Transmission vers la page en charge de l'affichage des résultats */
		this.getServletContext().getRequestDispatcher(VUE)
				.forward(request, response);
	}

	protected List<String> getComputers(HttpServletRequest request) {

		List<String> messages = new ArrayList<String>();

		List<Computer> list = new ArrayList<Computer>();
		list = ComputerDAOImpl.instance.getAPage(0, 100);
		for (Computer comp : list) {
			messages.add(comp.toString());
		}

		return messages;

	}

}

package com.excilys.cdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.impl.ComputerServiceImpl;


/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ATT_SEARCH = "search";
	private static final String ATT_NUMPAGE = "numPage";
	private static final String ATT_PAGE = "page";
	private static final String VUE = "/static/views/dashboard.jsp";

	public Dashboard() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nameSearched = "";
		int nbPP = 50; // a definir par défaut dans la page
		int numPage = 1;
		//TODO mettre dans static final
		if (request.getParameter("nbPerPage") != null) {
			nbPP = Integer.parseInt(request.getParameter("nbPerPage"));
		}

		if (request.getParameter("page") != null) {
			numPage = Integer.parseInt(request.getParameter("page"));
		}

		if (request.getParameter("search") != null) {
			nameSearched = request.getParameter("search");
		}

		Page page = getAPage(numPage, nbPP, nameSearched);

		request.setAttribute(ATT_NUMPAGE, numPage);
		request.setAttribute(ATT_SEARCH, nameSearched);
		request.setAttribute(ATT_PAGE, page);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}
	
	protected Page getAPage(int numPage, int nbPP, String search) {
		
		int index = (numPage - 1) * nbPP;
		Page page = ComputerServiceImpl.instance.getAPage(index, nbPP, search);

		return page;
	}

}

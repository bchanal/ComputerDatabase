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
	private static final String ATT_MESSAGES = "listComputers";
	private static final String ATT_NBPAGES = "nbTotalComputer";
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

		List<Computer> listComputers = null;
		String nameSearched;
		
		int nbComputers = getNbComputers(request);
		request.setAttribute(ATT_NBPAGES, nbComputers);
		
		if(request.getParameter("search") != null){
			nameSearched = request.getParameter("search");
			listComputers = ComputerDAOImpl.instance.getByName(nameSearched);

		}
		else{
			listComputers = getComputers(request);	
		}
	
		request.setAttribute(ATT_MESSAGES, listComputers);

		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String ids = request.getParameter("selection");
		String[] tabId = ids.split(",");
		int id;
		
		for( String idStr : tabId){
			id = Integer.parseInt(idStr);
			ComputerDAOImpl.instance.delete(id);
		}

	}

	protected List<Computer> getComputers(HttpServletRequest request) {

		List<Computer> list = new ArrayList<Computer>();
		list = ComputerDAOImpl.instance.getAPage(0, 100);
		return list;
	}
	
	protected int getNbComputers(HttpServletRequest request){
		
		return ComputerDAOImpl.instance.getNbComputers();
	}

}

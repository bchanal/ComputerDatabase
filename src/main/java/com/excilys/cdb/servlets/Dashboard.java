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
import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.dto.DtoMapper;

/**
 * Servlet implementation class Dashboard
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID	= 1L;
	private static final String ATT_MESSAGES 	= "listComputers";
	private static final String ATT_NBCOMPUTERS = "nbTotalComputer";
	private static final String ATT_NBPERPAGE 	= "nbPerPage";
	private static final String ATT_SEARCH 		= "search";

	private static final String ATT_PAGE 		= "page";
	private static final String ATT_NBPAGES 	= "nbTotalPages";
	private static final String VUE 			= "/static/views/dashboard.jsp";

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
		List<ComputerDto> listComputersDto = new ArrayList<ComputerDto>();
		String nameSearched=null;
		int nbPP;
		int numPage;
		
		
		int nbComputers = getNbComputers(request); // trouver comment le faire en fonction de la requete
		request.setAttribute(ATT_NBCOMPUTERS, nbComputers);
		
		if(!request.getParameter("nbPerPage").equals(null)){
			nbPP = Integer.parseInt(request.getParameter("nbPerPage"));
		} else {
			nbPP = 50;
		}
	
		if(!request.getParameter("page").equals(null)){
			numPage = Integer.parseInt(request.getParameter("page"));
		} else {
			numPage = 1;
		}
		
		if(request.getParameter("search") != null){
			nameSearched = request.getParameter("search");
			List<Computer> listResults = ComputerDAOImpl.instance.getByName(nameSearched);
			listComputers = setPage(listResults, numPage, nbPP);
		}
		else{
			listComputers = getComputers(request, numPage, nbPP);	
		}
		
		for (Computer c : listComputers) {
			ComputerDto cd= DtoMapper.computerToDto(c);	
			listComputersDto.add(cd);
		}
	
		request.setAttribute(ATT_NBPAGES, (int)Math.ceil((double)nbComputers/(double)nbPP));
		request.setAttribute(ATT_MESSAGES, listComputersDto);
		//request.setAttribute(ATT_MESSAGES, listComputers);
		request.setAttribute(ATT_PAGE, numPage);
		request.setAttribute(ATT_NBPERPAGE, nbPP);
		request.setAttribute(ATT_SEARCH, nameSearched);


		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String ids	 = request.getParameter("selection");
		String[] tabId = ids.split(",");
		int id;
		
		for( String idStr : tabId){
			id = Integer.parseInt(idStr);
			ComputerDAOImpl.instance.delete(id);
		}
	}

	protected List<Computer> getComputers(HttpServletRequest request, int numPage, int nbPP) {

		List<Computer> list = new ArrayList<Computer>();
		int index = (numPage-1)*nbPP;
		list = ComputerDAOImpl.instance.getAPage(index, nbPP);
		return list;
	}
	
	protected List<Computer> setPage(List<Computer> list, int numPage, int nbPP){
		
		List<Computer> res = new ArrayList<Computer>();
		int index = (numPage-1)*nbPP;
		res = ComputerDAOImpl.instance.getAPage(index, nbPP);
		return res;
		
	}
	
	protected int getNbComputers(HttpServletRequest request){
		
		return ComputerDAOImpl.instance.getNbComputers();
	}

}

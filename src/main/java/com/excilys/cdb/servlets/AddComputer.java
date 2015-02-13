package com.excilys.cdb.servlets;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.CompanyDAOImpl;
import com.excilys.cdb.persistence.ComputerDAOImpl;

/**
 * Servlet implementation class AddComputer
 */
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fromPage= request.getParameter("fromPage");
		if ( fromPage!= null ){
			if ( fromPage.equals("page1") )
				traiterBean(request, response);							
		}					
	}
	private void traiterBean(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		try{	
			
			String name	= request.getParameter("computerName");
			LocalDateTime dateIntro = LocalDateTime.parse(request.getParameter("introduced"), formatter);
			LocalDateTime dateDisc = LocalDateTime.parse(request.getParameter("discontinued"), formatter);
			int companyId = Integer.parseInt(request.getParameter("companyId"));
			//Company company = CompanyDAOImpl.instance.getById(companyId);
			
			ComputerDAOImpl.instance.create(name, dateIntro, dateDisc, companyId);
 
			//Computer computer = new Computer(name, dateIntro, dateDisc, company);
			
	
			getServletContext().getRequestDispatcher( "page2.jsp").forward(request,response);														
		}catch (Exception e) {
			Exception exception =  new Exception("MyServlet:traiterBean() = Error : "+ e.getClass().getName() + " Message = " + e.getMessage()); 
			}
	}

}

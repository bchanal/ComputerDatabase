package com.excilys.cdb.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 * Servlet implementation class AbstractSpringHttpServlet
 */
@WebServlet("/AbstractSpringHttpServlet")
public abstract class AbstractSpringHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

}

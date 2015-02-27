package com.excilys.cdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.service.impl.ComputerServiceImpl;

/**
 * Servlet implementation class DeleteCommputer
 */
@WebServlet("/delete-computer")
public class DeleteComputer extends AbstractSpringHttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ComputerServiceImpl ctdao;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteComputer() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ids = request.getParameter("selection");
		String[] tabId = ids.split(",");
		int id;

		for (String idStr : tabId) {
			id = Integer.parseInt(idStr);
			ctdao.delete(id);
		}
		response.sendRedirect("dashboard");
	}

}

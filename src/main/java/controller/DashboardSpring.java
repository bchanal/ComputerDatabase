package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.servlets.AbstractSpringHttpServlet;


/**
 * Servlet implementation class Dashboard
 */
@Controller
public class DashboardSpring extends AbstractSpringHttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ATT_SEARCH = "search";
	private static final String ATT_NUMPAGE = "numPage";
	private static final String ATT_PAGE = "page";
	private static final String VUE = "/static/views/dashboard.jsp";
	
	@Autowired
	private ComputerServiceImpl ctdao;
	@Autowired
	private CompanyServiceImpl cndao;

	public DashboardSpring() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@RequestMapping(value="/dashboardTest", method=RequestMethod.GET)
//	protected void doGet(HttpServletRequest request,
//			HttpServletResponse response) throws ServletException, IOException {

	protected String dashboard(ModelMap map) {
		
	
		String nameSearched = "";
		int nbPP = 50; // a definir par défaut dans la page
		int numPage = 1;
		Page page = getAPage(numPage, nbPP, nameSearched);
		
		map.addAttribute("page", page);
		map.addAttribute("search", nameSearched);
		map.addAttribute("numPage", numPage);
		
		return "display dashboard";

//		if (request.getParameter("nbPerPage") != null) {
//			nbPP = Integer.parseInt(request.getParameter("nbPerPage"));
//		}
//
//		if (request.getParameter("page") != null) {
//			numPage = Integer.parseInt(request.getParameter("page"));
//		}
//
//		if (request.getParameter("search") != null) {
//			nameSearched = request.getParameter("search");
//		}
//
//		Page page = getAPage(numPage, nbPP, nameSearched);
//
//		request.setAttribute(ATT_NUMPAGE, numPage);
//		request.setAttribute(ATT_SEARCH, nameSearched);
//		request.setAttribute(ATT_PAGE, page);

//		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);

	}
	
	protected Page getAPage(int numPage, int nbPP, String search) {
		
		int index = (numPage - 1) * nbPP;
		Page page = ctdao.getAPage(index, nbPP, search);

		return page;
	}

}

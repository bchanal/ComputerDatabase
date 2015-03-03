package com.excilys.cdb.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.dto.DtoMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;
import com.excilys.cdb.validation.DtoValidation;

/**
 * Servlet implementation class editComputer
 */
@WebServlet("/edit-computer2")
public class EditComputer extends AbstractSpringHttpServlet {
  private static final long   serialVersionUID  = 1L;
  private static final String VUE               = "/static/views/editComputer.jsp";
  private static final String ATT_LISTCOMPANIES = "listCompanies";
  private static final String ATT_COMPUTER      = "computer";
  private static final String ATT_IDEDIT        = "idEdit";

  private final static Logger LOGGER            = LoggerFactory.getLogger(EditComputer.class);

  private int                 idEdit;
  @Autowired
  private ComputerServiceImpl ctdao;
  @Autowired
  private CompanyServiceImpl  cndao;
  @Autowired
  private DtoMapper dtoMap;

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
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    List<Company> listCompanies = getCompanies(request);
    request.setAttribute(ATT_LISTCOMPANIES, listCompanies);

    this.idEdit = Integer.parseInt(request.getParameter("id"));
    request.setAttribute(ATT_IDEDIT, idEdit);

    Computer computer = ctdao.getById(idEdit);
    ComputerDto cdto = dtoMap.computerToDto(computer);

    request.setAttribute(ATT_COMPUTER, cdto);

    this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
   *      response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    List<Company> listCompanies = getCompanies(request);
    request.setAttribute(ATT_LISTCOMPANIES, listCompanies);

    updateComputer(request, response);

  }

  private List<Company> getCompanies(HttpServletRequest request) {
    try {
      return cndao.getAll();
    } catch (SQLException e) {
      e.printStackTrace();
      LOGGER.error(e.getMessage());
      throw new RuntimeException();
    }
  }

  private void updateComputer(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    DtoValidation<ComputerDto> dtoval = new DtoValidation<ComputerDto>();
    System.out.println(idEdit);
    final String name = request.getParameter("computerName");
    final String introduced = request.getParameter("introduced");
    final String discontinued = request.getParameter("discontinued");
    final int companyId = Integer.parseInt(request.getParameter("companyId"));

    Company comp = null;
    try {
      comp = cndao.getById(companyId);
    } catch (SQLException e) {
      e.printStackTrace();
      LOGGER.error(e.getMessage());
      throw new RuntimeException();
    }

    ComputerDto cdto = new ComputerDto(idEdit, name, introduced, discontinued, comp);
    System.out.println(cdto.toString());

    List<String> validationErrors = new ArrayList<>();
    validationErrors = dtoval.validate(cdto);

    if (validationErrors.size() == 0) {
      Computer c = dtoMap.dtoToComputer(cdto);
      ctdao.update(c);
      LOGGER.info("Computer added with success, redirecting to the Dashboard");
      response.sendRedirect(request.getContextPath() + "/dashboard");
    } else {
      LOGGER.info("Wrong input, redirecting to the view");
      request.setAttribute("validationErrors", validationErrors);
      doGet(request, response);
    }

  }

}

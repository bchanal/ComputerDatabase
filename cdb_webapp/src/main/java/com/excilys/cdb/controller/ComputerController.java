package com.excilys.cdb.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.dto.ComputerDto;
import com.excilys.cdb.dto.DtoMapper;
import com.excilys.cdb.dto.PageDto;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

/**
 * controllers to add, edit une delete computers.
 * @author berangere
 *
 */
@Controller
@RequestMapping("/")
public class ComputerController {

    private final static Logger LOGGER       = LoggerFactory.getLogger(ComputerController.class);

    @Autowired
    private ComputerService     ctdao;
    @Autowired
    private CompanyService      cndao;
    @Autowired
    private DtoMapper           dtoMap;
    public final static String  PARAM_PAGE   = "page";
    public final static String  PARAM_SEARCH = "search";
    public final static String  PARAM_ORDER  = "order";

    public ComputerController() {}

    /**
     * load the companies and the form for adding a computer
     * @param map the ModelMap which contains the parameters
     * @return the form to add a computer
     * @throws SQLException
     */
    @RequestMapping(value = "/add-computer", method = RequestMethod.GET)
    protected String getAddForm(ModelMap map) throws SQLException {

        List<Company> listCompanies = cndao.getAll();

        ComputerDto computerDto = new ComputerDto();
        map.addAttribute("computer", computerDto);
        map.addAttribute("listCompanies", listCompanies);

        return "addComputer";
    }

    /**
     * add the computer from the form
     * @param compDto the computerDto to add
     * @param res the Binding Result
     * @param map the modelMap which contains the parameters
     * @param companyId the id of the company of the computer to add
     * @return the form if there is an error, the dashboard else
     * @throws SQLException
     */
    @RequestMapping(value = "/add-computer", method = RequestMethod.POST)
    protected String addComputer(@ModelAttribute("computer") @Valid ComputerDto compDto,
            BindingResult res, ModelMap map, @RequestParam("companyId") int companyId)
            throws SQLException {

        Company company = null;
        if (res.hasErrors()) {
            List<Company> listcompanies = cndao.getAll();
            map.addAttribute("listCompanies", listcompanies);
            LOGGER.info("error, computer not added");
            return "addComputer";
        }

        if (companyId != 0) {
            company = cndao.getById(companyId);
        }
        compDto.setCompany(company);

        System.out.println(compDto.toString());
        ctdao.create(dtoMap.dtoToComputer(compDto));
        LOGGER.info("add the computer");

        return "redirect:/dashboard";
    }

    /**
     * display the list of computers
     * @param map the ModelMap which contains the parameters
     * @param index the index of the computers (the first id to display)
     * @param nbPP the number per pages to display
     * @param search the word searched
     * @param order the criterion to sort the result = the name of a column
     * @return the dashboard
     */
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    protected String displayDashboard(ModelMap map,
            @RequestParam(value = PARAM_PAGE, required = false, defaultValue = "1") int index,
            @RequestParam(value = "nbPerPage", required = false, defaultValue = "50") int nbPP,
            @RequestParam(value = PARAM_SEARCH, required = false, defaultValue = "") String search,
            @RequestParam(value = PARAM_ORDER, required = false, defaultValue = "id") String order) {
        {

            int column = defineOrder(order);
            PageDto page = getAPage(index, nbPP, search, column);

            map.addAttribute(PARAM_PAGE, page);
            map.addAttribute(PARAM_SEARCH, search);
            map.addAttribute("numPage", index);
            map.addAttribute(PARAM_ORDER, order);

            return "dashboard";
        }
    }

    /**
     * delete some computers (one or more), selected from the dashboard
     * @param ids the id to delete
     * @return the dashboard
     */
    @RequestMapping(value = "/delete-computer", method = RequestMethod.POST)
    protected String delete(@RequestParam("selection") String ids) {
        String[] tabId = ids.split(",");
        int id;

        for (String idStr : tabId) {
            id = Integer.parseInt(idStr);
            ctdao.delete(id);
        }
        return "redirect:/dashboard";
    }

    /**
     * transform the name of the column in the number of the column to display
     * @param order the string
     * @return the order (int format)
     */
    protected int defineOrder(String order) {
        switch (order) {
            case "name":
                return 2;
            case "intro":
                return 3;
            case "disc":
                return 4;
            case "company":
                return 7;
            default:
                return 1;
        }
    }

    /**
     * get the form to edit a computer
     * @param idEdit the id of the computer to edit
     * @param map the ModelMap which contains the parameters
     * @return the form
     * @throws SQLException
     */
    @RequestMapping(value = "/edit-computer", method = RequestMethod.GET)
    protected String getEditParam(@RequestParam("id") int idEdit, ModelMap map) throws SQLException {

        List<Company> listCompanies = cndao.getAll();
        map.addAttribute("listCompanies", listCompanies);
        map.addAttribute("idEdit", idEdit);

        Computer computer = ctdao.getById(idEdit);
        ComputerDto cdto = dtoMap.computerToDto(computer);

        map.addAttribute("computer", cdto);

        return "editComputer";
    }

    /**
     * edit a computer
     * @param map the ModelMap which contains the parameters
     * @param compDto the new version of the computer
     * @param res the bindingResult
     * @param companyId the id of the company of the computer to add
     * @return the form if there is an error, the dashboard else
     * @throws SQLException
     */
    @RequestMapping(value = "/edit-computer", method = RequestMethod.POST)
    protected String postEdit(ModelMap map, @ModelAttribute("computer") @Valid ComputerDto compDto,
            BindingResult res, @RequestParam("companyId") int companyId) throws SQLException {

        if (res.hasErrors()) {
            List<Company> listcompanies = cndao.getAll();
            map.addAttribute("listCompanies", listcompanies);
            LOGGER.info("error ! try again, the computer could not have been modified");
            return "editComputer";
        }
        Company company = cndao.getById(companyId);
        compDto.setCompany(company);

        Computer comp = dtoMap.dtoToComputer(compDto);
        System.out.println("hoy"+compDto.getId());
        comp.setId(compDto.getId());
        System.out.println("hay"+comp.getId());
        ctdao.update(comp);
        LOGGER.info("computer modified");
        return "redirect:/dashboard";
    }

    /**
     * get a page
     * @param numPage the number of the page
     * @param nbPP the number of computers to display per pages
     * @param search the word searched
     * @param order the criterion to sort the results
     * @return the page
     */
    protected PageDto getAPage(int numPage, int nbPP, String search, int order) {

        int index = (numPage - 1) * nbPP;
        PageDto page = ctdao.getAPage(index, nbPP, search, order);
        return page;
    }

}

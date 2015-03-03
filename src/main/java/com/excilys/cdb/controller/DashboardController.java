package com.excilys.cdb.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.model.Page;
import com.excilys.cdb.service.impl.CompanyServiceImpl;
import com.excilys.cdb.service.impl.ComputerServiceImpl;

/**
 * Servlet implementation class Dashboard
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private ComputerServiceImpl ctdao;
    @Autowired
    private CompanyServiceImpl  cndao;

    public DashboardController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @RequestMapping(method = RequestMethod.GET)
    protected String doGet(ModelMap map,
            @RequestParam(value = "page", required = false, defaultValue = "1") int index,
            @RequestParam(value = "nbPerPage", required = false, defaultValue = "50") int nbPP,
            @RequestParam(value = "search", required = false, defaultValue = "") String search,
            @RequestParam(value = "order", required = false, defaultValue = "id") String order) {
        {

            int column = defineOrder(order);
            Page page = getAPage(index, nbPP, search, column);

            map.addAttribute("page", page);
            map.addAttribute("search", search);
            map.addAttribute("numPage", index);
            map.addAttribute("order", order);

            return "dashboard";
        }

    }

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

    protected Page getAPage(int numPage, int nbPP, String search, int order) {

        int index = (numPage - 1) * nbPP;
        Page page = ctdao.getAPage(index, nbPP, search, order);

        return page;
    }

}

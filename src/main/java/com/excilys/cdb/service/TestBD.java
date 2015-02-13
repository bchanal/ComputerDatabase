package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDAOImpl;

public class TestBD {
    /* La liste qui contiendra tous les résultats de nos essais */
    private List<String> messages = new ArrayList<String>();

    public List<String> executerTests( HttpServletRequest request ) {
    	
    	List<String> messages= new ArrayList<String>();
    	
    	List<Computer> list= new ArrayList<Computer>();
    	list = ComputerDAOImpl.instance.getAPage(0,50);
    	for (Computer comp : list){
    		messages.add(comp.toString());
    	}
    	
        /* Ici, nous placerons le code de nos manipulations */
        /* ... */

        return messages;
    }
}


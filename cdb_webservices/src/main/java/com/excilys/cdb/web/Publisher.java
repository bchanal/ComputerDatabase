package com.excilys.cdb.web;

import javax.xml.ws.Endpoint;

import com.excilys.cdb.web.impl.CompanyWSImpl;
import com.excilys.cdb.web.impl.ComputerWSImpl;

public class Publisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8081/cdb/computer", new ComputerWSImpl());    
        Endpoint.publish("http://localhost:8081/cdb/company", new CompanyWSImpl());

     }
}

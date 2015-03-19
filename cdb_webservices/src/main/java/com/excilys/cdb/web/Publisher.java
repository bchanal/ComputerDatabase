package com.excilys.cdb.web;

import javax.xml.ws.Endpoint;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.web.impl.CompanyWSImpl;
import com.excilys.cdb.web.impl.ComputerWSImpl;

public class Publisher {

    public static void main(String[] args) {
        
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/application-context-webservice.xml");
        
        CompanyService companyService = (CompanyService)context.getBean(CompanyService.class);
        Endpoint.publish("http://localhost:8081/cdb/company", new CompanyWSImpl(companyService));

        
        ComputerService computerService = (ComputerService) context.getBean(ComputerService.class);
        Endpoint.publish("http://localhost:8081/cdb/computer", new ComputerWSImpl(computerService));    
        
        context.close();


     }
}

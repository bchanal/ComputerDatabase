package com.excilys.cdb.cli;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.cdb.web.impl.CompanyWSImpl;
import com.excilys.cdb.web.impl.ComputerWSImpl;

public class Launcher {

    public static void main(String[] args) throws SQLException, MalformedURLException {

        Cli console = new Cli();

        URL computerUrl = new URL("http://localhost:8081/cdb/computer?wsdl");
        URL companyUrl = new URL("http://localhost:8081/cdb/company?wsdl");

        QName qname = new QName("http://impl.webservice.java.formation.excilys.com/",
                "CompanyWebServiceImplService");
        Service service = Service.create(companyUrl, qname);

        CompanyWSImpl companyWebService = service.getPort(CompanyWSImpl.class);

        qname = new QName("http://impl.webservice.java.formation.excilys.com/",
                "ComputerWebServiceImplService");
        service = Service.create(computerUrl, qname);

        ComputerWSImpl computerWebService = service.getPort(ComputerWSImpl.class);

        console.setCompanyWSImpl(companyWebService);
        console.setComputerWSImpl(computerWebService);

        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
                "/console-application-context.xml");
        Cli cli = ctx.getBean(Cli.class);
        cli.run(args);

        ctx.close();
    }
}

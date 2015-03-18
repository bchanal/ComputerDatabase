package com.excilys.cdb.cli;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.excilys.cdb.web.ComputerWS;

public class Main {

    public static void main(String[] args) throws Exception {

        URL url = new URL("http://localhost:8080/cdb/hello?wsdl");

        QName qname = new QName("http://impl.web.cdb.excilys.com/", "ComputerWSImplService");

        Service service = Service.create(url, qname);

        ComputerWS hello = service.getPort(ComputerWS.class);

        System.out.println(hello.getHelloWorldAsString("mkyong"));

    }
}

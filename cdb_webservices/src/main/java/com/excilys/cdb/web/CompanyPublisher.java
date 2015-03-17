package com.excilys.cdb.web;

import javax.xml.ws.Endpoint;

import com.excilys.cdb.web.impl.CompanyWSImpl;

public class CompanyPublisher {

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/cdb/hello", new CompanyWSImpl());
     }
}

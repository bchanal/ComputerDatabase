package com.excilys.cdb.web;

import javax.xml.ws.Endpoint;

import com.excilys.cdb.web.impl.ComputerWSImpl;
 
public class ComputerPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/cdb/hello", new ComputerWSImpl());
     }
}

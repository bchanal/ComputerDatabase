package com.excilys.cdb.dto;

import java.time.LocalDateTime;

import com.excilys.cdb.cli.util;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class DtoMapper {
	
	public DtoMapper(){
		
	}
	
    public static ComputerDto computerToDto(Computer computer) {
    	
    	String introduced="";
    	String discontinued = "";
    	Company company= new Company();
        
        int id = computer.getId();
        String name = computer.getName();
        if(computer.getDateIntro()!=null){
        	introduced = computer.getDateIntro().toString();
        }

        if(computer.getDateDiscontinued()!=null){
        	discontinued = computer.getDateDiscontinued().toString();
        }
        if(computer.getManufacturer()!= null){
        	company = computer.getManufacturer();
        }
        
        ComputerDto cdto = new ComputerDto(id, name, introduced, discontinued, company);
        
        return cdto;    
        
    }
    
    public static Computer dtoToComputer(ComputerDto computerd) {
    	
        int id = computerd.getId();
        String name = computerd.getName();
        LocalDateTime introduced = util.checkDate(computerd.getIntroduced());
        LocalDateTime discontinued = util.checkDate(computerd.getDiscontinued());
        Company company = computerd.getCompany();
        
        Computer c = new Computer(id, name, introduced, discontinued, company);
        
        return c;
        
        }
}

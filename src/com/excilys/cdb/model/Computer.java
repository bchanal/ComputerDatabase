package com.excilys.cdb.model;

import java.util.*;

public class Computer {
	
	private final int identifiant;	
	private String name;
	private Date dateIntro;
	private Date dateDiscontinued;
	private Company manufacturer;

	public Computer(int id, String name, Date dateIntro, Date dateDis, Company manufact){
		
		this.identifiant =id;
		this.name = name;
		this.dateIntro = dateIntro;
		this.setDateDiscontinued(dateDis);
		this.manufacturer = manufact;
	}
	
	public Computer(int id, String name){
		
		this.identifiant =id;
		this.name = name;
		this.dateIntro = new Date();
		this.setDateDiscontinued(new Date());
		
	}
	
	public String toString(){
		return ("ordinateur : "+name+"dela marque "+manufacturer+" introduit le : "+ dateIntro.toString()+" enlevé le "+dateDiscontinued);
	}

	public Date getDateDiscontinued() {
		return dateDiscontinued;
	}

	public void setDateDiscontinued(Date dateDiscontinued) {
		this.dateDiscontinued = dateDiscontinued;
	}

	public int getIdentifiant() {
		return identifiant;
	}

}

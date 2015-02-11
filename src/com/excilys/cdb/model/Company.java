package com.excilys.cdb.model;
/**
 * 
 * @author berangere
 *
 */
public class Company {

	private int id;
	private String name;

	public Company(){
		
	}
	
	public Company(int id, String nom) {
		this.id = id;
		this.name = nom;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String nom) {
		this.name = nom;
	}

	public int getId() {
		return this.id;
	}

	public static void main(String[] args) {

	}
/**
 * toString returns a String containing a company's informations
 * @return the String of compayn's informations
 */
	public String toString() {
		return ("id : " + id + " name : " + name);
	}

}

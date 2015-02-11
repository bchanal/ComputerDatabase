package com.excilys.cdb.model;

import java.time.LocalDateTime;
/**
 * 
 * @author berangere
 * Computer represents a computer
 */
public class Computer {

	private int id;
	private String name;
	private LocalDateTime dateIntro;
	private LocalDateTime dateDiscontinued;
	private Company manufacturer;

	public Computer(){
		
	}
	/**
	 * constructor of Computer with arguments
	 * @param id the id from the computer
	 * @param name the name of the computer
	 * @param time the date it was introduced
	 * @param time2 the date it was discontinued
	 * @param company the manufacturer of the computer
	 */
	public Computer(int id, String name, LocalDateTime time,
			LocalDateTime time2, Company company) {

		this.id = id;
		this.name = name;
		this.dateIntro = time;
		this.dateDiscontinued = time2;
		this.manufacturer = company;

	}
/**
 * toString returns a String with the computer attributes
 * @return result the String containing the informations about a computer
 */
	public String toString() {		
		String result = "computer : " + name;
		if (this.manufacturer != null) {
			result = result + " from company " + this.manufacturer.toString();
		}
		if (this.dateIntro != null) {
			result = result + " introduced on " + this.dateIntro.toString();
		}
		if (this.dateDiscontinued != null) {
			result = result + " discontinued on " + this.dateDiscontinued.toString();
		}

		return (result);

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDateIntro() {
		return dateIntro;
	}

	public void setDateIntro(LocalDateTime dateIntro) {
		this.dateIntro = dateIntro;
	}

	public Company getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Company manufacturer) {
		this.manufacturer = manufacturer;
	}

	public LocalDateTime getDateDiscontinued() {
		return dateDiscontinued;
	}

	public void setDateDiscontinued(LocalDateTime dateDiscontinued) {
		this.dateDiscontinued = dateDiscontinued;
	}

	public int getId() {
		return this.id;
	}

}

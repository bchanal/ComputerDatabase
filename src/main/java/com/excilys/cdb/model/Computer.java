package com.excilys.cdb.model;

import java.time.LocalDateTime;

/**
 * 
 * @author berangere Computer represents a computer
 */
public class Computer {

	private int id;
	private String name;
	private LocalDateTime dateIntro;
	private LocalDateTime dateDiscontinued;
	private Company manufacturer;

	public Computer() {

	}

	/**
	 * constructor of Computer with arguments
	 * 
	 * @param id
	 *            the id from the computer
	 * @param name
	 *            the name of the computer
	 * @param time
	 *            the date it was introduced
	 * @param time2
	 *            the date it was discontinued
	 * @param company
	 *            the manufacturer of the computer
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
	 * 
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
			result = result + " discontinued on "
					+ this.dateDiscontinued.toString();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((dateDiscontinued == null) ? 0 : dateDiscontinued.hashCode());
		result = prime * result
				+ ((dateIntro == null) ? 0 : dateIntro.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (dateDiscontinued == null) {
			if (other.dateDiscontinued != null)
				return false;
		} else if (!dateDiscontinued.equals(other.dateDiscontinued))
			return false;
		if (dateIntro == null) {
			if (other.dateIntro != null)
				return false;
		} else if (!dateIntro.equals(other.dateIntro))
			return false;
		if (id != other.id)
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}

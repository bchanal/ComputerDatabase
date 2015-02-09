package com.excilys.cdb.model;

import java.time.LocalDateTime;
import java.sql.SQLException;
import com.excilys.cdb.persistence.CompanyDAO;

public class Computer {

	private final int identifiant;
	private String name;
	private LocalDateTime dateIntro;
	private LocalDateTime dateDiscontinued;
	private int manufacturer;

	public Computer(int id, String name, LocalDateTime time,
			LocalDateTime time2, int company) {

		this.identifiant = id;
		this.name = name;
		this.dateIntro = time;
		this.dateDiscontinued = time2;
		this.manufacturer = company;

	}

	public String toString() {
		CompanyDAO cdao = new CompanyDAO();
		Company nomEntr = null;
		try {
			if (this.manufacturer != 0) {
				nomEntr = CompanyDAO.getACompany(this.manufacturer);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
		}
		String result = "ordinateur : " + name;
		if (nomEntr != null) {
			result = result + " de la marque " + nomEntr.toString();
		}
		if (this.dateIntro != null) {
			result = result + " introduit le " + this.dateIntro.toString();
		}
		if (this.dateDiscontinued != null) {
			result = result + " arreté le " + this.dateDiscontinued.toString();
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

	public int getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(int manufacturer) {
		this.manufacturer = manufacturer;
	}

	public LocalDateTime getDateDiscontinued() {
		return dateDiscontinued;
	}

	public void setDateDiscontinued(LocalDateTime dateDiscontinued) {
		this.dateDiscontinued = dateDiscontinued;
	}

	public int getIdentifiant() {
		return identifiant;
	}

}

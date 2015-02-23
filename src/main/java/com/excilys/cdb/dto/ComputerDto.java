package com.excilys.cdb.dto;

import org.hibernate.validator.constraints.NotBlank;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.validation.*;

public class ComputerDto {
	private int id;
	@NotBlank(message="name mandatory")
	private String name;
	@Date(message="wrong date format : introduced")
	private String introduced;
	@Date(message="wrong date format : introduced")
	private String discontinued;
	private Company company;
	
	public ComputerDto(int id, String name, String date1, String date2, Company company) {
        super();
        this.id = id;
        this.name = name;
        this.introduced = date1;
        this.discontinued = date2;
        this.company = company;
	}

	@Override
	public String toString() {
		return "ComputerDto [id=" + id + ", name=" + name + ", introduced="
				+ introduced + ", discontinued=" + discontinued + ", company="
				+ company + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
}

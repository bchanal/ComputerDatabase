package com.excilys.cdb.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.validation.*;

/**
 * ComputerDto class : simplifies attributes formats, to send lighter objects to jsp
 * @author berangere
 *
 */
@Component
public class ComputerDto {
        
    private int     id;
    @NotBlank
    private String  name;
    @Date
    private String  introduced;
    @Date
    private String  discontinued;
    private Company company;

    public ComputerDto() {
        this.id = 0;
        this.name = "";
        this.introduced = null;
        this.discontinued = null;
        this.company = null;
    }

    /**
     * Constructor from computerDto
     * 
     * @param id the id
     * @param name the name of the computerdto
     * @param date1 the date it was introduced
     * @param date2 the date it was discontinued
     * @param company the company which made the computer(dto)
     */
    public ComputerDto(int id, String name, String date1, String date2, Company company) {
        super();
        this.id = id;
        this.name = name;
        this.introduced = date1;
        this.discontinued = date2;
        this.company = company;
    }

    /**
     * toString
     * @return String the summary of a computerDto
     */
    public String toString() {
        return "ComputerDto [id=" + id + ", name=" + name + ", introduced=" + introduced
                + ", discontinued=" + discontinued + ", company=" + company + "]";
    }

    /**
     * 
     * @return id the id of the computerDto
     */
    public int getId() {
        return id;
    }

    /**
     * set id of a computerDto
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get the name of the computerdto
     * @return name the name of the computer
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of thr computerdto
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the date it was introduced, in string format
     * @return introduced the date it was introduced
     */
    public String getIntroduced() {
        return introduced;
    }

    /**
     * set the date it was introduced
     * @param introduced the date
     */
    public void setIntroduced(String introduced) {
        this.introduced = introduced;
    }

    /**
     * get the date it was discontinued, in string format
     * @return discontinued the date it was discontinued
     */
    public String getDiscontinued() {
        return discontinued;
    }

    /**
     * set the date it was discontinued
     * @param discontinued the date
     */
    public void setDiscontinued(String discontinued) {
        this.discontinued = discontinued;
    }

    /**
     * get the company which made the computer
     * @return company the company object
     */
    public Company getCompany() {
        return company;
    }

    /**
     * set the company
     * @param company the company
     */
    public void setCompany(Company company) {
        this.company = company;
    }

}

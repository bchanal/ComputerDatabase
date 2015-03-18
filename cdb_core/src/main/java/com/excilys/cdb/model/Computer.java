package com.excilys.cdb.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * 
 * @author berangere Computer represents a computer
 */
@Entity
@Table(name = "computer")
public class Computer implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue
    @Column(name = "id", unique = true)
    @Id
    private int id;
    @Column(name = "name")
    private String            name;
    @Column(name = "introduced")
    @Type(type = "com.excilys.cdb.persistence.mapper.LocalDateTimeMapper")
    private LocalDateTime     dateIntro;

    @Column(name = "discontinued")
    @Type(type = "com.excilys.cdb.persistence.mapper.LocalDateTimeMapper")
    private LocalDateTime     dateDiscontinued;

    @ManyToOne
    @JoinColumn(name = "company_id")

    private Company           manufacturer;

    public Computer() { }

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
    public Computer(int id, String name, LocalDateTime time, LocalDateTime time2, Company company) {

        this.id = id;
        this.name = name;
        this.dateIntro = time;
        this.dateDiscontinued = time2;
        this.manufacturer = company;
    }

    /**
     * constructor of Computer with arguments

     * @param name
     *            the name of the computer
     * @param time
     *            the date it was introduced
     * @param time2
     *            the date it was discontinued
     * @param company
     *            the manufacturer of the computer
     */
    public Computer(String name, LocalDateTime time, LocalDateTime time2, Company company) {

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

    public static class Builder {
        Computer computer;

        private Builder() {
            computer = new Computer();
        }

        public Builder id(int id) {
            if (id != 0)
                this.computer.id = id;
            return this;
        }

        public Builder name(String name) {
            this.computer.name = name;
            return this;
        }

        public Builder introduced(LocalDateTime introduced) {
            this.computer.dateIntro = introduced;
            return this;
        }

        public Builder discontinued(LocalDateTime discontinued) {
            this.computer.dateDiscontinued = discontinued;
            return this;
        }

        public Builder company(Company company) {
            if (company != null)
                this.computer.manufacturer = company;
            return this;
        }

        public Computer build() {
            return this.computer;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setId(int id) {
        this.id = id;
    }

}

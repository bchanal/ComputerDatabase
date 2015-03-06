package com.excilys.cdb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author berangere
 *
 */
@Entity
@Table(name = "company")
public class Company implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int    id;
    @Column(name = "name")
    private String name;

    public Company() {}

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

    /**
     * toString returns a String containing a company's informations
     * 
     * @return the String of compayn's informations
     */
    public String toString() {
        return ("id : " + id + " name : " + name);
    }

    public static class Builder {
        Company company;

        private Builder() {
            company = new Company();
        }

        public Builder id(int id) {
            if (id != 0)
                this.company.id = id;
            return this;
        }

        public Builder name(String name) {
            this.company.name = name;
            return this;
        }

        public Company build() {
            return this.company;
        }
    }

    public static Builder builder() {
        return new Builder();
    }


    public void setId(int id) {
        this.id = id;
    }

}

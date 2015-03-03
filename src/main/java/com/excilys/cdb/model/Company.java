package com.excilys.cdb.model;

/**
 * 
 * @author berangere
 *
 */
public class Company {

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        Company other = (Company) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    private int    id;
    private String name;

    public Company() {

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
     * 
     * @return the String of compayn's informations
     */
    public String toString() {
        return ("id : " + id + " name : " + name);
    }

}

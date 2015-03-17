package com.excilys.cdb.model;

import java.util.Arrays;
import java.util.List;

import com.excilys.cdb.model.Computer;

/**
 * Page can be used to make a pagination system for the results display
 * 
 * @author berangere
 *
 */
public class Page {

    @Override
    public String toString() {
        return "Page [list=" + list + ", nbPerPage=" + nbPerPage + ", nbTotalComputer="
                + nbTotalComputer + ", nbTotalPages=" + nbTotalPages + ", index=" + index
                + ", range=" + Arrays.toString(range) + "]";
    }

    private List<Computer> list;
    private int               nbPerPage;
    private int               nbTotalComputer;
    private int               nbTotalPages;
    private int               index;
    private int[]             range = new int[2];

    public Page(int index, int nb, List<Computer> list, int nbTotal) {
        this.index = index;
        this.nbPerPage = nb;
        this.list = list;
        this.nbTotalComputer = nbTotal + 1;
        this.nbTotalPages = (int) Math.ceil(nbTotalComputer / nbPerPage);
        this.range[0] = Math.max(1, this.index / this.nbPerPage - 5);
        this.range[1] = Math.min(nbTotalPages, this.index / this.nbPerPage + 5);

    }

    public int getNbPerPage() {
        return nbPerPage;
    }

    public void setNbPerPage(int nbPerPage) {
        this.nbPerPage = nbPerPage;
    }

    public int getNbTotalPages() {
        return nbTotalPages;
    }

    public void setNbTotalPages(int nbTotalPages) {
        this.nbTotalPages = nbTotalPages;
    }

    public List<Computer> getList() {
        return list;
    }

    public void setList(List<Computer> list) {
        this.list = list;
    }

    public int getNbTotalComputer() {
        return nbTotalComputer;
    }

    public void setNbTotalComputer(int nbTotalComputer) {
        this.nbTotalComputer = nbTotalComputer;
        this.nbTotalPages = (int) Math.ceil(nbTotalComputer / nbPerPage)+1;
        this.range[1] = Math.min(nbTotalPages, this.index / this.nbPerPage + 5);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int[] getRange() {
        return range;
    }

    public void setRange(int[] range) {
        this.range = range;
    }
}

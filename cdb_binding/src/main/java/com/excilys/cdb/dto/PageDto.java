package com.excilys.cdb.dto;

import java.util.Arrays;
import java.util.List;

import com.excilys.cdb.dto.ComputerDto;

public class PageDto {

    @Override
    public String toString() {
        return "Page [list=" + list + ", nbPerPage=" + nbPerPage + ", nbTotalComputer="
                + nbTotalComputer + ", nbTotalPages=" + nbTotalPages + ", index=" + index
                + ", range=" + Arrays.toString(range) + "]";
    }

    private List<ComputerDto> list;
    private int               nbPerPage;
    private int               nbTotalComputer;
    private int               nbTotalPages;
    private int               index;
    private int[]             range = new int[2];

    /**
     * constructor
     * @param index the first id of the page
     * @param nb the number of computers to display
     * @param list the list of computers
     * @param nbTotal the number total of computers
     */
    public PageDto(int index, int nb, List<ComputerDto> list, int nbTotal) {
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

    public List<ComputerDto> getList() {
        return list;
    }

    public void setList(List<ComputerDto> list) {
        this.list = list;
    }

    public int getNbTotalComputer() {
        return nbTotalComputer;
    }

    /**
     * set the nb total of computers and recalculate the ranges and the total number of pages
     * @param nbTotalComputer
     */
    public void setNbTotalComputer(int nbTotalComputer) {
        this.nbTotalComputer = nbTotalComputer;
        this.nbTotalPages = (int) Math.ceil(nbTotalComputer / nbPerPage) + 1;
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

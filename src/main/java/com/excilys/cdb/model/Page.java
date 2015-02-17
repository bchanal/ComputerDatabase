package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.excilys.cdb.persistence.ComputerDAOImpl;

/**
 * Page can be used to make a pagination system for the results display
 * 
 * @author berangere
 *
 */
public class Page {

	private List<Computer> list;
	private int nbParPage;
	private int nbTotalComputer;
	//private int nbTotalPages;
	private int index;

	public Page() {
		this.list = new ArrayList<Computer>();
		this.nbParPage = 10;
		this.index = 0;

	}
	
	public Page(int index, int nb){
		this.index = index;
		this.nbParPage = nb;

	}
	
	public Page(int index, int nb, List<Computer> list){
		this.index = index;
		this.nbParPage = nb;
		this.list = list;

	}

	
//	public Page(List<Computer> al, int index, int nbTotalComp, int nb) {
//
//		this.list = al;
//		this.nbParPage = nb;
//		this.index = index;
//		this.nbTotalComputer = nbTotalComp;
//		this.nbTotalPage = Math.round(nbTotalComputer/this.nbParPage);
//
//	}
	

	/**
	 * display the results by page, with a certain number of results per page
	 */
	public void display() {

		boolean fini = false;
		Scanner scanner = new Scanner(System.in);
		this.nbTotalComputer = ComputerDAOImpl.instance.getNbComputers();

		while (!fini) {
			this.list = ComputerDAOImpl.instance.getAPage(index, 20);
			for (int i = 1; i <nbParPage ; i++) {
				// System.out.println(this.list.size()); (575)
				if (this.list.get(i) != null) {
					System.out.println(index+i + " " + this.list.get(i).toString());
				} else
					fini = true;
			}
			System.out
					.println("\n enter (p : previous, n : next, q : quit)\n ");
			System.out.println("computers "+(index+nbParPage)+" sur "+ nbTotalComputer);
			String ok = scanner.nextLine();
			if (ok.equals("p")) {
				index = index - nbParPage;
			} else if (ok.equals("q")) {
				// break;
				fini = true;
			} else {
				index = index + nbParPage;
			}
		}
		scanner.close();
	}
}

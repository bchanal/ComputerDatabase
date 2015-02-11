package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Page can be used to make a pagination system for the results display
 * 
 * @author berangere
 *
 */
public class Page {

	private List<Computer> list;
	private int nbParPage;

	public Page() {
		this.list = new ArrayList<Computer>();
		this.nbParPage = 10;

	}

	public Page(List<Computer> al, int nb) {

		this.list = al;
		this.nbParPage = nb;

	}

	/**
	 * display the results by page, with a certain number of results per page
	 */
	public void display() {
		int i;
		int numPage = 1;
		boolean fini = false;
		Scanner scanner = new Scanner(System.in);

		while (!fini) {
			for (i = (numPage - 1) * nbParPage; i <= this.nbParPage * numPage
					- 1; i++) {
				// gérer le cas avec this.list.size()
				// System.out.println(this.list.size()); (575)
				if (this.list.get(i) != null) {
					System.out.println(i + " " + this.list.get(i).toString());
				} else
					fini = true;
			}
			System.out
					.println("\n enter (p : previous, n : next, q : quit)\n ");
			String ok = scanner.nextLine();
			if (ok.equals("p")) {
				numPage--;
			} else if (ok.equals("q")) {
				// break;
				fini = true;
			} else {
				numPage++;
			}
		}
		scanner.close();
	}
}

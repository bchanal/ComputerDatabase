package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

	public void afficher() {
		int i;
		int numPage = 1;
		boolean fini = false;
		Scanner scan = new Scanner(System.in);

		while (!fini) {
			for (i = (numPage - 1) * nbParPage; i <= this.nbParPage * numPage
					- 1; i++) {

				if (this.list.get(i) != null) {
					System.out.println(i + " " + this.list.get(i).toString());
				} else
					fini = true;
			}
			System.out
					.println("\n suivant --> (p pour previous, n pour next, q our quitter)\n ");
			String ok = scan.nextLine();
			if (ok.equals("p")) {
				numPage--;
			} else if (ok.equals("q")) {
				break;
			} else {
				numPage++;
			}
		}

	}

}

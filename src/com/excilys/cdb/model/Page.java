package com.excilys.cdb.model;

import java.util.ArrayList;
import java.util.Scanner;

public class Page {

	private ArrayList<Computer> list;
	private int nbParPage;

	public Page() {
		this.list = new ArrayList<Computer>();
		this.nbParPage = 10;

	}

	public Page(ArrayList<Computer> al, int nb) {

		this.list = new ArrayList<Computer>();
		this.nbParPage = nb;

	}

	public void afficher() {
		int i;
		int numPage = 1;
		boolean fini = false;
		Scanner scan = new Scanner(System.in);

		while (!fini) {
			for (i = (numPage - 1) * nbParPage + 1; i <= this.nbParPage
					* numPage; i++) {
				if (list.get(i) != null) {
					list.get(i).toString();
				} else
					fini = true;
			}
			String ok = scan.nextLine();
			numPage++;
		}

	}

}

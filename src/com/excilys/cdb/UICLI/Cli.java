package com.excilys.cdb.UICLI;

import java.util.*;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.*;

public class Cli {

	private static boolean fini;

	public Cli() {
		this.fini = false;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (!fini) {
			afficherMenu();
			String str = sc.nextLine();
			traiterChoix(str);
			if (str == "7") {
				fini = true;
			}
		}
		sc.close();
		System.exit(0);
	}

	private static void afficherMenu() {

		System.out.println("\n \n Tapez votre choix (1 à 7)");
		System.out.println("\n----------------");
		System.out.println(" 1 - Lister les ordinateurs");
		System.out.println(" 2 - Lister les entreprises");
		System.out.println(" 3 - Afficher les détails d'un ordinateur");
		System.out.println(" 4 - Créer un ordinateur");
		System.out.println(" 5 - Modifier un ordinateur");
		System.out.println(" 6 - Supprimer un ordinateur");
		System.out.println(" 7 - Quitter");
		System.out.println("\n----------------");

	}

	private static void traiterChoix(String str) {
		Scanner scan = new Scanner(System.in);
		switch (str) {

		case "1":
			System.out.println("Liste des machines : ");
			//ConnexionBD cdb = new ConnexionBD("SELECT name FROM computer");
			break;

		case "2":
			System.out.println("Liste des entreprises : ");
			// ConnexionBD cdb2 = new ConnexionBD("SELECT name FROM company");
			ArrayList <Company> res = CompanyDAO.getAllCompany();
			for(Company entr : res){
				System.out.println(entr.toString());
				
			}
			break;

		case "3":
			System.out.println("Id de l'ordinateur dont vous voulez voir les détails : ");
			int id = scan.nextInt();
			// vérifier que l'int est dans la liste
			ConnexionBD cdb3 = new ConnexionBD(
					"SELECT * FROM computer WHERE id=" + id);

			// cas de l'id non existant
			// cas ou on rentre n'importe quoi
			// cas ou on se souvient plus et ouon veut revoir la liste
			// truc pour quitter
			break;

		case "4":
			System.out.println("Créer un ordinateur : ");
			//System.out.println("Nom : "); // unicité
			//String nom = scan.nextLine();
			//System.out.println("Date de mise en service, au format AAAA-JJ-MM HH:MM:SS.D : "); // erreurs, date inexistante, date pas encore passée,
			//String date = scan.nextLine();
			//System.out.println("Date de fin de production, au format AAAA-JJ-MM HH:MM:SS.D : ");
			//String dateFin = scan.nextLine();
			//System.out.println("Nom de l'entreprise, compris dans la liste de départ");
			// String comp = scan.nextLine();
			// Computer nc = new Computer(nom, date, dateFin, comp);

			// cas ou saisie incorrecte
			// cas ou l'entreprise existe pas
			// cas ou on ne saisit pas tout
			// truc pour quitter
			break;

		case "5":
			System.out.println("Mettre à jour un ordinateur : ");
			// mise a jour d'un truc qui existe
			// confirmation que c'est bien celui la
			// vérif des nouvelles infos
			// si format erroné, recommencer
			// doit etre synchroniezd
			// truc pour quitter

			break;

		case "6":
			System.out.println("Supprimer un ordinateur : ");
			// synchronised
			// affichage du truc a virer avant
			// virer de la BD
			// truc pour quitter
			break;

		case "7":
			System.out.println("Bye !");
			System.exit(0);
			break;

		default:
			System.out.println("Choix non compris");
			break;
		}
		//scan.close();

	}
}

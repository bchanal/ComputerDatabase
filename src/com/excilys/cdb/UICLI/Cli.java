package com.excilys.cdb.UICLI;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.*;

public class Cli {

	private static boolean fini;

	public Cli() {
		this.setFini(false);
	}

	public static void main(String[] args) throws SQLException {

		Scanner sc = new Scanner(System.in);
		while (!isFini()) {
			afficherMenu();
			String str = sc.nextLine();
			traiterChoix(str);
			if (str == "7") {
				setFini(true);
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

	private static void traiterChoix(String str) throws SQLException {
		Scanner scan = new Scanner(System.in);
		switch (str) {

		case "1":
			System.out.println("Liste des ordinateurs : ");
			List<Computer> resu = ComputerDAO.getAllComputer();
			
			Page display = new Page(resu, 20);
			display.afficher();
			
			
			/**for (Computer comp : resu) {
				System.out.println(comp.toString());
			}*/
			break;

		case "2":
			System.out.println("Liste des entreprises : ");
			List<Company> res = CompanyDAO.getAllCompany();
			for (Company entr : res) {
				System.out.println(entr.toString());

			}

			break;

		case "3":
			System.out
					.println("Id de l'ordinateur dont vous voulez voir les détails : ");
			int id = scan.nextInt();
			Computer computer = ComputerDAO.getAComputer(id);
			if (computer != null) {
				System.out.println(computer.toString());
			} else {
				System.out.println("id non trouvé");
			}

			// vérifier que l'int est dans la liste
			// cas de l'id non existant
			// cas ou on rentre n'importe quoi
			break;

		case "4":
			System.out
					.println("Créer un ordinateur (tapez null dans les champs non définis): ");
			System.out.println("Nom : "); // unicité
			String nom = scan.nextLine();
			System.out
					.println("Date de mise en service, au format AAAA-MM-JJ HH:MM : ");

			String date = scan.nextLine();
			System.out
					.println("Date de fin de production, au format AAAA-MM-JJ HH:MM : ");
			String dateFin = scan.nextLine();
			System.out
					.println("Id de l'entreprise, compris dans la liste de départ");
			int comp = scan.nextInt();

			DateTimeFormatter formatter = DateTimeFormatter
					.ofPattern("yyyy-MM-dd HH:mm");
			LocalDateTime dateTime = null;
			LocalDateTime dateTimeFin = null;

			if (!date.equals("null")) {
				dateTime = LocalDateTime.parse(date, formatter);
			}

			if (!dateFin.equals("null")) {
				dateTimeFin = LocalDateTime.parse(dateFin, formatter);
			}

			Computer nc = ComputerDAO.createComputer(nom, dateTime,
					dateTimeFin, comp);

			// cas ou saisie incorrecte
			break;

		case "5":
			System.out.println("Mettre à jour un ordinateur ");
			System.out.println("Id de l'ordinateur à modifier : ");
			int idUp = scan.nextInt();
			scan.nextLine();

			Computer comput = ComputerDAO.getAComputer(idUp);
			System.out.println(comput.toString());
			System.out.println("------------------");

			
			System.out.println("Modifier le nom ? oui/non ");
			String nomUp;
			String rep = scan.nextLine();
			if (rep.equals("oui")) {
				System.out.println("Nouveau nom : ");
				nomUp = scan.nextLine();
			} else {
				nomUp = comput.getName();
			}

			
			DateTimeFormatter formatterUp = DateTimeFormatter
					.ofPattern("yyyy-MM-dd HH:mm");

			
			System.out
					.println("Modifier la date de mise en service ? oui /non  ");
			rep = scan.nextLine();
			LocalDateTime dateTimeUp = null;

			if (rep.equals("oui")) {
				System.out
						.println("Date de mise en service, au format AAAA-MM-JJ HH:MM : ");
				String dateUp = scan.nextLine();

				if (!dateUp.equals("null")) {
					dateTimeUp = LocalDateTime.parse(dateUp, formatterUp);
				}
			} else {
				dateTimeUp = comput.getDateIntro();
			}

			
			System.out.println("Modifier la date de retrait ? oui/non ");
			rep = scan.nextLine();
			LocalDateTime dateTimeFinUp = null;

			if (rep.equals("oui")) {
				System.out.println("Date de fin de production, au format AAAA-MM-JJ HH:MM : ");
				String dateFinUp = scan.nextLine();

				if (!dateFinUp.equals("null")) {
					dateTimeFinUp = LocalDateTime.parse(dateFinUp, formatterUp);
				}
			} else {
				dateTimeFinUp = comput.getDateDiscontinued();
			}

			
			System.out.println("Modifier l'entreprise ? oui/non ");
			rep = scan.nextLine();
			int compUp;
			if (rep.equals("oui")) {
				System.out.println("Id de l'entreprise, compris dans la liste de départ");
				compUp = scan.nextInt();
			} else {
				compUp = comput.getManufacturer();
			}

			Computer nouveau = new Computer(idUp, nomUp, dateTimeUp,
					dateTimeFinUp, compUp);
			ComputerDAO.updateComputer(nouveau);
			
			break;

		case "6":
			System.out.println("Supprimer un ordinateur ");
			System.out.println("Id de l'ordinateur à supprimer : ");
			int ide = scan.nextInt();

			Computer compute = ComputerDAO.getAComputer(ide);
			System.out.println(compute.toString());

			ComputerDAO.deleteComputer(ide);
			System.out.println("fait !");

			break;

		case "7":
			System.out.println("Bye !");
			System.exit(0);
			break;

		default:
			System.out.println("Choix non compris");
			break;
		}
		// scan.close();

	}

	public static boolean isFini() {
		return fini;
	}

	public static void setFini(boolean fini) {
		Cli.fini = fini;
	}
}

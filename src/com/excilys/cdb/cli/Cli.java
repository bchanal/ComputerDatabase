package com.excilys.cdb.cli;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.*;

public class Cli {

	public Cli() {
	}

	public static void main(String[] args) throws SQLException {

		Scanner sc = new Scanner(System.in);
		while (true) {
			afficherMenu();
			String str = sc.nextLine();
			traiterChoix(str);
		}
		// sc.close();
	}

	private static void afficherMenu() {

		System.out.println("\n \n Please enter (1 to 7)");
		System.out.println("\n----------------");
		System.out.println(" 1 - List computers");
		System.out.println(" 2 - List companies");
		System.out.println(" 3 - Display a computer");
		System.out.println(" 4 - Create new computer");
		System.out.println(" 5 - Update a computer");
		System.out.println(" 6 - Delete a computer");
		System.out.println(" 7 - Quit");
		System.out.println("\n----------------");

	}

	private static void traiterChoix(String str) throws SQLException {
		Scanner scan = new Scanner(System.in);
		switch (str) {

		case "1":
			System.out.println("List computers : ");
			List<Computer> resu = ComputerDAO.getAll();

			Page display = new Page(resu, 20);
			display.afficher();

			/**
			 * for (Computer comp : resu) { System.out.println(comp.toString());
			 * }
			 */
			break;

		case "2":
			System.out.println("List companies : ");
			List<Company> res = CompanyDAO.getAll();
			for (Company entr : res) {
				System.out.println(entr.toString());

			}

			break;

		case "3":
			System.out.println("Id to display : ");
			String idStr = scan.nextLine();
			int id = Integer.parseInt(idStr);
			Computer computer = ComputerDAO.getById(id);
			if (computer != null) {
				System.out.println(computer.toString());
			} else {
				System.out.println("id not found");
			}

			// vérifier que l'int est dans la liste
			// cas de l'id non existant
			// cas ou on rentre n'importe quoi
			break;

		case "4":
			System.out
					.println("Create a computer (enter null for undefined) : ");
			System.out.println("Name : "); // unicité
			String nom = scan.nextLine();
			System.out.println("Introduced on : ( format AAAA-MM-JJ HH:MM) ");

			String date = scan.nextLine();
			System.out.println("Discontinued on : ( format AAAA-MM-JJ HH:MM) ");
			String dateFin = scan.nextLine();
			System.out.println("Id of the company");
			String compStr = scan.nextLine();
			int comp = Integer.parseInt(compStr);

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

			ComputerDAO.create(nom, dateTime, dateTimeFin, comp);

			// cas ou saisie incorrecte
			break;

		case "5":
			System.out.println("Update a computer ");
			System.out.println("Id : ");
			String idUpStr = scan.nextLine();
			int idUp = Integer.parseInt(idUpStr);

			Computer comput = ComputerDAO.getById(idUp);
			System.out.println(comput.toString());
			System.out.println("------------------");

			System.out.println("Update name ? y/n ");
			String nomUp;
			String rep = scan.nextLine();
			if (rep.equals("y")) {
				System.out.println("New Name : ");
				nomUp = scan.nextLine();
			} else {
				nomUp = comput.getName();
			}

			DateTimeFormatter formatterUp = DateTimeFormatter
					.ofPattern("yyyy-MM-dd HH:mm");

			System.out.println("Update introduction date ? y/n  ");
			rep = scan.nextLine();
			LocalDateTime dateTimeUp = null;

			if (rep.equals("y")) {
				System.out
						.println("Introduced on : ( format AAAA-MM-JJ HH:MM) ");
				String dateUp = scan.nextLine();

				if (!dateUp.equals("null")) {
					dateTimeUp = LocalDateTime.parse(dateUp, formatterUp);
				}
			} else {
				dateTimeUp = comput.getDateIntro();
			}

			System.out.println("Update discontinued date ? y/n ");
			rep = scan.nextLine();
			LocalDateTime dateTimeFinUp = null;

			if (rep.equals("y")) {
				System.out
						.println("Discontinued on : (format AAAA-MM-JJ HH:MM) ");
				String dateFinUp = scan.nextLine();

				if (!dateFinUp.equals("null")) {
					dateTimeFinUp = LocalDateTime.parse(dateFinUp, formatterUp);
				}
			} else {
				dateTimeFinUp = comput.getDateDiscontinued();
			}

			System.out.println("Update computer's company ? y/n ");
			rep = scan.nextLine();
			Company compUp;
			int compUpId;
			if (rep.equals("y")) {
				System.out.println("Id  : ");
				String compUpIdStr = scan.nextLine();
				compUpId = Integer.parseInt(compUpIdStr);
				
				CompanyDAO cdao = CompanyDAO.instance;
				compUp = cdao.getById(compUpId);

			} else {
				compUp = comput.getManufacturer();
			}

			Computer nouveau = new Computer(idUp, nomUp, dateTimeUp,
					dateTimeFinUp, compUp);
			ComputerDAO.update(nouveau);

			break;

		case "6":
			System.out.println("Delete a computer ");
			System.out.println("Id : ");
			String ideStr = scan.nextLine();
			int ide = Integer.parseInt(ideStr);

			Computer compute = ComputerDAO.getById(ide);
			System.out.println(compute.toString());

			ComputerDAO.delete(ide);
			System.out.println("Done !");

			break;

		case "7":
			System.out.println("Bye !");
			System.exit(0);
			break;

		default:
			System.out.println("Invalid choice");
			break;
		}
		//scan.close();

	}
}

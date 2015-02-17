package com.excilys.cdb.cli;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.Page;
import com.excilys.cdb.persistence.*;

/**
 * Cli is a user interface
 * 
 * @author berangere
 *
 */
public class Cli {
	private static boolean end;

	public Cli() {
	}

	public static void main(String[] args) throws SQLException {
		String str;

		end = false;
		Scanner sc = new Scanner(System.in);
		while (!end) {
			displayMenu();
			str = sc.nextLine();
			process(str);
		}
		sc.close();
	}

	/**
	 * display the menu
	 */
	private static void displayMenu() {

		System.out.println("\n \n Please enter (1 to 7)");
		System.out.println("\n----------------");
		System.out.println(" 1 - List computers");
		System.out.println(" 2 - List companies");
		System.out.println(" 3 - Display a computer");
		System.out.println(" 4 - Create new computer");
		System.out.println(" 5 - Update a computer");
		System.out.println(" 6 - Delete a computer");
		System.out.println(" 7 - Quit");
		System.out.println("\n----------------\n");

	}

	/**
	 * process :
	 * 
	 * @param str
	 *            the menu entry choosen by the user
	 * @throws SQLException
	 */
	private static void process(String str) throws SQLException {
		Scanner scan = new Scanner(System.in);
		switch (str) {

		case "1":
			System.out.println("List computers : ");
			int index = 0;
			Page page = new Page(index, 20);
			page.display();

			break;

		case "2":
			System.out.println("List companies : ");
			List<Company> res = CompanyDAOImpl.instance.getAll();
			for (Company entr : res) {
				System.out.println(entr.toString());

			}

			break;

		case "3":
			System.out.println("Id to display : ");
			String idStr = scan.nextLine();
			int id = util.checkId(idStr);
			Computer computer = ComputerDAOImpl.instance.getById(id);
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
			caseCreate();

			// cas ou saisie incorrecte
			break;

		case "5":
			System.out.println("Update a computer ");
			caseUpdate();

			break;

		case "6":
			System.out.println("Delete a computer ");
			System.out.println("Id : ");
			String ideStr = scan.nextLine();
			int ide = Integer.parseInt(ideStr);

			Computer compute = ComputerDAOImpl.instance.getById(ide);
			if (compute != null) {
				System.out.println(compute.toString());
			}

			ComputerDAOImpl.delete(ide);
			System.out.println("Done !");

			break;

		case "7":
			System.out.println("Bye !");
			end = true;
			System.exit(0);
			break;

		default:
			System.out.println("Invalid choice");
			break;
		}
		scan.close();

	}

	private static void caseCreate() {
		Scanner scan = new Scanner(System.in);

		System.out.println("Name : "); // unicité
		String nom = scan.nextLine();
		System.out.println("Introduced on : ( format AAAA-MM-JJ HH:MM) ");

		String date = scan.nextLine();
		System.out.println("Discontinued on : ( format AAAA-MM-JJ HH:MM) ");
		String dateFin = scan.nextLine();
		System.out.println("Id of the company");
		String compStr = scan.nextLine();
		// int comp = Integer.parseInt(compStr);
		int comp = util.checkId(compStr);

		LocalDateTime dateTime = null;
		LocalDateTime dateTimeFin = null;

		if (!date.equals("null")) {
			// date.checkDate();
			dateTime = util.checkDate(date);
			// LocalDateTime.parse(date, formatter);
		}

		if (!dateFin.equals("null")) {
			// dateFin.checkDate();
			// dateTimeFin = LocalDateTime.parse(dateFin, formatter);
			dateTimeFin = util.checkDate(dateFin);
		}

		ComputerDAOImpl.instance.create(nom, dateTime, dateTimeFin, comp);
		scan.close();
	}

	private static void caseUpdate() throws SQLException {
		Scanner scan = new Scanner(System.in);
		System.out.println("Id : ");
		String idUpStr = scan.nextLine();
		int idUp = Integer.parseInt(idUpStr);

		Computer comput = ComputerDAOImpl.instance.getById(idUp);
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

		System.out.println("Update introduction date ? y/n  ");
		rep = scan.nextLine();
		LocalDateTime dateTimeUp = null;

		if (rep.equals("y")) {
			System.out.println("Introduced on : ( format AAAA-MM-JJ HH:MM) ");
			String dateUp = scan.nextLine();

			if (!dateUp.equals("null")) {
				dateTimeUp = util.checkDate(dateUp);
				// dateTimeUp = LocalDateTime.parse(dateUp, formatterUp);
			}
		} else {
			dateTimeUp = comput.getDateIntro();
		}
		// date discontinued

		System.out.println("Update discontinued date ? y/n ");
		rep = scan.nextLine();
		LocalDateTime dateTimeFinUp = null;

		if (rep.equals("y")) {
			System.out.println("Discontinued on : (format AAAA-MM-JJ HH:MM) ");
			String dateFinUp = scan.nextLine();

			if (!dateFinUp.equals("null")) {
				dateTimeFinUp = util.checkDate(dateFinUp);
				// dateTimeFinUp = LocalDateTime.parse(dateFinUp, formatterUp);
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
			compUpId = util.checkId(compUpIdStr);
			// compUpId = Integer.parseInt(compUpIdStr);

			CompanyDAOImpl cdao = CompanyDAOImpl.instance;
			compUp = cdao.getById(compUpId);

		} else {
			compUp = comput.getManufacturer();
		}

		Computer nouveau = new Computer(idUp, nomUp, dateTimeUp, dateTimeFinUp,
				compUp);
		ComputerDAOImpl.instance.update(nouveau);
		scan.close();
	}

}

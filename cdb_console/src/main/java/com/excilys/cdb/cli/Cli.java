package com.excilys.cdb.cli;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.dto.PageDto;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.util.Util;
import com.excilys.cdb.web.impl.CompanyWSImpl;
import com.excilys.cdb.web.impl.ComputerWSImpl;

/**
 * Cli is a user interface
 * 
 * @author berangere
 *
 */
@Component
public class Cli {
    private boolean        end;
    @Autowired
    private ComputerWSImpl computerWS;
    @Autowired
    private CompanyWSImpl  companyWS;

    public Cli() {}

    public void run(String[] args) throws SQLException {
        end = false;
        Scanner sc = new Scanner(System.in);
        while (!end) {
            displayMenu();
            process();
        }
        sc.close();
    }

    /**
     * display the menu
     */
    private void displayMenu() {

        System.out.println("\n \n Please enter (1 to 7)");
        System.out.println("\n----------------");
        System.out.println(" 1 - List computers");
        System.out.println(" 2 - List companies");
        System.out.println(" 3 - Display a computer");
        System.out.println(" 4 - Create new computer");
        System.out.println(" 5 - Update a computer");
        System.out.println(" 6 - Delete a computer");
        System.out.println(" 7 - Delete a company");
        System.out.println(" 8 - Quit ");
        System.out.println("\n----------------\n");

    }

    /**
     * process :
     * 
     * @param str
     *            the menu entry choosen by the user
     * @throws SQLException
     */
    private void process() throws SQLException {
        Scanner scan = new Scanner(System.in);
        String str = "8";
        if (scan.hasNext()) {
            str = scan.nextLine();
        }

        switch (str) {

            case "1":
                System.out.println("List computers : ");
                int index = 0;
                PageDto page = computerWS.getAPage(index, 20, "", 1);
                display(page);
                //TODO : affichage d'une page

                break;

            case "2":
                System.out.println("List companies : ");
                List<Company> res = companyWS.getAll();
                for (Company entr : res) {
                    System.out.println(entr.toString());

                }

                break;

            case "3":
                System.out.println("Id to display : ");
                String idStr = scan.nextLine();
                int id = Util.checkId(idStr);
                Computer computer = computerWS.getById(id);
                if (computer != null) {
                    System.out.println(computer.toString());
                } else {
                    System.out.println("id not found");
                }

                break;

            case "4":
                System.out.println("Create a computer (enter null for undefined) : ");
                caseCreate();
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

                Computer compute = computerWS.getById(ide);
                if (compute != null) {
                    System.out.println(compute.toString());
                    computerWS.delete(ide);
                }

                System.out.println("Done !");

                break;

            case "7":
                System.out.println("delete a company and all his computers");
                System.out.println("Id : ");
                idStr = scan.nextLine();
                id = Integer.parseInt(idStr);

                Company c = companyWS.getById(id);
                if (c != null) {
                    System.out.println(c.toString());
                }

                companyWS.delete(id);
                System.out.println("Done !");
                break;

            case "8":
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

    private void display(PageDto page) {
        boolean fini = false;
        Scanner scanner = new Scanner(System.in);
        int nbTotalComputer = page.getNbTotalComputer();
        int index = page.getIndex();

        while (!fini) {
            for (int i = 1; i < page.getNbPerPage(); i++) {
                if (page.getList().get(i) != null) {
                    System.out.println(index + i + " " + page.getList().get(i));
                } else
                    fini = true;
            }
            System.out.println("\n enter (p : previous, n : next, q : quit)\n ");
            System.out.println("computers " + index + page.getNbPerPage() + " sur "
                    + nbTotalComputer);
            String ok = scanner.nextLine();
            if (ok.equals("p")) {
                index = index - page.getNbPerPage();
            } else if (ok.equals("q")) {
                fini = true;
            } else {
                index = index + page.getNbPerPage();
            }
        }
        scanner.close();
    }

    private void caseCreate() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Name : "); // unicité
        String nom = scan.nextLine();
        System.out.println("Introduced on : ( format AAAA-MM-JJ HH:MM) ");

        String date = scan.nextLine();
        System.out.println("Discontinued on : ( format AAAA-MM-JJ HH:MM) ");
        String dateFin = scan.nextLine();
        System.out.println("Id of the company");
        String compStr = scan.nextLine();
        int comp = Util.checkId(compStr);

        LocalDateTime dateTime = null;
        LocalDateTime dateTimeFin = null;

        if (!date.equals("null")) {
            dateTime = Util.checkDate(date);
        }

        if (!dateFin.equals("null")) {
            dateTimeFin = Util.checkDate(dateFin);
        }

        computerWS.create(nom, dateTime, dateTimeFin, comp);
        scan.close();
    }

    private void caseUpdate() throws SQLException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Id : ");
        String idUpStr = scan.nextLine();
        int idUp = Integer.parseInt(idUpStr);

        Computer comput = computerWS.getById(idUp);
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
                dateTimeUp = Util.checkDate(dateUp);
            }
        } else {
            dateTimeUp = comput.getDateIntro();
        }

        System.out.println("Update discontinued date ? y/n ");
        rep = scan.nextLine();
        LocalDateTime dateTimeFinUp = null;

        if (rep.equals("y")) {
            System.out.println("Discontinued on : (format AAAA-MM-JJ HH:MM) ");
            String dateFinUp = scan.nextLine();

            if (!dateFinUp.equals("null")) {
                dateTimeFinUp = Util.checkDate(dateFinUp);
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
            compUpId = Util.checkId(compUpIdStr);

            compUp = companyWS.getById(compUpId);

        } else {
            compUp = comput.getManufacturer();
        }

        Computer nouveau = new Computer(idUp, nomUp, dateTimeUp, dateTimeFinUp, compUp);
        computerWS.update(nouveau);
        scan.close();
    }

}

package com.excilys.cdb.persistence;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;

import com.mysql.jdbc.Statement;

public class RequeteManager {
	ConnexionBD co;

	protected void afficherResultats(ResultSet result) {

		ResultSetMetaData resultMeta;
		try {
			resultMeta = result.getMetaData();
			System.out.println("\n**********************************");
			// On affiche le nom des colonnes
			for (int i = 1; i <= resultMeta.getColumnCount(); i++)
				System.out.print("\t"
						+ resultMeta.getColumnName(i).toUpperCase() + "\t *");
			System.out.println("\n**********************************");

			while (result.next()) {
				for (int i = 1; i <= resultMeta.getColumnCount(); i++)
					if (result.getObject(i) != null) {
						System.out.print("\t" + result.getObject(i).toString()
								+ "\t |");
					} else {
						System.out.print("\t" + "null" + "\t |");
					}
				System.out.println("\n---------------------------------");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void afficherOrdinateurs(Statement state) {
		connexion();

		ResultSet result;
		try {
			result = state.executeQuery("SELECT * FROM computer");
			afficherResultats(result);
			result.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Statement connexion() {
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
		String user = "admincdb";
		String passwd = "qwerty1234";

		try {
			Connection conn = DriverManager.getConnection(url, user, passwd);
			// Création d'un objet Statement
			Statement state = (Statement) conn.createStatement();
			return state;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return 0;
		

	}

}

package com.excilys.cdb.persistence;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ConnexionBD {
	private ResultSet result;

	public ConnexionBD(String requete) {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
			String user = "admincdb";
			String passwd = "qwerty1234";

			Connection conn = DriverManager.getConnection(url, user, passwd);

			// Création d'un objet Statement

			Statement state = conn.createStatement();

			// L'objet ResultSet contient le résultat de la requête SQL
			// ResultSet result = state.executeQuery("SELECT * FROM classe");
			result = state.executeQuery(requete);
			// On récupère les MetaData

			ResultSetMetaData resultMeta = result.getMetaData();
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
					}
					else{ 
						System.out.print("\t" + "---"+ "\t |");						
					}
				System.out.println("\n---------------------------------");
			}
			result.close();
			state.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

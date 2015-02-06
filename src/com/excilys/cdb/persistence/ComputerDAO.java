package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.cdb.model.*;

public class ComputerDAO {

	public ComputerDAO() {
	}

	public static ArrayList<Computer> getAllComputer() {
		ArrayList<Computer> listComputer = new ArrayList<Computer>();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
			String user = "admincdb";
			String passwd = "qwerty1234";

			Connection conn = DriverManager.getConnection(url, user, passwd);

			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM computer");
			// On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			
			for (int i=1; i <= resultMeta.getColumnCount(); i++){
				Computer comp = new Computer(result.getInt("id"), result.getString("name"));
				
				listComputer.add(comp);
			}
			
			result.close();
			state.close();
			conn.close();
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listComputer;
	}

}

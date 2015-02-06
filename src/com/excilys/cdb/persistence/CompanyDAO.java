package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.excilys.cdb.model.*;

public class CompanyDAO {

	public CompanyDAO() {
	}

	public static ArrayList<Company> getAllCompany() {
		ArrayList<Company> listCompany = new ArrayList<Company>();

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
			String user = "admincdb";
			String passwd = "qwerty1234";

			Connection conn = DriverManager.getConnection(url, user, passwd);

			Statement state = conn.createStatement();
			ResultSet result = state.executeQuery("SELECT * FROM company");
			// On récupère les MetaData
			ResultSetMetaData resultMeta = result.getMetaData();
			
			for (int i=1; i <= resultMeta.getColumnCount(); i++){
				Company comp = new Company(result.getInt("id"), result.getString("name"));
				
				listCompany.add(comp);
			}
			
			result.close();
			state.close();
			conn.close();
			

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listCompany;
	}

}

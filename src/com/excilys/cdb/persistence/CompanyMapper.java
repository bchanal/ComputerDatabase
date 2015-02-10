package com.excilys.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.model.Company;

public enum CompanyMapper implements RowMapper<Company> {

	instance;

	public List<Company> toList(ResultSet result) {
		List<Company> listCompany = new ArrayList<Company>();

		try {
			while (result.next()) {
				Company comp = new Company(result.getInt("id"),
						result.getString("name"));
				listCompany.add(comp);


			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listCompany;

	}

	@Override
	public Company toObject(ResultSet rs) {
		// TODO Auto-generated method stub
		return null;
	}

}

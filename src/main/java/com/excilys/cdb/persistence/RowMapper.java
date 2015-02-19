package com.excilys.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * interface for the mappers
 * 
 * @author berangere
 *
 * @param <T>
 */
public interface RowMapper<T> {

	default List<T> toList(ResultSet rs) throws SQLException{
		
		List<T> listT = new ArrayList<T>();

		while (rs.next()) {
			listT.add(toObject(rs));
		}
		return listT;
	}

	T toObject(ResultSet rs);

}

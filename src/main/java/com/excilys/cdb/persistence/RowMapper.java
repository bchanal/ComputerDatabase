package com.excilys.cdb.persistence;

import java.sql.ResultSet;
import java.util.List;

/**
 * interface for the mappers
 * 
 * @author berangere
 *
 * @param <T>
 */
public interface RowMapper<T> {

	List<T> toList(ResultSet rs);

	T toObject(ResultSet rs);

}

package com.excilys.cdb.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RowMapper<T> {

	List<T> toList(ResultSet rs);

	T toObject(ResultSet rs);

}

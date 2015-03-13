package com.excilys.cdb.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * interface for the mappers
 * 
 * @author berangere
 *
 * @param <T> the content to map
 */
public interface RowMapper<T> {
    /**
     * get a list of results from a resultset
     * @param rs the resultset
     * @return the list of results
     * @throws SQLException
     */

    public default List<T> toList(ResultSet rs) throws SQLException {

        List<T> listT = new ArrayList<T>();
        while (rs.next()) {
            listT.add(toObject(rs));
        }
        return listT;
    }

    T toObject(ResultSet rs);

}

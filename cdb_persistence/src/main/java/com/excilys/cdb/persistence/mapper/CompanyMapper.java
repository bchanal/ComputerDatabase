package com.excilys.cdb.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;

/**
 * CompanyMapper is used to display companies from ResultSets
 * 
 * @author berangere
 *
 *
 */
@Component
public class CompanyMapper implements RowMapper<Company> {

    private final static Logger LOGGER = LoggerFactory.getLogger(CompanyMapper.class);

    
    public CompanyMapper(){}
    /**
     * toObject return an object company from a resultset
     * @param rs the resultset
     * @return company the company
     */

    @Override
    public Company toObject(ResultSet rs) {
        Company company = null;

        try {
            company = new Company(rs.getInt("id"), rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            throw new RuntimeException();
        }
        return company;

    }

}

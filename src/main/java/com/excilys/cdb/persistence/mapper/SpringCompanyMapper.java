package com.excilys.cdb.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;

@Component
public class SpringCompanyMapper implements RowMapper<Company> {

    private final static Logger LOGGER = LoggerFactory.getLogger(SpringCompanyMapper.class);

    public SpringCompanyMapper(){}
    @Override
    public Company mapRow(ResultSet rs, int arg1) throws SQLException {
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

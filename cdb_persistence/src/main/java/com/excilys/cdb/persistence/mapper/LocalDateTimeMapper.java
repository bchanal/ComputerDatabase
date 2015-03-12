package com.excilys.cdb.persistence.mapper;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.EnhancedUserType;

public class LocalDateTimeMapper implements EnhancedUserType, Serializable {

    private static final long serialVersionUID = 1L;
    
    public LocalDateTimeMapper(){
        
    }

    @Override
    public Object assemble(Serializable cached, Object obj) throws HibernateException {
        return cached;
    }

    @Override
    public Object deepCopy(Object obj) throws HibernateException {
        return obj;
    }

    @Override
    public Serializable disassemble(Object obj) throws HibernateException {
        return (Serializable) obj;
    }

    @Override
    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        }
        if (x == null || y == null) {
            return false;
        }
        LocalDateTime dtx = (LocalDateTime) x;
        LocalDateTime dty = (LocalDateTime) y;
        return dtx.equals(dty);
    }

    @Override
    public int hashCode(Object obj) throws HibernateException {
        return obj.hashCode();
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] names, SessionImplementor session,
            Object owner) throws HibernateException, SQLException {
        Object timestamp = StandardBasicTypes.TIMESTAMP.nullSafeGet(resultSet, names, session,
                owner);
        if (timestamp == null) {
            return null;
        }
        Date ts = (Date) timestamp;
        Instant instant = Instant.ofEpochMilli(ts.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index,
            SessionImplementor session) throws HibernateException, SQLException {
        if (value == null) {
            StandardBasicTypes.TIMESTAMP.nullSafeSet(preparedStatement, null, index, session);
        } else {
            LocalDateTime ldt = ((LocalDateTime) value);
            Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
            Date timestamp = Date.from(instant);
            StandardBasicTypes.TIMESTAMP.nullSafeSet(preparedStatement, timestamp, index, session);
        }
    }

    @Override
    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }

    @Override
    public Class<LocalDateTime> returnedClass() {
        return LocalDateTime.class;
    }

    @Override
    public int[] sqlTypes() {
        return new int[] { Types.TIMESTAMP };
    }

    @Override
    public String objectToSQLString(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object fromXMLString(String val) {
        return LocalDateTime.parse(val);
    }

    @Override
    public String toXMLString(Object obj) {
        return obj.toString();
    }

}

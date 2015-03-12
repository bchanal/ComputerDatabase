package com.excilys.cdb.persistence;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 
 * @author berangere
 *
 */
public class Util {

    public static LocalDate getLocalDate(Timestamp ts) {
        LocalDate ld = null;
        if (ts != null)
            ld = ts.toLocalDateTime().toLocalDate();
        return ld;
    }

    public static Timestamp getTimestamp(LocalDateTime datetime) {
        Timestamp timestamp = Timestamp.valueOf(datetime);
        return timestamp;
    }
}

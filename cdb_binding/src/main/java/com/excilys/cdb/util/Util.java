package com.excilys.cdb.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    private static final String DATE_REGEX_EN   = "(19|20)[0-9][0-9]/((0[1-9])|([1-2][0-9])|(3[0-1]))/((0[1-9])|(1[0-2]))(T|\\s)(([0-1][0-9])|(2[0-3])):([0-5][0-9])";
    private static final String DATE_PATTERN_EN = "yyyy/dd/MM HH:mm";

    private static final String INT_REGEX       = "^[0-9]*$";

    /**
     * check an id format
     * @param str the string to check
     * @return the id, (int format)
     */
    public static int checkId(String str) {
        int id = 0;

        Pattern p = Pattern.compile(INT_REGEX);
        Matcher m = p.matcher(str);
        if (m.find()) {
            id = Integer.parseInt(str);
        }

        return id;

    }

    public static LocalDateTime checkDate(String str) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN_EN);

        LocalDateTime ldt = null;

        Pattern p = Pattern.compile(DATE_REGEX_EN);
        Matcher m = p.matcher(str);
        if (m.find()) {
            ldt = LocalDateTime.parse(str, formatter);
        }

        return ldt;

    }

    /**
     * check a date whith a pattern and a regex
     * @param str the string to check
     * @param pattern the pattern expected
     * @param regex the regex
     * @return the date, in LocalDateTime format
     */
    public static LocalDateTime checkDate(String str, String pattern, String regex) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        LocalDateTime ldt = null;

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        if (m.find()) {
            ldt = LocalDateTime.parse(str, formatter);
        }

        return ldt;

    }
}

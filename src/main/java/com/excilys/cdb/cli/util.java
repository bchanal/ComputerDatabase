package com.excilys.cdb.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class util {

	private static final String DATE_REGEX = "(19|20)[0-9][0-9]-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2][0-9])|(3[0-1]))(T|\\s)(([0-1][0-9])|(2[0-3])):([0-5][0-9])";
	private static final String INT_REGEX = "^[0-9]*$";

	public static int checkId(String str) {
		int id=0;
		
		Pattern p = Pattern.compile(INT_REGEX);
		Matcher m = p.matcher(str);
		if (m.find()) {
			id = Integer.parseInt(str);
		}

		return id;

	}

	public static LocalDateTime checkDate(String str) {
		DateTimeFormatter formatter = DateTimeFormatter
				.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime ldt = null;

		Pattern p = Pattern.compile(DATE_REGEX);
		Matcher m = p.matcher(str);
		if (m.find()) {
			ldt = LocalDateTime.parse(str, formatter);
		}

		return ldt;

	}
}

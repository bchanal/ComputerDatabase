package com.excilys.cdb.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum PropertyValue {
	
	INSTANCE;

	public String getDbName() {
		
		Properties prop = new Properties();
		String propFileName = "config/db.properties";
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		try {
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '"
						+ propFileName + "' not found in the classpath");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String t = prop.getProperty("DB_NAME");
		return t;
	}
}
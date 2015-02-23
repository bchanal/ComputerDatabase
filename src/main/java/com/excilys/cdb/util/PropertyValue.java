package com.excilys.cdb.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum PropertyValue {

	instance;

	private String url;
	private String user;
	private String passwd;
	private int min;
	private int max;
	private int partitionCount;

	private PropertyValue() {
		Properties prop = new Properties();
		String propFileName = "db.properties";
		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(propFileName);

		try {
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '"+ propFileName + "' not found in the classpath");
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		url = prop.getProperty("URL");
		user = prop.getProperty("USER");
		passwd = prop.getProperty("PASSWD");
		min = Integer.parseInt(prop.getProperty("MINCONNECTIONS"));
		max = Integer.parseInt(prop.getProperty("MAXCONNECTIONS"));
		partitionCount = Integer.parseInt(prop.getProperty("PARTITIONCOUNT"));

	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPasswd() {
		return passwd;
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}

	public int getPartitionCount() {
		return partitionCount;
	}

}
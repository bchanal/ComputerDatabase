package com.excilys.cdb.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class PropertyValue {

    private String url;
    private String user;
    private String passwd;

    /**
     * constructor which get in a file the parameters to use  the database
     */
    public PropertyValue() {
        Properties prop = new Properties();
        String propFileName = "db.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        try {
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName
                        + "' not found in the classpath");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        url = prop.getProperty("URL");
        user = prop.getProperty("USER");
        passwd = prop.getProperty("PASSWD");

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

}
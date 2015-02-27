package com.excilys.cdb.cli;

import java.sql.SQLException;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

	public static void main(String[] args) throws SQLException {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(
				"/application-context.xml");
		Cli cli = ctx.getBean(Cli.class);
		cli.run(args);
		
		ctx.close();
	}

}

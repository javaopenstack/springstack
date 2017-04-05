package org.java.openstack.flyway;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.util.jdbc.DriverDataSource;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class Main {

	public static void main(String args[]) {
		ArgParams argParams = new ArgParams();
		CmdLineParser parser = new CmdLineParser(argParams);
		try {
			parser.parseArgument(args);
			if (argParams.getPassword() == null || argParams.getPassword().trim().length() == 0) {
				System.out.println("");
				System.out.println("Please insert password");
				System.out.println("");
				String password = new String(System.console().readPassword());
				argParams.setPassword(password);
			}
			DataSource dataSource = new DriverDataSource(Main.class.getClassLoader(), argParams.getDriver(),
					argParams.getUrl(), argParams.getUser(), argParams.getPassword());
			List<String> locations = new ArrayList<>();
			locations.add("classpath:/configuration/flyway/common");
			Flyway flyway = FlywayInizializer.createFlyway(dataSource);
			flyway.migrate();
		} catch (CmdLineException e) {
			System.out.println("\nSet these parameters");
			parser.printUsage(System.out);
			return;
		}

	}

}

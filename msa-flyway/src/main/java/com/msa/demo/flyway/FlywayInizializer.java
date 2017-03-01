package com.msa.demo.flyway;

import java.util.List;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;

public class FlywayInizializer {

	public static Flyway createFlyway(DataSource dataSource, List<String> locations ){
		 
		Flyway flyway = new Flyway();
		flyway.setBaselineOnMigrate( true );
		flyway.setLocations( locations.toArray(new String[]{}) );
		flyway.setDataSource( dataSource );
		flyway.migrate();
		return flyway;
	}
	
}

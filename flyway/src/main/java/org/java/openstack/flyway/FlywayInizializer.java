package org.java.openstack.flyway;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlywayInizializer {
	
	public FlywayInizializer(){
		
	}
	
	public static Flyway createFlyway(DataSource dataSource ){
		
		final Logger logger = LoggerFactory.getLogger( FlywayInizializer.class );
	 	 		
		try( Connection connection = dataSource.getConnection() ) {
			
			 
			String vendor = connection.getMetaData().getDatabaseProductName();
			
			List<String> locations = new ArrayList<>();
			String rootPath = "classpath:script" + File.separator;
			locations.add( rootPath + "common" );
			locations.add( rootPath + "vendor" + File.separator + vendor );
			
			
			logger.info( StringUtils.repeat( "-", 80 ) );
			logger.info( "Flyway folders " );
			logger.info( StringUtils.repeat( "-", 80 ) );
			logger.info( StringUtils.join( locations.toArray(), ", " ) );
			logger.info( StringUtils.repeat( "-", 80 ) + "\n\n" ); 
			Flyway flyway = new Flyway();
			flyway.setBaselineOnMigrate( true );
			flyway.setClassLoader(FlywayInizializer.class.getClassLoader());
			flyway.setLocations( locations.toArray(new String[]{}) );
			flyway.setDataSource( dataSource );
			flyway.migrate();  
			return flyway;
		}catch(Throwable e){
			throw new RuntimeException(e);
		}
	}

	
}

package org.java.openstack.application;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.log4j.PropertyConfigurator;
import org.java.openstack.dbserviceimpl.config.CoreConfig;
import org.java.openstack.tools.configuration.Configuration;
import org.java.openstack.tools.server.ConfigurationFactory;

public class EnvInitializer {

	public static Configuration prepareEnvironment(String fileName){
		
		Properties properties = new Properties();
		properties.setProperty( "log4j.rootLogger", "INFO, stdout" );
		properties.setProperty( "log4j.appender.stdout", "org.apache.log4j.ConsoleAppender" );
		properties.setProperty( "log4j.appender.stdout.Target", "System.out" );
		properties.setProperty( "log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout" );
		properties.setProperty( "log4j.appender.stdout.layout.ConversionPattern", "%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n" );
		properties.setProperty( "log4j.logger.jdbc", "ERROR" );
		properties.setProperty( "log4j.logger.jdbc.sqlonly", "INFO" );
		properties.setProperty( "log4j.logger.jdbc.resultset", "ERROR" );
		properties.setProperty( "log4j.logger.jdbc.connection", "ERROR" );
		properties.setProperty( "log4j.logger.jdbc.sqltiming", "ERROR" );
		properties.setProperty( "log4j.logger.jdbc.audit", "ERROR" );
		PropertyConfigurator.configure( properties ); 
		
		if ( StringUtils.isEmpty(System.getProperty(CoreConfig.ENV_NAME)) ){
			System.setProperty(CoreConfig.ENV_NAME, new File(SystemUtils.USER_HOME,CoreConfig.ENV_NAME).toString() );
		}
		Configuration configuration = ConfigurationFactory.createConfiguration(fileName); 
		return configuration;
		
	}
	
}

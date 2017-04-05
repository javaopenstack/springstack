package org.java.openstack.service;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.java.openstack.dbserviceimpl.config.CoreConfig;
import org.java.openstack.tools.configuration.Configuration;
import org.java.openstack.tools.configuration.Configuration.Datasources;
import org.java.openstack.tools.configuration.Configuration.Datasources.Datasource;
import org.java.openstack.tools.server.JndiInitializer;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath*:configuration/spring/test-config.xml","classpath*:configuration/spring/*-service-context.xml"}) 
public abstract class AbstractTest {
	@BeforeClass
	public static void before() throws Exception{
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
		ClassLoader loader = AbstractTest.class.getClassLoader();  
		
		if ( StringUtils.isEmpty(System.getProperty(CoreConfig.ENV_NAME)) ){
			File appHome = new File(loader.getResource("configuration").getFile(), "env_home");
			System.setProperty(CoreConfig.ENV_NAME, appHome.toString());
		}
		 
		Configuration configuration = new Configuration();
		configuration.setDatasources(new Datasources());
		Datasource datasource = new Datasource();
		datasource.setJndi("java:comp/env/jdbc/data");
		datasource.setUser("sa");
		datasource.setPassword("");
		datasource.setUrl("jdbc:h2:mem:test;MODE=MySQL");
		datasource.setClassname("org.h2.Driver");
		datasource.setValidationQuery("SELECT 1");
		configuration.getDatasources().getDatasource().add(datasource);
		JndiInitializer.init(configuration);
	}
	
}

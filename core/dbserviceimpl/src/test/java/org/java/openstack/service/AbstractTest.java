package org.java.openstack.service;

import java.io.File;

import javax.naming.Context;

import org.apache.commons.lang3.StringUtils;
import org.java.openstack.dbserviceimpl.config.CoreConfig;
import org.java.openstack.tools.configuration.Configuration;
import org.java.openstack.tools.configuration.Configuration.Datasources;
import org.java.openstack.tools.configuration.Configuration.Datasources.Datasource;
import org.java.openstack.tools.server.JndiInitializer;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath*:configuration/spring/test-config.xml","classpath*:configuration/spring/*-service-context.xml"}) 
public abstract class AbstractTest {
	@BeforeClass
	public static void before(){
		
		if ( StringUtils.isEmpty(System.getProperty(CoreConfig.APP_NAME_VARIABLE_NAME)) ){
			File appHome = new File(AbstractTest.class.getClassLoader().getResource(".").getFile(), "test_home");
			
			System.setProperty(CoreConfig.APP_NAME_VARIABLE_NAME, appHome.toString());
		}
		Configuration configuration = new Configuration();
		configuration.setDatasources(new Datasources());
		Datasource datasource = new Datasource();
		datasource.setClassname("org.h2.Driver");
		datasource.setJndi("java:comp/env/jdbc/data");
		datasource.setPassword("");
		datasource.setUrl("jdbc:h2:mem:test;MODE=MySQL");
		datasource.setUser("sa");
		configuration.getDatasources().getDatasource().add(datasource);
		JndiInitializer.init(configuration);
	}
	
}

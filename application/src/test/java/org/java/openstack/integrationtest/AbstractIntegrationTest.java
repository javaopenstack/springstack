package org.java.openstack.integrationtest;

import org.java.openstack.application.EnvInitializer;
import org.java.openstack.tools.configuration.Configuration;
import org.java.openstack.tools.server.JndiInitializer;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath*:configuration/spring/test-config.xml","classpath*:configuration/spring/*-service-context.xml"}) 
public abstract class AbstractIntegrationTest {
	@BeforeClass
	public static void before(){ 
		
		Configuration configuration = EnvInitializer.prepareEnvironment("api-config.xml");
		JndiInitializer.init(configuration);
	}
	
}

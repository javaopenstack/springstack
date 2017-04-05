package org.java.openstack.service;

import java.io.File;

import javax.naming.Context;

import org.apache.commons.lang3.StringUtils;
import org.java.openstack.dbserviceimpl.config.CoreConfig;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner; 

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"classpath*:configuration/spring/test-config.xml","classpath*:configuration/spring/*-service-context.xml"}) 
public abstract class AbstractTest {
	@BeforeClass
	public static void before(){
		
		JndiBinder.init();
		
		if ( StringUtils.isEmpty(System.getProperty(CoreConfig.APP_NAME_VARIABLE_NAME)) ){
			File appHome = new File(AbstractTest.class.getClassLoader().getResource(".").toString(), "test_home");
			System.setProperty(CoreConfig.APP_NAME_VARIABLE_NAME, appHome.toString());
		}
		
	}
	
}

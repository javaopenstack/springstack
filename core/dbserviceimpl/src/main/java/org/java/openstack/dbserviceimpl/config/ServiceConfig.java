package org.java.openstack.dbserviceimpl.config;

import org.java.openstack.dbserviceimpl.ConfigurationServiceImpl;
import org.java.openstack.dbserviceimpl.UserServiceImpl;
import org.java.openstack.service.UserService;
import org.springframework.context.annotation.Bean; 

public class ServiceConfig {

	@Bean
	public ConfigurationServiceImpl configurationServiceImpl(){
		return new ConfigurationServiceImpl();
	}
	
	@Bean
	public UserService userService(){
		return new UserServiceImpl();
	}
	 
}

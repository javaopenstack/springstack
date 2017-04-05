package org.java.openstack.dbserviceimpl.config;

import org.java.openstack.dbserviceimpl.ConfigurationServiceImpl;
import org.springframework.context.annotation.Bean;

public class ServiceConfig {

	@Bean
	public ConfigurationServiceImpl userServiceImpl(){
		return new ConfigurationServiceImpl();
	}
}

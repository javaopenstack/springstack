package org.java.openstack.webapp.config;

import org.java.openstack.webapp.controller.CommonController;
import org.java.openstack.webapp.controller.HomeController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; 

@Configuration
public class ControllerConfig {
	
	@Bean
	public HomeController homeController(){
		
		return new HomeController();
		
	} 
	
	@Bean
	public CommonController commonController(){
		
		return new CommonController();
		
	}
	
}

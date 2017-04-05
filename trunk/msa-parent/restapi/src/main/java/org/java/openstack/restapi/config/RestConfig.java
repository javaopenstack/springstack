package org.java.openstack.restapi.config;

import org.apache.commons.lang3.StringUtils;
import org.java.openstack.restapi.controller.DataController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource; 
import org.springframework.web.servlet.config.annotation.EnableWebMvc; 
 

@EnableWebMvc
@ImportResource("classpath*:configuration/spring/*-service-context.xml")
public class RestConfig {
	
	@Bean
	public DataController dataController(){
		
		return new DataController();
		
	} 
	
	@Bean
	public String applicationName(){ 
		String names[] = StringUtils.split(this.getClass().getPackage().getName(), ".");
		String appName = names[names.length-2];
		return appName;
	}
	
}

package org.java.openstack.restapi.config;

import org.apache.commons.lang3.StringUtils;
import org.java.openstack.restapi.controller.UserResourceApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource; 
import org.springframework.web.servlet.config.annotation.EnableWebMvc; 
 

@EnableWebMvc
@ImportResource("classpath*:configuration/spring/*-service-context.xml")
public class RestConfig {
	
	@Bean
	public UserResourceApi dataController(){
		
		return new UserResourceApi();
		
	} 
	
	@Bean
	public String applicationName(){ 
		String names[] = StringUtils.split(this.getClass().getPackage().getName(), ".");
		String appName = names[names.length-2];
		return appName;
	}
	
}

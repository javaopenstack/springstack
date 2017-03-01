package com.msa.demo.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.msa.demo.restapi.controller.DataController; 
 

@EnableWebMvc
@ImportResource("classpath*:configuration/spring/*-service-context.xml")
public class MvcConfig {
	
	@Bean
	public DataController dataController(){
		
		return new DataController();
		
	} 
	 
	
}

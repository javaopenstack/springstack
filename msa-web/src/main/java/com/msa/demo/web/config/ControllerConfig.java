package com.msa.demo.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.msa.demo.web.controller.CommonController;
import com.msa.demo.web.controller.HomeController; 

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

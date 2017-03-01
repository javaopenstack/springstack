package com.msa.demo.config;

import org.springframework.context.annotation.Bean;

import com.msa.demo.service.ConfigurationServiceImpl;

public class ServiceConfig {

	@Bean
	public ConfigurationServiceImpl userServiceImpl(){
		return new ConfigurationServiceImpl();
	}
}

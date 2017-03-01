package com.msa.demo.service;

import java.util.Iterator;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;

import com.msa.demo.dto.Configuration;
import com.msa.demo.repository.ConfigurationRepository;
import com.msa.demo.service.ConfigurationService; 

public class ConfigurationServiceImpl implements ConfigurationService{

	@Autowired
	private ConfigurationRepository configurationRepository;
	
	@Override
	public Properties getConfiguration() {
		Properties properties = new Properties();
		for (Configuration c : configurationRepository.findAll()) {
			properties.put(c.getKey(),c.getValue()); 
		} 
		return properties;
	}

	 

}

package org.java.openstack.dbserviceimpl;

import java.util.Properties;

import org.java.openstack.dbserviceimpl.repository.ConfigurationRepository;
import org.java.openstack.dto.Configuration;
import org.java.openstack.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
 

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

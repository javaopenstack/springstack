package org.java.openstack.dbserviceimpl.config;

import org.java.openstack.dbserviceimpl.repository.ConfigurationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
public class RepositoryConfig {

	@Bean
	public ConfigurationRepository configurationRepository( JpaRepositoryFactory jpaRepositoryFactory ){
		return jpaRepositoryFactory.getRepository(ConfigurationRepository.class);
	}
	
}

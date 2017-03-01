package com.msa.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import com.msa.demo.repository.ConfigurationRepository;
public class RepositoryConfig {

	@Bean
	public ConfigurationRepository configurationRepository( JpaRepositoryFactory jpaRepositoryFactory ){
		return jpaRepositoryFactory.getRepository(ConfigurationRepository.class);
	}
	
}

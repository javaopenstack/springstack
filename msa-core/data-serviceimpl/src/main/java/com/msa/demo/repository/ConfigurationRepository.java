package com.msa.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.msa.demo.dto.Configuration;

public interface ConfigurationRepository extends CrudRepository<Configuration, String>{

}

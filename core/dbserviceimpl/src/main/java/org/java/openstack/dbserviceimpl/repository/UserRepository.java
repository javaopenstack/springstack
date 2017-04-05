package org.java.openstack.dbserviceimpl.repository;

import org.java.openstack.dto.User;
import org.springframework.data.repository.CrudRepository;
 

public interface UserRepository extends CrudRepository<User, String>{
	
	User findByToken(String token); 
	
}

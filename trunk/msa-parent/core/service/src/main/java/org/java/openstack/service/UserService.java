package org.java.openstack.service;

import org.java.openstack.dto.User;

public interface UserService {
	
	User authenticate(String user, String password);
	
	User checkToken(String token, long timeOutMillis);
	
	
}

package org.java.openstack.service;

import org.java.openstack.dto.User;

public interface UserService {
	
	public User getUser(String username);
	
	User validateToken(String token, long timeOutMillis);
	
	
}

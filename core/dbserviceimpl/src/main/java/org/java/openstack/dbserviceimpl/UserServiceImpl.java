package org.java.openstack.dbserviceimpl;

import java.util.Date;

import org.java.openstack.dbserviceimpl.repository.UserRepository;
import org.java.openstack.dto.User;
import org.java.openstack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
  

public class UserServiceImpl implements UserService {
 
	 
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public User getUser(String username) {
		 
		User user =  userRepository.findOne(username);
		return user;
	}
 
	@Override
	@Transactional 
	public User validateToken(String token, long timeOutMillis){
		 
		User authenticatedUser = null;
		 
		if ( token != null ){
			User user = userRepository.findByToken(token);
			if ( user != null ){
				Date lastUpdate = user.getLastUpdate();
				lastUpdate = lastUpdate == null ? new Date(System.currentTimeMillis()-1000) : lastUpdate;
				long diff = System.currentTimeMillis() - lastUpdate.getTime();
				if ( timeOutMillis== -1 || diff <= timeOutMillis ){ 
					user.setLastUpdate(new Date());
					userRepository.save(user);
					authenticatedUser = new User();
					authenticatedUser.setLastUpdate(user.getLastUpdate());
					authenticatedUser.setCompany(user.getCompany());
					authenticatedUser.setToken(token);
					authenticatedUser.setRole(user.getRole());
					authenticatedUser.setUsername(user.getUsername());
				}else{
					logger.warn("! Session was expired : " + user.getUsername() + " , " + diff + " > " + timeOutMillis);
				}
			}
		}
		
		return authenticatedUser;
		
	}
	 
}

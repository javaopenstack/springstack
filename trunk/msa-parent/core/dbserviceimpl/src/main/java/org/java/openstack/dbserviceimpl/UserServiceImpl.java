package org.java.openstack.dbserviceimpl;

import java.util.Date;
 
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.java.openstack.dbserviceimpl.repository.UserRepository;
import org.java.openstack.dto.User;
import org.java.openstack.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
  

public class UserServiceImpl implements UserService {
 
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public User authenticate(String username, String password) {
		 
		User user =  userRepository.findOne(username);
		User authUser = null;
		if  ( user != null ){
			
			if ( passwordEncoder.matches(password, user.getPassword())){
				byte data[] = RandomUtils.nextBytes(25);
				String token = new String(Hex.encode(data));
				Date lastUpdate = new Date();
				authUser = new User();
				String encodedPassword = passwordEncoder.encode(password); 
				authUser.setToken(token);
				authUser.setCompany(user.getCompany());
				authUser.setLastUpdate(lastUpdate);
				authUser.setRole(user.getRole());
				user.setToken(token);
				user.setPassword(encodedPassword);
				user.setLastUpdate(lastUpdate);
				userRepository.save(user);
			}
			
		}
		return authUser;
	}
 
	@Override
	@Transactional 
	public User checkToken(String token, long timeOutMillis){
		 
		User tokenDto = null;
		 
		if ( token != null ){
			User user = userRepository.findByToken(token);
			if ( user != null ){
				Date lastUpdate = user.getLastUpdate();
				lastUpdate = lastUpdate == null ? new Date(System.currentTimeMillis()-1000) : lastUpdate;
				long diff = System.currentTimeMillis() - lastUpdate.getTime();
				if ( timeOutMillis== -1 || diff <= timeOutMillis ){ 
					user.setLastUpdate(new Date());
					userRepository.save(user);
					tokenDto = new User();
					tokenDto.setLastUpdate(user.getLastUpdate());
					tokenDto.setCompany(user.getCompany());
					tokenDto.setToken(token);
					tokenDto.setRole(user.getRole());
					tokenDto.setUsername(user.getUsername());
				}else{
					logger.warn("! Session was expired : " + user.getUsername() + " , " + diff + " > " + timeOutMillis);
				}
			}
		}
		
		return tokenDto;
		
	}
	 
}

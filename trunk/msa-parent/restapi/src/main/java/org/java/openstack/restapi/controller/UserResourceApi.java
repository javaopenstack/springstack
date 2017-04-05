package org.java.openstack.restapi.controller;

import org.java.openstack.dto.User;
import org.java.openstack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
 
@RequestMapping("/api/users")
public class UserResourceApi {
	
	 @Autowired
	 private UserService userService;

	 @RequestMapping("/{id}")	
	 @ResponseBody
	 User getUser(@PathVariable("id")Integer id){
		 User user = new User();
		 
		 return user;
	 }
	 
	 @RequestMapping(method=RequestMethod.POST)	
	 @ResponseBody
	 User saveUser(@RequestBody User user){
		 
		 return user;
	 }
}

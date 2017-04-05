package org.java.openstack.restapi.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msa.demo.dto.User;
@RequestMapping("/api/users")
public class DataController {

	 @RequestMapping("/{id}")	
	 @ResponseBody
	 User getUser(@PathVariable("id")Integer id){
		 User user = new User();
		 user.setAge(13);
		 user.setId(1);
		 user.setName("pippo");
		 return user;
	 }
	 
	 @RequestMapping(method=RequestMethod.POST)	
	 @ResponseBody
	 User saveUser(@RequestBody User user){
		 
		 return user;
	 }
}

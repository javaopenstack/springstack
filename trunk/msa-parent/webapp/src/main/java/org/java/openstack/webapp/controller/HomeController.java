package org.java.openstack.webapp.controller;

import org.java.openstack.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 



@Controller 
public class HomeController {
	
	@Autowired
	private ConfigurationService configurationService;
	
	@RequestMapping( "/" )
	public ModelAndView index()
	{			

		configurationService.getConfiguration();
	 
		return new ModelAndView( "index.html" );
	}
	
	@RequestMapping( "/done" )
	public ModelAndView done()
	{	 
		return new ModelAndView( "index.html" );
	} 
	
}

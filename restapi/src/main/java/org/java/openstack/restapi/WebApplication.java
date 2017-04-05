package org.java.openstack.restapi;

import org.java.openstack.restapi.config.RestConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplication extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	public final static Boolean SECURITY = Boolean.TRUE;
	
	public final static Long SESSION_TIMEOUT = -1l;
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{RestConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[]{ "/" };
	} 

 
}

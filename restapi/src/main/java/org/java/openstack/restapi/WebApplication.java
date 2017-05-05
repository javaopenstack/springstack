package org.java.openstack.restapi;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.java.openstack.restapi.config.RestConfig;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;
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

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		if ( SECURITY ){
			String filterName = AbstractSecurityWebApplicationInitializer.DEFAULT_FILTER_NAME;
			DelegatingFilterProxy springSecurityFilterChain = new DelegatingFilterProxy();
			registerServletFilter(servletContext, springSecurityFilterChain);
			 
		}
	 
		super.onStartup(servletContext);
	} 
 
}

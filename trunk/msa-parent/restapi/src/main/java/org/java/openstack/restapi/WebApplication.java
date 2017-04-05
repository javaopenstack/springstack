package org.java.openstack.restapi;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.java.openstack.restapi.config.RestConfig;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebApplication extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	public final static Boolean SECURITY = Boolean.FALSE;
	
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
			servletContext.addFilter("securityFilter", new DelegatingFilterProxy("springSecurityFilterChain")).addMappingForUrlPatterns(null, false, "/*");	
		}
	 
		super.onStartup(servletContext);
	} 
 
}

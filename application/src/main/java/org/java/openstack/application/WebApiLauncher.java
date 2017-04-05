package org.java.openstack.application;

import java.io.File;

import org.apache.commons.lang3.SystemUtils;
import org.java.openstack.dbserviceimpl.config.CoreConfig;
import org.java.openstack.restapi.WebApplication;
import org.java.openstack.tools.configuration.Configuration;
import org.java.openstack.tools.server.ConfigurationFactory;
import org.java.openstack.tools.server.LocalServer; 

public class WebApiLauncher {

	public static void main(String argv[]){
		
		System.setProperty(CoreConfig.APP_NAME_VARIABLE_NAME, new File(SystemUtils.USER_HOME,CoreConfig.APP_NAME_VARIABLE_NAME).toString() );
		Configuration configuration = ConfigurationFactory.createConfiguration("api-config.xml");
		configuration.setServlet(WebApplication.class.getName());
		LocalServer localServer = new LocalServer(configuration);
		localServer.startServer();
	}
}

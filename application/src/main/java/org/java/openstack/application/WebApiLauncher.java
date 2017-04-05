package org.java.openstack.application;

import org.java.openstack.restapi.WebApplication;
import org.java.openstack.tools.configuration.Configuration;
import org.java.openstack.tools.server.LocalServer; 

public class WebApiLauncher {

	public static void main(String argv[]){
		
		Configuration configuration = EnvInitializer.prepareEnvironment("api-config.xml"); 
		configuration.setServlet(WebApplication.class.getName());
		LocalServer localServer = new LocalServer(configuration);
		localServer.startServer();
	}
}

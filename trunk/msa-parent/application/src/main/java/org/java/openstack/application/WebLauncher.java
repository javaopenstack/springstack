package org.java.openstack.application;

import org.java.openstack.tools.configuration.Configuration;
import org.java.openstack.tools.server.ConfigurationFactory;
import org.java.openstack.tools.server.LocalServer;
import org.java.openstack.webapp.WebApplication;

public class WebLauncher {

	public static void main(String argv[]){
		
		Configuration configuration = ConfigurationFactory.createConfiguration("web-config.xml");
		configuration.setServlet(WebApplication.class.getName());
		LocalServer localServer = new LocalServer(configuration);
		localServer.startServer(); 
	}
}

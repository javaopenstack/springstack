package org.java.openstack.service;

import org.java.openstack.tools.configuration.Configuration;
import org.java.openstack.tools.configuration.Configuration.Datasources;
import org.java.openstack.tools.configuration.Configuration.Datasources.Datasource;
import org.java.openstack.tools.server.JndiInitializer;

public class JndiBinder   {
	 
	static void init(){
		
		JndiInitializer jndiInitializer = new JndiInitializer();
		Configuration configuration = new Configuration();
		configuration.setDatasources(new Datasources());
		Datasource datasource = new Datasource();
		datasource.setClassname("org.h2.Driver");
		datasource.setJndi("java:comp/env/jdbc/test");
		datasource.setUrl("jdbc:h2:mem:test;MODE=MySQL");
		datasource.setUser("sa"); 
		configuration.getDatasources().getDatasource().add(datasource);
		jndiInitializer.init(configuration);
	}
 
}

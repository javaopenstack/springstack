package org.java.openstack.service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.java.openstack.dbserviceimpl.config.CoreConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
 

public class JndiInitializer {

	private Logger log = LoggerFactory.getLogger(JndiInitializer.class);
	
	public static void setup(){
		try{
			
			String msa_home = System.getProperty("msa_home");
			if ( msa_home == null ){
				msa_home=  new File(JndiInitializer.class.getClassLoader().getResource("./").getFile(), "msa_home").getPath();
				System.setProperty( "MSA_HOME", msa_home );
			}
			
			System.setProperty( "log4jdbc.spylogdelegator.name", "net.sf.log4jdbc.log.slf4j.Slf4jSpyLogDelegator" );
			System.setProperty( "log4jdbc.auto.load.popular.drivers", "false" );
			Hashtable<String, String> env = new Hashtable<>();
			env.put( Context.INITIAL_CONTEXT_FACTORY, "org.osjava.sj.memory.MemoryContextFactory" );
			env.put( "org.osjava.sj.jndi.shared", "true" );
			
			System.setProperty( Context.INITIAL_CONTEXT_FACTORY, "org.osjava.sj.memory.MemoryContextFactory" );
			System.setProperty( "org.osjava.sj.jndi.shared", "true" );
			Context context = new InitialContext( env );
			
			context.createSubcontext( "java:" );
			context.createSubcontext( "java:comp" );
			context.createSubcontext( "java:comp/env" );
			context.createSubcontext( "java:comp/env/jdbc" );
			
			BasicDataSource datasource = new BasicDataSource();
			datasource.setUrl( "jdbc:h2:mem:test;MODE=MySQL" );
			datasource.setDriverClassName( "org.h2.Driver" );
			datasource.setUsername( "sa");
			datasource.setPassword( "" );
			datasource.setInitialSize( 1 );
			context.rebind( CoreConfig.JNDI_NAME, new DataSourceSpy( datasource ) );
		}catch(Throwable e){
			throw new RuntimeException(e);
		}
		
	} 
	
}

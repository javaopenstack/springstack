package org.java.openstack.dbserviceimpl.config; 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ReflectionUtils;

import com.github.lalyos.jfiglet.FigletFont; 

@EnableTransactionManagement
@EnableJpaRepositories
@Configuration 
@Import(value={RepositoryConfig.class, ServiceConfig.class})
public class CoreConfig
{	
	public final static String APP_NAME_VARIABLE_NAME ="ENV_HOME";
	
	public final static String JNDI_NAME ="java:comp/env/jdbc/data";
	
	@Value("applicationName")
	private String appname;
	 
	
	public void setAppname(String appname) {
		this.appname = appname;
	}
	
	@Bean
	public DataSource dataSource(File appFolder){
		
		JndiDataSourceLookup jdDataSourceLookup = new JndiDataSourceLookup();
		jdDataSourceLookup.setResourceRef(true);
		return jdDataSourceLookup.getDataSource(JNDI_NAME);
	}
	 
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource)
	{
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl( false );
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter( vendorAdapter );
		factory.setPackagesToScan( org.java.openstack.dto.Configuration.class.getPackage().getName());
		factory.setDataSource( dataSource ); 
		return factory;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
	{
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory( entityManagerFactory );
		return txManager;
	}
	
	@Bean
	public JpaRepositoryFactory jpaRepositoryFactory( EntityManager entityManager )
	{
		return new JpaRepositoryFactory( entityManager );
	}
	
	@Bean
	public TransactionTemplate transactionTemplate( PlatformTransactionManager transactionManager )
	{
		return new TransactionTemplate( transactionManager );
	}
	 
	@Bean
	public File appFolder() throws Exception
	{
		
		File appFolder = new File( System.getProperty( APP_NAME_VARIABLE_NAME ), appname );
		StringBuilder sb = new StringBuilder();
		sb.append( "\n\n\n\n" );
		sb.append( StringUtils.repeat( "_", 80 ) );
		sb.append( "\n" );
		String asciiArt = FigletFont.convertOneLine( this.appname );
		String line = null;
		try( BufferedReader bufferedReader = new BufferedReader( new StringReader( asciiArt ) ) ) {
			
			while( ( line = bufferedReader.readLine() ) != null ) {
				sb.append( StringUtils.center( line, 80 ) + "\n" );
			}
			
		}
		
		sb.append( StringUtils.center( " " + APP_NAME_VARIABLE_NAME + " : " + System.getProperty( APP_NAME_VARIABLE_NAME ), 80 ) );
		sb.append( "\n" );
		sb.append( StringUtils.center( " APP_FOLDER : " + appFolder.getAbsolutePath() + " ", 80 ) );
		sb.append( "\n" );
		sb.append( "\n" );
		sb.append( StringUtils.repeat( "_", 80 ) );
		sb.append( "\n\n\n\n" );
		System.out.println( sb );
		configureLogger( appFolder );
		return appFolder;
	}	
	 
	
	private void configureLogger( File detachFolder ) throws Exception
	{
		File log4jConfig = new File( detachFolder, "config/log4j.properties" );
		if( !log4jConfig.isFile() ) {
			StringBuilder sb = new StringBuilder();
			sb.append( "\n\n" );
			sb.append( StringUtils.repeat( "*", 80 ) + "\n" );
			sb.append( StringUtils.center( "Missing file " + log4jConfig.getAbsolutePath() + " please check ", 78 ) );
			sb.append( StringUtils.repeat( "*", 80 ) + "\n" );
			sb.append( "\n\n" );
			System.out.println( sb );
		} else {
			Properties properties = new Properties();
			properties.load( new FileInputStream( log4jConfig ) );
			
			/**
			 * Invoke BasicConfigurator.resetConfiguration();PropertyConfigurator.configure( properties );
			 * used to avoid log4j compile dependency
			 */
			ReflectionUtils.findMethod(Class.forName("org.apache.log4j.BasicConfigurator"), "resetConfiguration").invoke(null, new Object[]{});
			ReflectionUtils.findMethod(Class.forName("org.apache.log4j.PropertyConfigurator"), "configure").invoke(null, new Object[]{properties});

		}
	}
	
}

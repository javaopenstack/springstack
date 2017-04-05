package org.java.openstack.dbserviceimpl.config; 

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

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

@EnableTransactionManagement
@EnableJpaRepositories
@Configuration 
@Import(value={RepositoryConfig.class, ServiceConfig.class})
public class CoreConfig
{	
	public final static String ENV_NAME ="ENV_HOME";
	
	public final static String JNDI_NAME ="java:comp/env/jdbc/data";
	
	@Value("applicationName")
	private String appname;
	 
	
	public void setAppname(String appname) {
		this.appname = appname;
	}
	
	
	
	@Bean
	public DataSource dataSource(){
		
		JndiDataSourceLookup jdDataSourceLookup = new JndiDataSourceLookup();
		jdDataSourceLookup.setResourceRef(true);
		return jdDataSourceLookup.getDataSource(JNDI_NAME);
	}  
	@Bean  
	public ConfigInitializer inizialize(DataSource dataSource){
		ConfigInitializer configInitializer = new ConfigInitializer(appname);
		configInitializer.configureLogger(); 
		return configInitializer;
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
	  
	
}

package org.java.openstack.dbserviceimpl.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils; 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.lalyos.jfiglet.FigletFont;

public class ConfigInitializer {
	
	private File appFolder;
	
	public ConfigInitializer(String appname){
		
		try{
			appFolder = new File( System.getProperty( CoreConfig.ENV_NAME ), appname );
			StringBuilder sb = new StringBuilder();
			sb.append( "\n\n\n\n" );
			sb.append( StringUtils.repeat( "_", 80 ) );
			sb.append( "\n" );
			String asciiArt = FigletFont.convertOneLine( appname );
			String line = null;
			try( BufferedReader bufferedReader = new BufferedReader( new StringReader( asciiArt ) ) ) {
				
				while( ( line = bufferedReader.readLine() ) != null ) {
					sb.append( StringUtils.center( line, 80 ) + "\n" );
				}
				
			}
			
			sb.append( StringUtils.center( " " + CoreConfig.ENV_NAME + " : " + CoreConfig.ENV_NAME, 80 ) );
			sb.append( "\n" );
			sb.append( StringUtils.center( " APP_FOLDER : " + appFolder.getAbsolutePath() + " ", 80 ) );
			sb.append( "\n" );
			sb.append( "\n" );
			sb.append( StringUtils.repeat( "_", 80 ) );
			sb.append( "\n\n\n\n" );
			System.out.println( sb );
			 
		}catch(Throwable e){
			throw new RuntimeException(e);
		}
			
		
	}
	 
	
	public Boolean configureLogger() {
		try{
			File log4jConfig = new File( appFolder, "config/log4j.properties" );
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
			//	LogManager.resetConfiguration();			 
				String path = log4jConfig.getPath(); 
			//	PropertyConfigurator.configureAndWatch(path, 300000);

			}
			return Boolean.TRUE;
		}catch(Throwable e){
			throw new IllegalStateException(e);
		} 
		
		
	}
	
}

package org.java.openstack.restapi.config;

import org.java.openstack.filter.TokenFilter;
import org.java.openstack.restapi.WebApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private TokenFilter tokenFilter;
	
	@Bean
	public TokenFilter tokenFilter(){
		return new TokenFilter();
	}
 
	 
	@Override
	public void configure(WebSecurity web) throws Exception {
		if ( WebApplication.SECURITY ){
			web.ignoring().antMatchers("/api/users/**");
		}		
	} 
	 
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
    	 
    	if ( WebApplication.SECURITY ){
    		http.csrf().disable().addFilterBefore(tokenFilter,UsernamePasswordAuthenticationFilter.class).antMatcher("/api/**");
    	}
 
    }
    
    
}

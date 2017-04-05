package org.java.openstack.webapp.config;

import java.util.Locale;
import java.util.Objects;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;
 
 
@EnableWebMvc
@ImportResource("classpath*:configuration/spring/*-service-context.xml")
@Import(ControllerConfig.class)
public class MvcConfig extends WebMvcConfigurerAdapter {
 	
	@Override
	public void addResourceHandlers( ResourceHandlerRegistry registry )
	{
		registry.addResourceHandler( "/**" ).addResourceLocations( "classpath:/static/" );
	}
	
	@Bean
	public String applicationName(){ 
		String names[] = StringUtils.split(this.getClass().getPackage().getName(), ".");
		String appName = names[names.length-2];
		return appName;
	}
	
	@Bean
	public LocaleResolver localeResolver()
	{
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale( Locale.ITALY );
		return sessionLocaleResolver;
	}
	
	@Bean
	public LocaleChangeInterceptor LocaleChangeInterceptor()
	{	
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName( "language" );
		return localeChangeInterceptor;
	}
	
	@Bean
	public ControllerClassNameHandlerMapping ControllerClassNameHandlerMapping( LocaleChangeInterceptor localeChangeInterceptor )
	{
		ControllerClassNameHandlerMapping controllerClassNameHandlerMapping = new ControllerClassNameHandlerMapping();
		controllerClassNameHandlerMapping.setInterceptors( new Object[] { localeChangeInterceptor } );
		return controllerClassNameHandlerMapping;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource()
	{
		ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasename( "messages" );
		return resourceBundleMessageSource;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new HandlerInterceptorAdapter() {

			@Override
			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
					ModelAndView modelAndView) throws Exception {
				if ( modelAndView != null ){
					modelAndView.addObject("devmode", Objects.equals(System.getProperty("devmode"), "true"));
				}
				super.postHandle(request, response, handler, modelAndView);
			}
		});
	}

	@Bean
	public ITemplateResolver templateResolver( ServletContext servletContext )
	{		
		ClassLoaderTemplateResolver classLoaderTemplateResolver = new ClassLoaderTemplateResolver();
		classLoaderTemplateResolver.setTemplateMode( TemplateMode.HTML );
		classLoaderTemplateResolver.setPrefix( "/pages/" );
		classLoaderTemplateResolver.setCacheable( false );
		return classLoaderTemplateResolver;
	}
	
	@Bean
	TemplateEngine templateEngine( ITemplateResolver templateResolver )
	{
		SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
		springTemplateEngine.setTemplateResolver( templateResolver );
		springTemplateEngine.addDialect(new LayoutDialect());
		
		return springTemplateEngine;
	}
	
	@Bean
	public ThymeleafViewResolver ThymeleafViewResolver( TemplateEngine templateEngine )
	{
		ThymeleafViewResolver thymeleafViewResolver = new ThymeleafViewResolver();
		thymeleafViewResolver.setTemplateEngine( templateEngine );
		return thymeleafViewResolver;
	} 
 
}

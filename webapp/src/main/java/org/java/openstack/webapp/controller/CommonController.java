package org.java.openstack.webapp.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletResponse;

import org.java.openstack.webapp.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

@ControllerAdvice
@Controller
public class CommonController {
	
	private AtomicInteger atomicInteger = new AtomicInteger(1); 

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 409
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView internalServerError(Exception e){
		 
		ModelAndView modelAndView = new ModelAndView("segments/error.html","devmode", Constants.DEVMODE);
		/**
		 * In modalita devo prevedo la visualizzazione dello stack trace ed anche del pulsate di invio.
		 */
		if ( Constants.DEVMODE ){
			StringWriter stringWriter = new StringWriter();
			try(PrintWriter writer = new PrintWriter(stringWriter);){
				e.printStackTrace(writer);				
			}  
			modelAndView.addObject("ref_id", atomicInteger.incrementAndGet());
			modelAndView.addObject("stacktrace", stringWriter.getBuffer());
		}
		return modelAndView;
		
	}
	
	/**
	 * Genero uno stream javascript
	 * @param response
	 * @param uriComponentsBuilder
	 * @throws Exception
	 */
	@RequestMapping("/config")
	@ResponseBody
	public void clientSettings(HttpServletResponse response, UriComponentsBuilder uriComponentsBuilder) throws Exception{
		
		StringBuffer buffer = null;
		
		Map<String, Object>config = new HashMap<>();
		config.put("contextPath", "\"" + uriComponentsBuilder.path("/").build().toUriString() + "\"");
		
		/**
		 * Scrivo una varibile del tipo
		 * 
		 *  var config = {
		 *  	
		 *  	chiave : valore
		 *  
		 *  };
		 */
		try(StringWriter stringWriter = new StringWriter();PrintWriter printWriter = new PrintWriter(stringWriter);){
			printWriter.println("var config = ");
			printWriter.println("{");
			Set<Entry<String, Object>> set = config.entrySet();
			for (Entry<String, Object> entry : set) {
				printWriter.println("  " + entry.getKey() + " : " + entry.getValue() + ",");
			}
			printWriter.println("  devmode : " + Objects.equals(System.getProperty("devmode"), "true"));
			printWriter.println("};"); 
			printWriter.flush();
			buffer = stringWriter.getBuffer();
		} 

		int length = buffer.toString().getBytes().length;
		response.setContentLength(length);			
		response.setContentType("application/javascript");
		response.setContentLength(length);
		response.getWriter().println(buffer);
		
	}
	 
	
}

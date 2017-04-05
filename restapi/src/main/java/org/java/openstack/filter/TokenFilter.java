package  org.java.openstack.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.java.openstack.dto.User;
import org.java.openstack.restapi.WebApplication;
import org.java.openstack.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;
  
public class TokenFilter extends GenericFilterBean {

	@Autowired
	private UserService userService;
	
	
	public TokenFilter(){
		
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest   = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		String token = request.getParameter("token");
		if ( token == null ){
			token = httpServletRequest.getHeader("token");
		}
		User tokenDto = userService.validateToken(token, WebApplication.SESSION_TIMEOUT);
		if ( tokenDto != null ){
			 
			request.setAttribute("tokenDto", tokenDto);
			chain.doFilter(request, response);
		 
		}else{
			httpServletResponse.sendError(401, "Missing or invalid token");
			 
		}
	}

}

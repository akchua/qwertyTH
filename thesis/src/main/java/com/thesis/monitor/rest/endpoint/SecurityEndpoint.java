package com.thesis.monitor.rest.endpoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.thesis.monitor.beans.AccountBean;
import com.thesis.monitor.rest.handler.SecurityHandler;

@Path("/security")
public class SecurityEndpoint {

	@Autowired
	private SecurityHandler securityHandler;
	
	@GET
	@Path("/account")
	@Produces({ MediaType.APPLICATION_JSON })
	public AccountBean getAccount() {
		return securityHandler.getAccount();
	}
	
	@POST
	@Path("/logout")
	public void logout(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		securityHandler.logout(request, response);
	}
}

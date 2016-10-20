package com.thesis.monitor;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.thesis.monitor.rest.endpoint.AccountEndpoint;
import com.thesis.monitor.rest.endpoint.SecurityEndpoint;
import com.thesis.monitor.rest.endpoint.UserBoardWatchlistEndpoint;

@Component
@ApplicationPath("services")
public class JerseyConfig extends ResourceConfig {
	
	public JerseyConfig() {
		//
		
		// Register End Points
		register(AccountEndpoint.class);
		register(SecurityEndpoint.class);
		register(UserBoardWatchlistEndpoint.class);
	}
}

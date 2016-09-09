package com.thesis.monitor.rest.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.objects.ObjectList;
import com.thesis.monitor.rest.handler.AccountHandler;

@Path("/account")
public class AccountEndpoint {

	@Autowired
	private AccountHandler accountHandler;
	
	@GET
	@Path("/list")
	@Produces({ MediaType.APPLICATION_JSON })
	public ObjectList<Account> getAccountObjectList(@QueryParam("pageNumber") Integer pageNumber, 
			@QueryParam("searchKey") String searchKey) {
		return accountHandler.getAccountObjectList(pageNumber, searchKey);
	}
	
	@GET
	@Path("/get")
	@Produces({ MediaType.APPLICATION_JSON })
	public Account getAccount(@QueryParam("accountId") Long accountId) {
		return accountHandler.getAccount(accountId);
	}
}

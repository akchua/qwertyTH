package com.thesis.monitor.rest.endpoint;

import java.io.IOException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.monitor.beans.AccountFormBean;
import com.thesis.monitor.beans.ResultBean;
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
	
	@POST
	@Path("/save")
	@Produces({ MediaType.APPLICATION_JSON })
	public ResultBean saveAccount(@FormParam("accountFormData") String accountFormData) throws IOException {
		final ResultBean result;
		
		final AccountFormBean accountForm = new ObjectMapper().readValue(accountFormData, AccountFormBean.class);
		
		if(accountForm.getId() == null) {
			result = accountHandler.createAccount(accountForm);
		} else {
			result = null;
		}
		
		return result;
	}
}

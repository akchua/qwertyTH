package com.thesis.monitor;

import javax.ws.rs.NotAuthorizedException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.thesis.monitor.beans.AccountBean;
import com.thesis.monitor.database.entity.Account;

public class AccountContextHolder {

	public static final AccountBean getAccount() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication != null && authentication instanceof UsernamePasswordAuthenticationToken) {
			return (AccountBean) authentication.getPrincipal();
		} else {
			throw new NotAuthorizedException("User is not authenticated.");
		}
	}
	
	public static final Integer getItemsPerPage() {
		return getAccount().getItemsPerPage();
	}
	
	public static final void refreshAccount(Account account) {
		getAccount().setAccount(account);
	}
}

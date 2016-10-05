package com.thesis.monitor.beans;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.enums.AccountType;

public class AccountBean extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 4698479704172294273L;
	
	private Account account;
	
	public AccountBean(String username, String password, Collection<? extends GrantedAuthority> authorities, Account account) {
		super(username, password, authorities);
		setAccount(account);
	}
	
	public Account getAccountEntity() {
		return account;
	}
	
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Long getAccountId() {
		return account.getId();
	}
	
	public Integer getItemsPerPage() {
		return account.getItemsPerPage();
	}
	
	public AccountType getAccountType() {
		return account.getAccountType();
	}
}

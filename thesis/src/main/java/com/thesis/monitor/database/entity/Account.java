package com.thesis.monitor.database.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.thesis.monitor.database.entity.base.BaseObject;
import com.thesis.monitor.database.enums.AccountType;

@Entity(name = "Account")
@Table(name = Account.TABLE_NAME)
public class Account extends BaseObject {
	
	private static final long serialVersionUID = 3533061827502665438L;
	
	public static final String TABLE_NAME = "account";
	
	private String username;
	
	private String password;
	
	private Integer itemsPerPage = Integer.valueOf(10);
	
	private AccountType accountType;

	@Basic
	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Basic
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
	@Column(name = "items_per_page")
	public Integer getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="account_type", length = 50)
	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
}
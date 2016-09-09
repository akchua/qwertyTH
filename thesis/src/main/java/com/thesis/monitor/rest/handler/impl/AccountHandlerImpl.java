package com.thesis.monitor.rest.handler.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.database.service.AccountService;
import com.thesis.monitor.objects.ObjectList;
import com.thesis.monitor.rest.handler.AccountHandler;

@Transactional
@Component
public class AccountHandlerImpl implements AccountHandler {

	@Autowired
	private AccountService accountService;
	
	@Override
	public ObjectList<Account> getAccountObjectList(Integer pageNumber, String searchKey) {
		return accountService.findAllWithPaging(pageNumber, 10, searchKey);
	}

	@Override
	public Account getAccount(Long accountId) {
		return accountService.find(accountId);
	}
}

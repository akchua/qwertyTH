package com.thesis.monitor.rest.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thesis.monitor.beans.AccountFormBean;
import com.thesis.monitor.beans.ResultBean;
import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.database.service.AccountService;
import com.thesis.monitor.objects.ObjectList;
import com.thesis.monitor.rest.handler.AccountHandler;
import com.thesis.monitor.utility.EncryptionUtil;

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

	@Override
	public ResultBean createAccount(AccountFormBean accountForm) {
		final ResultBean result;
		
		if(accountService.isExistsByUsername(accountForm.getUsername())) {
			result = new ResultBean(Boolean.FALSE, "Username already exists.");
		} else {
			final Account account = new Account();
			result = new ResultBean();
			
			setAccount(accountForm, account);
			
			if(accountService.insert(account) != null) {
				result.setSuccess(Boolean.TRUE);
				result.setMessage("Successfully created account with username \"" + accountForm.getUsername() + "\".");
			} else {
				result.setSuccess(Boolean.FALSE);
				result.setMessage("Failed to create account with username \"" + accountForm.getUsername() + "\".");
			}
		}
		
		return result;
	}
	
	private void setAccount(AccountFormBean accountForm, Account account) {
		account.setAccountType(accountForm.getAccountType());
		account.setItemsPerPage(accountForm.getItemsPerPage());
		account.setPassword(EncryptionUtil.getMd5(accountForm.getPassword()));
		account.setUsername(accountForm.getUsername());
	}
}

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
		final ResultBean temp = validateAccountForm(accountForm);
		
		if(temp.isSuccess()) {
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
		} else {
			result = temp;
		}
		
		return result;
	}
	
	@Override
	public ResultBean editAccount(AccountFormBean accountForm) {
		final ResultBean result;
		final ResultBean temp = validateAccountForm(accountForm);
		
		Account account = accountService.find(accountForm.getId());
		
		if(temp.isSuccess()) {
			if(accountService.isExistsByUsername(accountForm.getUsername()) &&
					!account.getUsername().equals(accountForm.getUsername())) {
				result = new ResultBean(Boolean.FALSE, "Username already exists.");
			} else {
				result = new ResultBean();
				setAccount(accountForm, account);
				
				result.setSuccess(accountService.update(account));
				
				if(result.isSuccess()) {
					result.setMessage("Successfully updated account details.");
				} else {
					result.setMessage("Failed to update account.");
				}
			}
		} else {
			result = temp;
		}
		
		return result;
	}
	
	@Override
	public ResultBean removeAccount(Long accountId) {
		final ResultBean result;
		
		Account account = accountService.find(accountId);
		
		if(account != null) {
			result = new ResultBean();
			
			result.setSuccess(accountService.delete(account));
			
			if(result.isSuccess()) {
				result.setMessage("Succcessfully removed account \"" + account.getUsername() + "\".");
			} else {
				result.setMessage("Failed to remove account \"" + account.getUsername() + "\".");
			}
		} else {
			result = new ResultBean(Boolean.FALSE, "Failed to remove account. Account does not exist!");
		}
		
		return result;
	}
	
	private ResultBean validateAccountForm(AccountFormBean accountForm) {
		final ResultBean result;
		
		if(accountForm.getUsername() != null &&
				accountForm.getItemsPerPage() != null &&
				accountForm.getAccountType() != null &&
				accountForm.getId() != null || accountForm.getPassword() != null) {
			if(accountForm.getUsername().matches("[A-Za-z0-9_]+")) {
				if(accountForm.getPassword() != null && accountForm.getPassword().length() < 3) {
					result = new ResultBean(Boolean.FALSE, "Password must be at least 3 characters long.");
				} else {
					result = new ResultBean(Boolean.TRUE, "");
				}
			} else {
				result = new ResultBean(Boolean.FALSE, "Invalid characters used in username field.");
			}
		} else {
			result = new ResultBean(Boolean.FALSE, "All fields must be filled.");
		}
		
		return result;
	}
	
	private void setAccount(AccountFormBean accountForm, Account account) {
		account.setAccountType(accountForm.getAccountType());
		account.setItemsPerPage(accountForm.getItemsPerPage());
		if(accountForm.getPassword() != null) account.setPassword(EncryptionUtil.getMd5(accountForm.getPassword()));
		account.setUsername(accountForm.getUsername());
	}
}

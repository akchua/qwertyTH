package com.thesis.monitor.rest.handler;

import com.thesis.monitor.beans.AccountFormBean;
import com.thesis.monitor.beans.ResultBean;
import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.objects.ObjectList;

public interface AccountHandler {

	ObjectList<Account> getAccountObjectList(Integer pageNumber, String searchKey);
	
	Account getAccount(Long accountId);
	
	ResultBean createAccount(AccountFormBean accountForm);
	
	ResultBean editAccount(AccountFormBean accountForm);
	
	ResultBean deleteAccount(Long accountId);

}

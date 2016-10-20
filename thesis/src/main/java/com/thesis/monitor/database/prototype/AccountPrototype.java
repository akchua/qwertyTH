package com.thesis.monitor.database.prototype;

import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.objects.ObjectList;

public interface AccountPrototype {

	ObjectList<Account> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey);
	
	Account findByUsernameAndPassword(String username, String password);
	
	Account findByUsername(String username);
}

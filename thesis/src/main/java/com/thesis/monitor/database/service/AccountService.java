package com.thesis.monitor.database.service;

import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.database.prototype.AccountPrototype;

public interface AccountService
		extends Service<Account, Long>, AccountPrototype {
	
	Account findByUsername(String username);
	
	Boolean isExistsByUsername(String username);
	
}

package com.thesis.monitor.database.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thesis.monitor.database.dao.AccountDAO;
import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.database.service.AccountService;
import com.thesis.monitor.objects.ObjectList;

@Service
public class AccountServiceImpl
		extends AbstractService<Account, Long, AccountDAO>
		implements AccountService {

	@Autowired
	protected AccountServiceImpl(AccountDAO dao) {
		super(dao);
	}

	@Override
	public ObjectList<Account> findAllWithPaging(int pageNumber, int resultsPerPage, String searchKey) {
		return dao.findAllWithPaging(pageNumber, resultsPerPage, searchKey);
	}

	@Override
	public Account findByUsernameAndPassword(String username, String password) {
		return dao.findByUsernameAndPassword(username, password);
	}
	

	@Override
	public Boolean isExistsByUsername(String username) {
		return dao.findByUsername(StringUtils.trimToEmpty(username)) != null;
	}
}

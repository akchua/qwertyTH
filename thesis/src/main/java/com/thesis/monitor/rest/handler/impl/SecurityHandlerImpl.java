package com.thesis.monitor.rest.handler.impl;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thesis.monitor.AccountContextHolder;
import com.thesis.monitor.beans.AccountBean;
import com.thesis.monitor.rest.handler.SecurityHandler;

@Transactional
@Component
public class SecurityHandlerImpl implements SecurityHandler {

	@Override
	public AccountBean getAccount() {
		return AccountContextHolder.getAccount();
	}
}

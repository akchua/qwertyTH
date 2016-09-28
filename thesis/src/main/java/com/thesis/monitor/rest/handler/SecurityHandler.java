package com.thesis.monitor.rest.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thesis.monitor.beans.AccountBean;

public interface SecurityHandler {

	AccountBean getAccount();
	
	void logout(HttpServletRequest request, HttpServletResponse response);
}

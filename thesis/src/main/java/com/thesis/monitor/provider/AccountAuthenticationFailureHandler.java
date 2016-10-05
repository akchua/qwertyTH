package com.thesis.monitor.provider;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.thesis.monitor.constants.ApplicationConstants;

@Component
public class AccountAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authentication)
			throws IOException, ServletException {
		response.getWriter().write(ApplicationConstants.FAILURE);
	}
}

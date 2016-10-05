package com.thesis.monitor.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.thesis.monitor.beans.AccountBean;
import com.thesis.monitor.database.entity.Account;
import com.thesis.monitor.database.service.AccountService;
import com.thesis.monitor.utility.EncryptionUtil;

@Transactional
@Component
public class AccountAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private AccountService accountService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        
        final Account account = accountService.findByUsernameAndPassword(username, EncryptionUtil.getMd5(password));
        if(account != null) {
        	final List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority(account.getAccountType().name()));
        	return new UsernamePasswordAuthenticationToken(new AccountBean(username, password, grantedAuths, account), password, grantedAuths);
        } else {
        	return null;
        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}

package com.cloudeggtech.chalk.xeps.component;

import com.cloudeggtech.chalk.core.stream.IAuthenticationToken;

public class SecretToken implements IAuthenticationToken {
	private String secret;
	
	public SecretToken(String secret) {
		this.secret = secret;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public Object getCredentials() {
		return secret;
	}
	
}

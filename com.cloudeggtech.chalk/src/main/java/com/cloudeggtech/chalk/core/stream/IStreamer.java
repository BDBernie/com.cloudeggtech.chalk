package com.cloudeggtech.chalk.core.stream;

import com.cloudeggtech.chalk.network.IConnectionListener;

public interface IStreamer {
	void negotiate(IAuthenticationToken authToken);
	void setConnectionListener(IConnectionListener connectionListener);
	IConnectionListener getConnectionListener();
	void setNegotiationListener(INegotiationListener negotiantListener);
	INegotiationListener getNegotiationListener();
	void setAuthenticationCallback(IAuthenticationCallback authenticationCallback);
	IAuthenticationCallback getAuthenticationCallback();
}

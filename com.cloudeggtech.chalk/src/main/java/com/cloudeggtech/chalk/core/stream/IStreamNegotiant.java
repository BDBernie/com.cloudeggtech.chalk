package com.cloudeggtech.chalk.core.stream;

import com.cloudeggtech.chalk.network.ConnectionException;
import com.cloudeggtech.chalk.network.IConnectionListener;

public interface IStreamNegotiant extends IConnectionListener {
	void negotiate(INegotiationContext context) throws ConnectionException, NegotiationException;
}

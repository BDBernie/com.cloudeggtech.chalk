package com.cloudeggtech.chalk.core.stream;

import com.cloudeggtech.basalt.protocol.core.stream.sasl.Failure;

public interface IAuthenticationFailure {
	boolean isRetriable();
	Failure.ErrorCondition getErrorCondition();
	int getFailureCount();
	void retry(IAuthenticationToken authToken);
	void abort();
}
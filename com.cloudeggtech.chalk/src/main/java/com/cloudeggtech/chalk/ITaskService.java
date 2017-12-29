package com.cloudeggtech.chalk;

import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;
import com.cloudeggtech.chalk.core.ErrorException;

public interface ITaskService {
	void execute(ITask<?> task);
	void setDefaultTimeout(int timeout);
	int getDefaultTimeout();
	void setDefaultTimeoutHandler(ITimeoutHandler timeoutHandler);
	<K extends Stanza, V> V execute(ISyncTask<K, V> task) throws ErrorException;
}

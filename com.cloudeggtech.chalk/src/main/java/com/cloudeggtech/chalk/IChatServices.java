package com.cloudeggtech.chalk;

import com.cloudeggtech.chalk.core.stream.IStream;

public interface IChatServices {
	IStream getStream();
	ITaskService getTaskService();
	IPresenceService getPresenceService();
	IMessageService getMessageService();
	IIqService getIqService();
	IErrorService getErrorService();
	
	public <T> T createApi(Class<T> apiType);
}

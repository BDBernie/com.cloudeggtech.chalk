package com.cloudeggtech.chalk;

import java.util.List;
import java.util.Properties;

import com.cloudeggtech.chalk.core.stream.IAuthenticationToken;
import com.cloudeggtech.chalk.core.stream.INegotiationListener;
import com.cloudeggtech.chalk.core.stream.IStream;
import com.cloudeggtech.chalk.core.stream.StreamConfig;
import com.cloudeggtech.chalk.network.ConnectionException;
import com.cloudeggtech.chalk.network.IConnectionListener;

public interface IChatClient {
	public enum State {
		CLOSED,
		CONNECTING,
		CONNECTED
	}
	
	void setStreamConfig(StreamConfig streamConfig);
	StreamConfig getStreamConfig();
	
	void addNegotiationListener(INegotiationListener negotiationListener);
	void removeNegotiationListener(INegotiationListener negotiationListener);
	List<INegotiationListener> getNegotiationListeners();
	
	void addConnectionListener(IConnectionListener connectionListener);
	void removeConnectionListener(IConnectionListener connectionListener);
	List<IConnectionListener> getConnectionListeners();
	
	void setDefaultErrorHandler(IErrorHandler errorHandler);
	IErrorHandler getDefaultErrorHandler();
	
	void setDefaultExceptionHandler(IExceptionHandler exceptionHandler);
	IExceptionHandler getExceptionHandler();
	
	void connect(IAuthenticationToken authToken) throws ConnectionException, AuthFailureException;
	boolean isConnected();
	void close();
	boolean isClosed();
	
	void register(Class<? extends IPlugin> pluginClass);
	void register(Class<? extends IPlugin> pluginClass, Properties properties);
	void unregister(Class<? extends IPlugin> pluginClass);
	
	<T> T createApi(Class<T> apiType);
	
	IStream getStream();
	
	IChatServices getChatServices();
	
	State getState();
}

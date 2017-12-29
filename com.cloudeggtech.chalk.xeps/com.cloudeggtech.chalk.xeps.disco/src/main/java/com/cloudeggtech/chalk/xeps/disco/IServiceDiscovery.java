package com.cloudeggtech.chalk.xeps.disco;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.chalk.core.ErrorException;

public interface IServiceDiscovery {
	boolean discoImServer() throws ErrorException;
	boolean discoAccount(JabberId account) throws ErrorException;
	JabberId[] discoAvailableResources(JabberId account) throws ErrorException;
}

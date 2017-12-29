package com.cloudeggtech.chalk;

import java.util.List;

import com.cloudeggtech.basalt.protocol.core.Protocol;
import com.cloudeggtech.basalt.protocol.core.stanza.Iq;
import com.cloudeggtech.chalk.core.stanza.IIqListener;

public interface IIqService {
	void send(Iq iq);
	void addListener(IIqListener listener);
	void removeListener(IIqListener listener);
	void addListener(Protocol protocol, IIqListener listener);
	void removeListener(Protocol protocol);
	
	List<IIqListener> getListeners();
}

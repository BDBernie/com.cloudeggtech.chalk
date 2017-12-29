package com.cloudeggtech.chalk;

import java.util.List;

import com.cloudeggtech.basalt.protocol.im.stanza.Presence;
import com.cloudeggtech.chalk.im.stanza.IPresenceListener;

public interface IPresenceService {
	void send(Presence presence);
	void addListener(IPresenceListener listener);
	void removeListener(IPresenceListener listener);
	List<IPresenceListener> getListeners();
	
	Presence getCurrent();
}

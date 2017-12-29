package com.cloudeggtech.chalk.im;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.im.stanza.Message;
import com.cloudeggtech.basalt.protocol.im.stanza.Presence;
import com.cloudeggtech.chalk.im.roster.IRosterService;
import com.cloudeggtech.chalk.im.stanza.IMessageListener;
import com.cloudeggtech.chalk.im.stanza.IPresenceListener;
import com.cloudeggtech.chalk.im.subscription.ISubscriptionService;

public interface IInstantingMessager {
	IRosterService getRosterService();
	ISubscriptionService getSubscriptionService();
	
	void send(Message message);
	void send(JabberId contact, Message message);
	void send(Presence presence);
	void send(JabberId contact, Presence presence);
	
	void addMessageListener(IMessageListener messageListener);
	void removeMessageListener(IMessageListener messageListener);
	void addPresenceListener(IPresenceListener presenceListener);
	void removePresenceListener(IPresenceListener presenceListener);
}

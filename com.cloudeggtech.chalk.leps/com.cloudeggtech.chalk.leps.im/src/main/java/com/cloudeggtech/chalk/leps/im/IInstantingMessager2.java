package com.cloudeggtech.chalk.leps.im;

import java.util.List;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.im.stanza.Message;
import com.cloudeggtech.basalt.protocol.im.stanza.Presence;
import com.cloudeggtech.chalk.im.roster.IRosterService;
import com.cloudeggtech.chalk.im.stanza.IPresenceListener;
import com.cloudeggtech.chalk.leps.im.subscription.ISubscriptionService2;

public interface IInstantingMessager2 {
	enum Protocol {
		STANDARD,
		LEP
	}
	
	IRosterService getRosterService();
	ISubscriptionService2 getSubscriptionService();
	
	String send(Message message);
	String send(JabberId contact, Message message);
	void send(Presence presence);
	void send(JabberId contact, Presence presence);
	
	void addMessageListener(IMessageListener2 messageListener);
	void removeMessageListener(IMessageListener2 messageListener);
	List<IMessageListener2> getMessageListeners();
	void addPresenceListener(IPresenceListener presenceListener);
	void removePresenceListener(IPresenceListener presenceListener);
	List<IPresenceListener> getPresenceListeners();
	
	void setSubscriptionProtocol(Protocol subscriptionProtocol);
	Protocol getSubscriptionProtocol();
	void setMessageProtocol(Protocol messageProtocol);
	Protocol getMessageProtocol();
	
	void sendMessageReadAck(JabberId contact, String messageId);
}

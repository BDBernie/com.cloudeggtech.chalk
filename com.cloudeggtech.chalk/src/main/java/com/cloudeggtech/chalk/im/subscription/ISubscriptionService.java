package com.cloudeggtech.chalk.im.subscription;

import com.cloudeggtech.basalt.protocol.core.JabberId;

public interface ISubscriptionService {
	void subscribe(JabberId contact);
	void unsubscribe(JabberId contact);
	void approve(JabberId user);
	void refuse(JabberId user);
	void addSubscriptionListener(ISubscriptionListener listener);
	void removeSubscriptionListener(ISubscriptionListener listener);
	ISubscriptionListener[] getSubscriptionListeners();
}

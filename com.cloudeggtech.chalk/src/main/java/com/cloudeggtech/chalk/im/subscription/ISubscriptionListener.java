package com.cloudeggtech.chalk.im.subscription;

import com.cloudeggtech.basalt.protocol.core.JabberId;

public interface ISubscriptionListener {
	void asked(JabberId user);
	void approved(JabberId contact);
	void refused(JabberId contact);
	void revoked(JabberId user);
	void occurred(SubscriptionError error);
}

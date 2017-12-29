package com.cloudeggtech.chalk.im.stanza;

import com.cloudeggtech.basalt.protocol.im.stanza.Message;

public interface IMessageListener {
	void received(Message message);
}

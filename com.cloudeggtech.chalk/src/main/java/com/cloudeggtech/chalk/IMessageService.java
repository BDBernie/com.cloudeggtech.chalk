package com.cloudeggtech.chalk;

import java.util.List;

import com.cloudeggtech.basalt.protocol.im.stanza.Message;
import com.cloudeggtech.chalk.im.stanza.IMessageListener;

public interface IMessageService {
	void send(Message message);
	void addListener(IMessageListener listener);
	void removeListener(IMessageListener listener);
	List<IMessageListener> getListeners();
}

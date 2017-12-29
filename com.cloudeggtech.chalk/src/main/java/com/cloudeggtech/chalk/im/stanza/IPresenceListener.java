package com.cloudeggtech.chalk.im.stanza;

import com.cloudeggtech.basalt.protocol.im.stanza.Presence;

public interface IPresenceListener {
	void received(Presence presence);
}

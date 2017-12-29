package com.cloudeggtech.chalk.core.stanza;

import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;

public interface IStanzaListener {
	void received(Stanza stanza);
}

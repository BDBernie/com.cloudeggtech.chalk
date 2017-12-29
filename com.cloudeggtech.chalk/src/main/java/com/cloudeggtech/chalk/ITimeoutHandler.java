package com.cloudeggtech.chalk;

import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;

public interface ITimeoutHandler {
	void process(Stanza stanza);
}

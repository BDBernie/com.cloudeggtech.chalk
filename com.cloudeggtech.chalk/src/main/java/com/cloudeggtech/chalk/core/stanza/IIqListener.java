package com.cloudeggtech.chalk.core.stanza;

import com.cloudeggtech.basalt.protocol.core.stanza.Iq;

public interface IIqListener {
	void received(Iq iq);
}

package com.cloudeggtech.chalk.xeps.muc.events;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;

public class KickedEvent extends RoomEvent<Kicked> {

	public KickedEvent(Stanza source, JabberId roomJid, Kicked eventObject) {
		super(source, roomJid, eventObject);
	}

}

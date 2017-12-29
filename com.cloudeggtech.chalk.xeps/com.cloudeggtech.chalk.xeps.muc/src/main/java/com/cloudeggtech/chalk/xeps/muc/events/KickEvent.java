package com.cloudeggtech.chalk.xeps.muc.events;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;

public class KickEvent extends RoomEvent<Kick> {

	public KickEvent(Stanza source, JabberId roomJid, Kick eventObject) {
		super(source, roomJid, eventObject);
	}

}

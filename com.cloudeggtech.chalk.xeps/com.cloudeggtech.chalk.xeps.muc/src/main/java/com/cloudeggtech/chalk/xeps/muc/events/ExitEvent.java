package com.cloudeggtech.chalk.xeps.muc.events;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;

public class ExitEvent extends RoomEvent<Exit> {

	public ExitEvent(Stanza source, JabberId roomJid, Exit exit) {
		super(source, roomJid, exit);
	}

}

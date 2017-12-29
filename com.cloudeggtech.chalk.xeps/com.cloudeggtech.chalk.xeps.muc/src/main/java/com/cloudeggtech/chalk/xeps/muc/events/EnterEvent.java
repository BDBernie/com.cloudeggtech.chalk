package com.cloudeggtech.chalk.xeps.muc.events;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;

public class EnterEvent extends RoomEvent<Enter> {

	public EnterEvent(Stanza source, JabberId roomJid, Enter enter) {
		super(source, roomJid, enter);
	}

}

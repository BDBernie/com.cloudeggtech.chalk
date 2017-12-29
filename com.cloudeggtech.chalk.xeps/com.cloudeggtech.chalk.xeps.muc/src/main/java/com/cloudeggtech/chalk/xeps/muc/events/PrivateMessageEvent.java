package com.cloudeggtech.chalk.xeps.muc.events;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;

public class PrivateMessageEvent extends RoomEvent<RoomMessage> {

	public PrivateMessageEvent(Stanza source, JabberId roomJid, RoomMessage message) {
		super(source, roomJid, message);
	}
	
}

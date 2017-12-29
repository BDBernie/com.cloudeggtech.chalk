package com.cloudeggtech.chalk.xeps.muc.events;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;

public class DiscussionHistoryEvent extends RoomEvent<RoomMessage> {

	public DiscussionHistoryEvent(Stanza source, JabberId roomJid, RoomMessage eventObject) {
		super(source, roomJid, eventObject);
	}

}

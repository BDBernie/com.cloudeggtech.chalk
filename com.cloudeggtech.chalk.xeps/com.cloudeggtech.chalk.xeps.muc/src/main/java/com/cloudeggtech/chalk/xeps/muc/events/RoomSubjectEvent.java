package com.cloudeggtech.chalk.xeps.muc.events;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;

public class RoomSubjectEvent extends RoomEvent<RoomSubject> {

	public RoomSubjectEvent(Stanza source, JabberId roomJid, RoomSubject subject) {
		super(source, roomJid, subject);
	}

}

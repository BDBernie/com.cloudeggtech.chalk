package com.cloudeggtech.chalk.xeps.muc.events;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;

public class ChangeAvailabilityStatusEvent extends RoomEvent<ChangeAvailabilityStatus> {

	public ChangeAvailabilityStatusEvent(Stanza source, JabberId roomJid,
			ChangeAvailabilityStatus eventObject) {
		super(source, roomJid, eventObject);
	}

}

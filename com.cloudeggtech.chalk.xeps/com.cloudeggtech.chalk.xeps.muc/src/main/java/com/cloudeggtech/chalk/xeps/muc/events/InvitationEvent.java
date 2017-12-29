package com.cloudeggtech.chalk.xeps.muc.events;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;

public class InvitationEvent extends RoomEvent<Invitation> {

	public InvitationEvent(Stanza source, JabberId roomJid, Invitation invitation) {
		super(source, roomJid, invitation);
	}

}

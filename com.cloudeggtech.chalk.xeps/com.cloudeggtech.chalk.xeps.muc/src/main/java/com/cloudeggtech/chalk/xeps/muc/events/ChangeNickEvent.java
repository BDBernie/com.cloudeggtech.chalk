package com.cloudeggtech.chalk.xeps.muc.events;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;

public class ChangeNickEvent extends RoomEvent<ChangeNick> {

	public ChangeNickEvent(Stanza source, JabberId roomJid, ChangeNick changeNick) {
		super(source, roomJid, changeNick);
	}

}

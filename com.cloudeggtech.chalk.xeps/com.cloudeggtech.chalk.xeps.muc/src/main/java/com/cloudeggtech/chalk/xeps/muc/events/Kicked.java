package com.cloudeggtech.chalk.xeps.muc.events;

import com.cloudeggtech.basalt.xeps.muc.Affiliation;
import com.cloudeggtech.basalt.xeps.muc.Role;

public class Kicked extends Kick {

	public Kicked(String nick, Affiliation affiliation, Role role) {
		super(nick, affiliation, role);
	}

}

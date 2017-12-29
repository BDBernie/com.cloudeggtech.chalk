package com.cloudeggtech.chalk.im.roster;

import com.cloudeggtech.basalt.protocol.im.roster.Roster;

public interface IRosterListener {
	void retrieved(Roster roster);
	void occurred(RosterError error);
	void updated(Roster roster);
}

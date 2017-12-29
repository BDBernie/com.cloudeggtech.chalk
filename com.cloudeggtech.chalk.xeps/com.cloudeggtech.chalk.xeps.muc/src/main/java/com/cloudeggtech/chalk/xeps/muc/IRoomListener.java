package com.cloudeggtech.chalk.xeps.muc;

import com.cloudeggtech.chalk.xeps.muc.events.RoomEvent;

public interface IRoomListener {
	void received(RoomEvent<?> event);
}

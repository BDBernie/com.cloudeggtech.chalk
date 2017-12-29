package com.cloudeggtech.chalk.xeps.muc;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.chalk.core.ErrorException;

public interface IMucService {
	JabberId[] getMucHosts() throws ErrorException;
	int getTotalNumberOfRooms(JabberId host) throws ErrorException;
	IRoom createInstantRoom(JabberId roomJid, String nick) throws ErrorException;
	<T> IRoom createReservedRoom(JabberId roomJid, String nick,
			IRoomConfigurator configurator) throws ErrorException;
	IRoom getRoom(JabberId roomJid);
	void addRoomListener(IRoomListener listener);
	void removeRoomListener(IRoomListener listener);
	JabberId[] getMucRooms();
}

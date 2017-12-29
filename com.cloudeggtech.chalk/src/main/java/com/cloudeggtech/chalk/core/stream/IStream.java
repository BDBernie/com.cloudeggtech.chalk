package com.cloudeggtech.chalk.core.stream;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;
import com.cloudeggtech.basalt.protocol.oxm.IOxmFactory;
import com.cloudeggtech.chalk.core.IErrorListener;
import com.cloudeggtech.chalk.core.stanza.IStanzaListener;
import com.cloudeggtech.chalk.network.IConnection;
import com.cloudeggtech.chalk.network.IConnectionListener;

public interface IStream {
	JabberId getJid();
	StreamConfig getStreamConfig();
	
	void send(Stanza stanza);
	
	boolean isConnected();
	
	void close();
	boolean isClosed();
	
	void addStanzaListener(IStanzaListener stanzaListener);
	void removeStanzaListener(IStanzaListener stanzaListener);
	IStanzaListener[] getStanzaListeners();
	
	void addErrorListener(IErrorListener errorListener);
	void removeErrorListener(IErrorListener errorListener);
	IErrorListener[] getErrorListeners();
	
	void addConnectionListener(IConnectionListener connectionListener);
	void removeConnectionListener(IConnectionListener connectionListener);
	IConnectionListener[] getConnectionListeners();
	
	void addStanzaWatcher(IStanzaWatcher stanzaWatcher);
	void removeStanzaWatcher(IStanzaWatcher stanzaWatcher);
	IStanzaWatcher[] getStanzaWatchers();
	
	IOxmFactory getOxmFactory();
	void setOxmFactory(IOxmFactory oxmFactory);
	
	IConnection getConnection();
}

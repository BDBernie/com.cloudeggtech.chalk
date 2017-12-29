package com.cloudeggtech.chalk;

import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;
import com.cloudeggtech.basalt.protocol.core.stanza.error.StanzaError;

interface ISyncOperation<K extends Stanza, V> {
	void trigger(IUnidirectionalStream<K> stream);
	boolean isErrorOccurred(StanzaError error);
	boolean isResultReceived(K stanza);
	V processResult(K stanza);
}

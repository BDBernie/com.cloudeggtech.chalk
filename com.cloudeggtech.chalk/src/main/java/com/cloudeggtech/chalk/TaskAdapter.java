package com.cloudeggtech.chalk;

import com.cloudeggtech.basalt.protocol.core.stanza.Stanza;
import com.cloudeggtech.basalt.protocol.core.stanza.error.StanzaError;

public abstract class TaskAdapter<T extends Stanza> implements ITask<T> {

	@Override
	public boolean processError(IUnidirectionalStream<T> stream, StanzaError error) {
		return false;
	}

	@Override
	public boolean processTimeout(IUnidirectionalStream<T> stream, T stanza) {
		return false;
	}

	@Override
	public void interrupted() {}

}

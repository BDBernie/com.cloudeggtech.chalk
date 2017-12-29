package com.cloudeggtech.chalk.leps.im;

import com.cloudeggtech.basalt.leps.im.message.traceable.Trace;
import com.cloudeggtech.chalk.im.stanza.IMessageListener;

public interface IMessageListener2 extends IMessageListener {
	void traced(Trace trace);
}

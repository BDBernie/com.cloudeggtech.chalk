package com.cloudeggtech.chalk.network.codec;

import com.cloudeggtech.basalt.protocol.core.ProtocolException;
import com.cloudeggtech.basalt.protocol.oxm.preprocessing.OutOfMaxBufferSizeException;

public interface IMessageParser {
	String[] parse(char[] in) throws OutOfMaxBufferSizeException, ProtocolException;
	void clear();
	void setMaxBufferSize(int maxBufferSize);
	int getMaxBufferSize();
}

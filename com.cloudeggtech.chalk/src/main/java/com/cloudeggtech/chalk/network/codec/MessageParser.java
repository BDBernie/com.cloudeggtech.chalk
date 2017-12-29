package com.cloudeggtech.chalk.network.codec;

import com.cloudeggtech.basalt.protocol.core.ProtocolException;
import com.cloudeggtech.basalt.protocol.oxm.OxmService;
import com.cloudeggtech.basalt.protocol.oxm.preprocessing.IProtocolPreprocessor;
import com.cloudeggtech.basalt.protocol.oxm.preprocessing.OutOfMaxBufferSizeException;

public class MessageParser implements IMessageParser {
	private static int DEFAULT_MAX_BUFFER_SIZE = 1024 * 1024;
	
	private IProtocolPreprocessor preprocessor;
	
	public MessageParser() {
		this(DEFAULT_MAX_BUFFER_SIZE);
	}
	
	public MessageParser(int maxBufferSize) {
		preprocessor = OxmService.createProtocolPreprocessor();
		preprocessor.setMaxBufferSize(maxBufferSize);
	}
	
	@Override
	public synchronized String[] parse(char[] bytes) throws OutOfMaxBufferSizeException, ProtocolException {
		int readBytes = bytes.length;
		
		if (readBytes == 0) {
			return preprocessor.getDocuments();
		}
		
		return preprocessor.parse(bytes, readBytes);
	}

	@Override
	public void setMaxBufferSize(int maxBufferSize) {
		preprocessor.setMaxBufferSize(maxBufferSize);
	}

	@Override
	public int getMaxBufferSize() {
		return preprocessor.getMaxBufferSize();
	}

	@Override
	public void clear() {
		preprocessor.clear();
	}

}

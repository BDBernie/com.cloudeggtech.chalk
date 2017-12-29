package com.cloudeggtech.chalk;

import com.cloudeggtech.basalt.protocol.core.IError;

public interface IErrorHandler {
	void process(IError error);
}

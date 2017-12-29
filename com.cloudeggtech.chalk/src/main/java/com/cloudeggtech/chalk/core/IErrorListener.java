package com.cloudeggtech.chalk.core;

import com.cloudeggtech.basalt.protocol.core.IError;

public interface IErrorListener {
	void occurred(IError error);
}

package com.cloudeggtech.chalk;

import java.util.List;

import com.cloudeggtech.chalk.core.IErrorListener;

public interface IErrorService {
	void addErrorListener(IErrorListener listener);
	void removeErrorListener(IErrorListener listener);
	List<IErrorListener> getErrorListeners();
}

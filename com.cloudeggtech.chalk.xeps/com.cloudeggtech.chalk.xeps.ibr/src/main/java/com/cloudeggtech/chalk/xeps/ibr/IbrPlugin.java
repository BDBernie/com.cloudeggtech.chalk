package com.cloudeggtech.chalk.xeps.ibr;

import java.util.Properties;

import com.cloudeggtech.chalk.IChatSystem;
import com.cloudeggtech.chalk.IPlugin;
import com.cloudeggtech.chalk.core.stream.StandardStreamConfig;
import com.cloudeggtech.chalk.core.stream.StreamConfig;

public class IbrPlugin implements IPlugin {

	@Override
	public void init(IChatSystem chatSystem, Properties properties) {
		StreamConfig streamConfig = chatSystem.getStreamConfig();
		
		if (!(streamConfig instanceof StandardStreamConfig)) {
			throw new IllegalArgumentException(String.format("IBR plugin needs a StandardStreamConfig."));
		}
		
		Properties apiProperties = new Properties();
		apiProperties.put("streamConfig", streamConfig);
		
		chatSystem.registerApi(IRegistration.class, Registration.class, apiProperties, false);
	}

	@Override
	public void destroy(IChatSystem chatSystem) {
		chatSystem.unregisterApi(IRegistration.class);
	}

}

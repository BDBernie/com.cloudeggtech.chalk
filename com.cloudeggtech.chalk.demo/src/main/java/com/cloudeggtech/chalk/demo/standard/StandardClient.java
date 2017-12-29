package com.cloudeggtech.chalk.demo.standard;

import java.util.Properties;

import com.cloudeggtech.chalk.demo.Client;
import com.cloudeggtech.chalk.demo.Demo;
import com.cloudeggtech.chalk.leps.im.IInstantingMessager2;
import com.cloudeggtech.chalk.leps.im.InstantingMessagerPlugin2;
import com.cloudeggtech.chalk.xeps.ibr.IbrPlugin;
import com.cloudeggtech.chalk.xeps.muc.MucPlugin;

public abstract class StandardClient extends Client {

	public StandardClient(Demo demo, String clientName) {
		super(demo, clientName);
	}

	@Override
	protected void registerPlugins() {
		chatClient.register(IbrPlugin.class);
		
		Properties properties = new Properties();
		properties.put("subscriptionProtocol", IInstantingMessager2.Protocol.STANDARD);
		properties.put("messageProtocol", IInstantingMessager2.Protocol.STANDARD);
		chatClient.register(InstantingMessagerPlugin2.class, properties);
		
		chatClient.register(MucPlugin.class);
	}

}

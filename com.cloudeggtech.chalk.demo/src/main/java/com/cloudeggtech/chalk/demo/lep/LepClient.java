package com.cloudeggtech.chalk.demo.lep;

import com.cloudeggtech.chalk.demo.Client;
import com.cloudeggtech.chalk.demo.Demo;
import com.cloudeggtech.chalk.leps.im.InstantingMessagerPlugin2;
import com.cloudeggtech.chalk.xeps.ibr.IbrPlugin;
import com.cloudeggtech.chalk.xeps.muc.MucPlugin;

public abstract class LepClient extends Client {

	public LepClient(Demo demo, String clientName) {
		super(demo, clientName);
	}

	protected void registerPlugins() {
		chatClient.register(IbrPlugin.class);
		chatClient.register(InstantingMessagerPlugin2.class);
		chatClient.register(MucPlugin.class);
	}

}

package com.cloudeggtech.chalk.xeps.rsm;

import java.util.Properties;

import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionTranslatorFactory;
import com.cloudeggtech.basalt.xeps.rsm.Set;
import com.cloudeggtech.chalk.IChatSystem;
import com.cloudeggtech.chalk.IPlugin;

public class RsmPlugin implements IPlugin {

	@Override
	public void init(IChatSystem chatSystem, Properties properties) {
		chatSystem.registerTranslator(Set.class,
				new NamingConventionTranslatorFactory<>(
						Set.class
				)
		);
	}

	@Override
	public void destroy(IChatSystem chatSystem) {
		chatSystem.unregisterTranslator(Set.class);
	}

}

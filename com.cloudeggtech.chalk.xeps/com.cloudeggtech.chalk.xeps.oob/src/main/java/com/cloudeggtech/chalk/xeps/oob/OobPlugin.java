package com.cloudeggtech.chalk.xeps.oob;

import java.util.Properties;

import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionTranslatorFactory;
import com.cloudeggtech.basalt.xeps.oob.IqOob;
import com.cloudeggtech.basalt.xeps.oob.XOob;
import com.cloudeggtech.chalk.IChatSystem;
import com.cloudeggtech.chalk.IPlugin;

public class OobPlugin implements IPlugin {

	@Override
	public void init(IChatSystem chatSystem, Properties properties) {
		chatSystem.registerTranslator(IqOob.class,
				new NamingConventionTranslatorFactory<>(
						IqOob.class
				)
		);
		
		chatSystem.registerTranslator(XOob.class,
				new NamingConventionTranslatorFactory<>(
						XOob.class
				)
		);
	}

	@Override
	public void destroy(IChatSystem chatSystem) {
		chatSystem.unregisterTranslator(XOob.class);
		chatSystem.unregisterTranslator(IqOob.class);
	}

}

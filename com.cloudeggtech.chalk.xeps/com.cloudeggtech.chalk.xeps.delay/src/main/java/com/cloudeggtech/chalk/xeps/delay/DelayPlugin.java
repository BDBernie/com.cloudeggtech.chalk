package com.cloudeggtech.chalk.xeps.delay;

import java.util.Properties;

import com.cloudeggtech.basalt.protocol.core.ProtocolChain;
import com.cloudeggtech.basalt.protocol.im.stanza.Message;
import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionParserFactory;
import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionTranslatorFactory;
import com.cloudeggtech.basalt.xeps.delay.Delay;
import com.cloudeggtech.chalk.IChatSystem;
import com.cloudeggtech.chalk.IPlugin;

public class DelayPlugin implements IPlugin {

	@Override
	public void init(IChatSystem chatSystem, Properties properties) {
		chatSystem.registerParser(
				ProtocolChain.first(Message.PROTOCOL).
				next(Delay.PROTOCOL),
				new NamingConventionParserFactory<>(
						Delay.class
				)
		);
		
		chatSystem.registerTranslator(
				Delay.class,
				new NamingConventionTranslatorFactory<>(
						Delay.class
				)
		);
	}

	@Override
	public void destroy(IChatSystem chatSystem) {
		chatSystem.unregisterTranslator(Delay.class);
		
		chatSystem.unregisterParser(
				ProtocolChain.first(Message.PROTOCOL).
				next(Delay.PROTOCOL)
		);
	}

}

package com.cloudeggtech.chalk.xeps.ping;

import java.util.Properties;

import com.cloudeggtech.basalt.protocol.core.ProtocolChain;
import com.cloudeggtech.basalt.protocol.core.stanza.Iq;
import com.cloudeggtech.basalt.protocol.oxm.parsers.SimpleObjectParserFactory;
import com.cloudeggtech.basalt.protocol.oxm.translators.SimpleObjectTranslatorFactory;
import com.cloudeggtech.basalt.xeps.ping.Ping;
import com.cloudeggtech.chalk.IChatSystem;
import com.cloudeggtech.chalk.IPlugin;

public class PingPlugin implements IPlugin {
	@Override
	public void init(IChatSystem chatSystem, Properties properties) {
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).next(Ping.PROTOCOL),
				new SimpleObjectParserFactory<>(Ping.PROTOCOL, Ping.class));
		chatSystem.registerTranslator(
				Ping.class,
				new SimpleObjectTranslatorFactory<>(Ping.class, Ping.PROTOCOL));
		
		chatSystem.registerApi(IPing.class, PingImpl.class, properties);			
	}

	@Override
	public void destroy(IChatSystem chatSystem) {
		chatSystem.unregisterApi(IPing.class);
		chatSystem.unregisterTranslator(Ping.class);
		chatSystem.unregisterParser(ProtocolChain.first(Iq.PROTOCOL).next(Ping.PROTOCOL));
	}

}

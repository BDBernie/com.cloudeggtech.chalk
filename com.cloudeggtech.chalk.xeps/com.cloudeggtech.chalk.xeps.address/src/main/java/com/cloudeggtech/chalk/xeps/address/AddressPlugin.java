package com.cloudeggtech.chalk.xeps.address;

import java.util.Properties;

import com.cloudeggtech.basalt.protocol.core.ProtocolChain;
import com.cloudeggtech.basalt.protocol.im.stanza.Message;
import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionParserFactory;
import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionTranslatorFactory;
import com.cloudeggtech.basalt.xeps.address.Addresses;
import com.cloudeggtech.chalk.IChatSystem;
import com.cloudeggtech.chalk.IPlugin;

public class AddressPlugin implements IPlugin {

	@Override
	public void init(IChatSystem chatSystem, Properties properties) {
		chatSystem.registerParser(
				ProtocolChain.first(Message.PROTOCOL).
				next(Addresses.PROTOCOL),
				new NamingConventionParserFactory<>(
						Addresses.class
				)
		);
		
		chatSystem.registerTranslator(
				Addresses.class,
				new NamingConventionTranslatorFactory<>(
						Addresses.class
				)
		);
	}

	@Override
	public void destroy(IChatSystem chatSystem) {
		chatSystem.unregisterTranslator(Addresses.class);
		
		chatSystem.unregisterParser(
				ProtocolChain.first(Message.PROTOCOL).
				next(Addresses.PROTOCOL)
		);
	}

}

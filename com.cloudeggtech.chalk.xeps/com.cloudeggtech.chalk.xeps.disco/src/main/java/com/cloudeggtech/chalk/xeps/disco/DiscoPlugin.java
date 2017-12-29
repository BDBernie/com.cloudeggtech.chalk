package com.cloudeggtech.chalk.xeps.disco;

import java.util.Properties;

import com.cloudeggtech.basalt.protocol.core.ProtocolChain;
import com.cloudeggtech.basalt.protocol.core.stanza.Iq;
import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionParserFactory;
import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionTranslatorFactory;
import com.cloudeggtech.basalt.xeps.disco.DiscoInfo;
import com.cloudeggtech.basalt.xeps.disco.DiscoItems;
import com.cloudeggtech.basalt.xeps.rsm.Set;
import com.cloudeggtech.basalt.xeps.xdata.XData;
import com.cloudeggtech.chalk.IChatSystem;
import com.cloudeggtech.chalk.IPlugin;
import com.cloudeggtech.chalk.xeps.rsm.RsmPlugin;

public class DiscoPlugin implements IPlugin {

	@Override
	public void init(IChatSystem chatSystem, Properties properties) {
		chatSystem.register(RsmPlugin.class);
		
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(DiscoInfo.PROTOCOL),
				new NamingConventionParserFactory<>(
						DiscoInfo.class)
		);
		
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(DiscoInfo.PROTOCOL).
				next(XData.PROTOCOL),
				new NamingConventionParserFactory<>(
						XData.class)
		);
		
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(DiscoItems.PROTOCOL),
				new NamingConventionParserFactory<>(
						DiscoItems.class
				)
		);
		
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
					next(DiscoItems.PROTOCOL).
					next(Set.PROTOCOL),
					new NamingConventionParserFactory<>(
							Set.class
					)
				);
		
		chatSystem.registerTranslator(
				DiscoInfo.class,
				new NamingConventionTranslatorFactory<>(
						DiscoInfo.class
				)
		);
		
		chatSystem.registerTranslator(
				DiscoItems.class,
				new NamingConventionTranslatorFactory<>(
						DiscoItems.class
				)
		);
		
		chatSystem.registerApi(IServiceDiscovery.class, ServiceDiscovery.class);
	}

	@Override
	public void destroy(IChatSystem chatSystem) {
		chatSystem.unregisterApi(IServiceDiscovery.class);
		
		chatSystem.unregisterTranslator(DiscoItems.class);
		chatSystem.unregisterTranslator(DiscoInfo.class);
		
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(DiscoItems.PROTOCOL).
				next(Set.PROTOCOL)
		);
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(DiscoItems.PROTOCOL).
				next(XData.PROTOCOL)
		);
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(DiscoItems.PROTOCOL)
		);
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(DiscoInfo.PROTOCOL)
		);
		
		chatSystem.unregister(RsmPlugin.class);
	}

}

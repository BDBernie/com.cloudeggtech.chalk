package com.cloudeggtech.chalk.leps.im;

import java.util.Properties;

import com.cloudeggtech.basalt.leps.im.message.traceable.Trace;
import com.cloudeggtech.basalt.leps.im.subscription.Subscribe;
import com.cloudeggtech.basalt.leps.im.subscription.Subscribed;
import com.cloudeggtech.basalt.leps.im.subscription.Unsubscribe;
import com.cloudeggtech.basalt.leps.im.subscription.Unsubscribed;
import com.cloudeggtech.basalt.protocol.core.ProtocolChain;
import com.cloudeggtech.basalt.protocol.core.stanza.Iq;
import com.cloudeggtech.basalt.protocol.im.stanza.Message;
import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionParserFactory;
import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionTranslatorFactory;
import com.cloudeggtech.basalt.protocol.oxm.parsers.SimpleObjectParserFactory;
import com.cloudeggtech.chalk.IChatSystem;
import com.cloudeggtech.chalk.IPlugin;
import com.cloudeggtech.chalk.im.InstantingMessagerPlugin;
import com.cloudeggtech.chalk.xeps.delay.DelayPlugin;

public class InstantingMessagerPlugin2 implements IPlugin {

	@Override
	public void init(IChatSystem chatSystem, Properties properties) {
		chatSystem.register(DelayPlugin.class);
		chatSystem.register(InstantingMessagerPlugin.class);
		
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(Subscribe.PROTOCOL),
				new NamingConventionParserFactory<>(
						Subscribe.class
				)
		);
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(Subscribed.PROTOCOL),
				new SimpleObjectParserFactory<>(
						Subscribed.PROTOCOL,
						Subscribed.class
				)
		);
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(Unsubscribe.PROTOCOL),
				new NamingConventionParserFactory<>(
						Unsubscribe.class
				)
		);
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(Unsubscribed.PROTOCOL),
				new NamingConventionParserFactory<>(
						Unsubscribed.class
				)
		);
		chatSystem.registerParser(
				ProtocolChain.first(Message.PROTOCOL).
				next(Trace.PROTOCOL),
				new NamingConventionParserFactory<>(
						Trace.class
				)
		);
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(Trace.PROTOCOL),
				new NamingConventionParserFactory<>(
						Trace.class
				)
		);
		
		chatSystem.registerTranslator(
				Subscribe.class,
				new NamingConventionTranslatorFactory<>(
						Subscribe.class
				)
		);
		
		chatSystem.registerTranslator(
				Subscribed.class,
				new NamingConventionTranslatorFactory<>(
						Subscribed.class
				)
		);
		chatSystem.registerTranslator(
				Unsubscribe.class,
				new NamingConventionTranslatorFactory<>(
						Unsubscribe.class
				)
		);
		chatSystem.registerTranslator(
				Unsubscribed.class,
				new NamingConventionTranslatorFactory<>(
						Unsubscribed.class
				)
		);
		chatSystem.registerTranslator(
				Trace.class,
				new NamingConventionTranslatorFactory<>(
						Trace.class
				)
		);
		
		chatSystem.registerApi(IInstantingMessager2.class, InstantingMessager2.class, properties);
	}

	@Override
	public void destroy(IChatSystem chatSystem) {
		chatSystem.unregisterApi(IInstantingMessager2.class);
		
		chatSystem.unregisterTranslator(Trace.class);
		chatSystem.unregisterTranslator(Unsubscribed.class);
		chatSystem.unregisterTranslator(Unsubscribe.class);
		chatSystem.unregisterTranslator(Subscribed.class);
		chatSystem.unregisterTranslator(Subscribe.class);
		
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(Trace.PROTOCOL));
		chatSystem.unregisterParser(
				ProtocolChain.first(Message.PROTOCOL).
				next(Trace.PROTOCOL));
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(Unsubscribed.PROTOCOL));
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(Unsubscribe.PROTOCOL));
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(Subscribed.PROTOCOL));
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(Subscribe.PROTOCOL));
		
		chatSystem.unregister(InstantingMessagerPlugin.class);
		chatSystem.unregister(DelayPlugin.class);
	}

}

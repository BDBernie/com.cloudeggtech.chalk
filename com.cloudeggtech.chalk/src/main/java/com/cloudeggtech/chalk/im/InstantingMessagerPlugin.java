package com.cloudeggtech.chalk.im;

import java.util.Properties;

import com.cloudeggtech.basalt.protocol.core.ProtocolChain;
import com.cloudeggtech.basalt.protocol.core.stanza.Iq;
import com.cloudeggtech.basalt.protocol.im.roster.Roster;
import com.cloudeggtech.basalt.protocol.im.roster.RosterParser;
import com.cloudeggtech.basalt.protocol.im.roster.RosterTranslatorFactory;
import com.cloudeggtech.basalt.protocol.oxm.annotation.AnnotatedParserFactory;
import com.cloudeggtech.chalk.IChatSystem;
import com.cloudeggtech.chalk.IPlugin;

public class InstantingMessagerPlugin implements IPlugin {

	@Override
	public void init(IChatSystem chatSystem, Properties properties) {
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(Roster.PROTOCOL),
				new AnnotatedParserFactory<>(RosterParser.class));
		chatSystem.registerTranslator(
				Roster.class,
				new RosterTranslatorFactory());
		chatSystem.registerApi(IInstantingMessager.class, InstantingMessager.class);
	}

	@Override
	public void destroy(IChatSystem chatSystem) {
		chatSystem.unregisterApi(IInstantingMessager.class);
		chatSystem.unregisterTranslator(Roster.class);
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(Roster.PROTOCOL));
	}

}

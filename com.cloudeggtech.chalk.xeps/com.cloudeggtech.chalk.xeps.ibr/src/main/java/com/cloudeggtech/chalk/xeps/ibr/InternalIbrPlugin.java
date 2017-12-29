package com.cloudeggtech.chalk.xeps.ibr;

import java.util.Properties;

import com.cloudeggtech.basalt.protocol.core.ProtocolChain;
import com.cloudeggtech.basalt.protocol.core.stanza.Iq;
import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionParserFactory;
import com.cloudeggtech.basalt.xeps.ibr.IqRegister;
import com.cloudeggtech.basalt.xeps.ibr.oxm.IqRegisterParserFactory;
import com.cloudeggtech.basalt.xeps.ibr.oxm.IqRegisterTranslatorFactory;
import com.cloudeggtech.basalt.xeps.oob.XOob;
import com.cloudeggtech.basalt.xeps.xdata.XData;
import com.cloudeggtech.chalk.IChatSystem;
import com.cloudeggtech.chalk.IPlugin;
import com.cloudeggtech.chalk.xeps.oob.OobPlugin;
import com.cloudeggtech.chalk.xeps.xdata.XDataPlugin;

public class InternalIbrPlugin implements IPlugin {
	@Override
	public void init(IChatSystem chatSystem, Properties properties) {
		chatSystem.register(OobPlugin.class);
		chatSystem.register(XDataPlugin.class);
		
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(IqRegister.PROTOCOL),
				new IqRegisterParserFactory()
		);
		
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(IqRegister.PROTOCOL).
				next(XData.PROTOCOL),
				new NamingConventionParserFactory<>(
						XData.class
				)
		);
		
		chatSystem.registerParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(IqRegister.PROTOCOL).
				next(XOob.PROTOCOL),
				new NamingConventionParserFactory<>(
						XOob.class
				)
		);
		
		chatSystem.registerTranslator(
				IqRegister.class,
				new IqRegisterTranslatorFactory()
		);
	}

	@Override
	public void destroy(IChatSystem chatSystem) {
		chatSystem.unregisterTranslator(IqRegister.class);
		
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(IqRegister.PROTOCOL)
		);
		
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(IqRegister.PROTOCOL).
				next(XData.PROTOCOL)
		);
		
		chatSystem.unregisterParser(
				ProtocolChain.first(Iq.PROTOCOL).
				next(IqRegister.PROTOCOL).
				next(XOob.PROTOCOL)
		);
		
		chatSystem.unregister(XDataPlugin.class);
		chatSystem.unregister(OobPlugin.class);
	}
}

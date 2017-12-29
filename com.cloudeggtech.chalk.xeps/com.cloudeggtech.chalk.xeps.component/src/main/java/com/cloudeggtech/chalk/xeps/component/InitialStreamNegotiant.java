package com.cloudeggtech.chalk.xeps.component;

import com.cloudeggtech.basalt.protocol.Constants;
import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stream.Stream;
import com.cloudeggtech.basalt.protocol.oxm.IOxmFactory;
import com.cloudeggtech.basalt.protocol.oxm.OxmService;
import com.cloudeggtech.chalk.core.stream.INegotiationContext;
import com.cloudeggtech.chalk.core.stream.NegotiationException;
import com.cloudeggtech.chalk.core.stream.negotiants.AbstractStreamNegotiant;
import com.cloudeggtech.chalk.network.ConnectionException;

public class InitialStreamNegotiant extends AbstractStreamNegotiant {
	private static final String COMPONENT_DEFAULT_NAMESPACE = "jabber:component:accept";
	private static IOxmFactory oxmFactory = OxmService.createStreamOxmFactory();
	
	private String componentName;
	private String lang;
	
	public InitialStreamNegotiant(String componentName, String lang) {
		this.componentName = componentName;
		this.lang = lang;
	}
	
	@Override
	protected void doNegotiate(INegotiationContext context) throws ConnectionException, NegotiationException {
		JabberId componentJid = JabberId.parse(componentName);
		if (componentJid.getResource() != null && componentJid.getName() != null) {
			throw new IllegalArgumentException(String.format("Illegal component jid: %s.", componentJid));
		}
		
		Stream openStream = new Stream();
		openStream.setDefaultNamespace(COMPONENT_DEFAULT_NAMESPACE);
		openStream.setTo(componentJid);
		openStream.setLang(lang);
		openStream.setVersion(Constants.SPECIFICATION_VERSION);
		
		context.write(oxmFactory.translate(openStream));
	}

}

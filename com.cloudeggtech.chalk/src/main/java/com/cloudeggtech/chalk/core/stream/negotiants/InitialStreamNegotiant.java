package com.cloudeggtech.chalk.core.stream.negotiants;

import java.util.List;

import com.cloudeggtech.basalt.protocol.Constants;
import com.cloudeggtech.basalt.protocol.core.IError;
import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.ProtocolChain;
import com.cloudeggtech.basalt.protocol.core.stream.Feature;
import com.cloudeggtech.basalt.protocol.core.stream.Features;
import com.cloudeggtech.basalt.protocol.core.stream.Stream;
import com.cloudeggtech.basalt.protocol.core.stream.sasl.Mechanisms;
import com.cloudeggtech.basalt.protocol.core.stream.tls.StartTls;
import com.cloudeggtech.basalt.protocol.oxm.IOxmFactory;
import com.cloudeggtech.basalt.protocol.oxm.OxmService;
import com.cloudeggtech.basalt.protocol.oxm.annotation.AnnotatedParserFactory;
import com.cloudeggtech.basalt.protocol.oxm.parsers.core.stream.FeaturesParser;
import com.cloudeggtech.basalt.protocol.oxm.parsers.core.stream.sasl.MechanismsParser;
import com.cloudeggtech.basalt.protocol.oxm.parsers.core.stream.tls.StartTlsParser;
import com.cloudeggtech.chalk.core.stream.INegotiationContext;
import com.cloudeggtech.chalk.core.stream.NegotiationException;
import com.cloudeggtech.chalk.core.stream.StandardStreamer;
import com.cloudeggtech.chalk.network.ConnectionException;

public class InitialStreamNegotiant extends AbstractStreamNegotiant {
	protected static IOxmFactory oxmFactory = OxmService.createStreamOxmFactory();
	
	static {
		oxmFactory.register(ProtocolChain.first(Features.PROTOCOL),
				new AnnotatedParserFactory<>(FeaturesParser.class));
		oxmFactory.register(ProtocolChain.first(Features.PROTOCOL).next(StartTls.PROTOCOL),
				new AnnotatedParserFactory<>(StartTlsParser.class));
		oxmFactory.register(ProtocolChain.first(Features.PROTOCOL).next(Mechanisms.PROTOCOL),
				new AnnotatedParserFactory<>(MechanismsParser.class));
	}
	
	protected String hostName;
	protected String lang;
	
	public InitialStreamNegotiant(String hostName, String lang) {
		this.hostName = hostName;
		this.lang = lang;
	}

	@Override
	protected void doNegotiate(INegotiationContext context) throws ConnectionException, NegotiationException {
		Stream openStream = new Stream();
		openStream.setDefaultNamespace(Constants.C2S_DEFAULT_NAMESPACE);
		openStream.setTo(JabberId.parse(hostName));
		openStream.setLang(lang);
		openStream.setVersion(Constants.SPECIFICATION_VERSION);
		
		context.write(oxmFactory.translate(openStream));
		
		openStream = (Stream)oxmFactory.parse(readResponse());
		
		Object response = oxmFactory.parse(readResponse());
		if (response instanceof Features) {
			List<Feature> features = ((Features)response).getFeatures();
			context.setAttribute(StandardStreamer.NEGOTIATION_KEY_FEATURES, features);
		} else {
			processError((IError)response, context, oxmFactory);
		}

	}

}

package com.cloudeggtech.chalk.xeps.component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.stream.Stream;
import com.cloudeggtech.basalt.protocol.oxm.IOxmFactory;
import com.cloudeggtech.basalt.protocol.oxm.OxmService;
import com.cloudeggtech.chalk.core.stream.INegotiationContext;
import com.cloudeggtech.chalk.core.stream.NegotiationException;
import com.cloudeggtech.chalk.core.stream.StandardStreamer;
import com.cloudeggtech.chalk.core.stream.negotiants.AbstractStreamNegotiant;
import com.cloudeggtech.chalk.network.ConnectionException;

public class HandshakeNegotiant extends AbstractStreamNegotiant {
	private static IOxmFactory oxmFactory = OxmService.createStreamOxmFactory();
	private String component;
	private String secret;
	
	public HandshakeNegotiant(String component, String secret) {
		this.component = component;
		this.secret = secret;
	}

	@Override
	protected void doNegotiate(INegotiationContext context) throws ConnectionException, NegotiationException {
		Stream openStream = (Stream)oxmFactory.parse(readResponse());
		String sid = openStream.getId();
		
		if (sid == null) {
			throw new NegotiationException("Null stream id.", this, null);
		}
		
		String sidAndSecret = sid + secret;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA1");
			byte[] hash = digest.digest(sidAndSecret.getBytes());
			
			String credentials = new BigInteger(1, hash).toString(16);
			context.write(String.format("<handshake>%s</handshake>", credentials));
			
			String response = readResponse();
			
			if ("<handshake/>".equals(response)) {
				context.setAttribute(StandardStreamer.NEGOTIATION_KEY_BINDED_CHAT_ID, JabberId.parse(component));
				return;
			}
			
			 throw new NegotiationException(this, oxmFactory.parse(response));
		} catch (NoSuchAlgorithmException e) {
			throw new NegotiationException("SHA1 algorithm not supported.", this, e);
		}
	}

}

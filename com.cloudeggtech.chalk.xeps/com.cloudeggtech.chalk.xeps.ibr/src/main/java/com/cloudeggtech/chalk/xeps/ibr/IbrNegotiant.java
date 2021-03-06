package com.cloudeggtech.chalk.xeps.ibr;

import java.util.List;

import com.cloudeggtech.basalt.protocol.core.stream.Feature;
import com.cloudeggtech.basalt.xeps.ibr.Register;
import com.cloudeggtech.chalk.core.stream.AbstractStreamer;
import com.cloudeggtech.chalk.core.stream.INegotiationContext;
import com.cloudeggtech.chalk.core.stream.NegotiationException;
import com.cloudeggtech.chalk.core.stream.negotiants.AbstractStreamNegotiant;
import com.cloudeggtech.chalk.network.ConnectionException;

public class IbrNegotiant extends AbstractStreamNegotiant {
	
	
	@Override
	protected void doNegotiate(INegotiationContext context) throws ConnectionException, NegotiationException {
		@SuppressWarnings("unchecked")
		List<Feature> features = (List<Feature>)context.getAttribute(AbstractStreamer.NEGOTIATION_KEY_FEATURES);
		
		for (Feature feature : features) {
			if (feature instanceof Register) {
				return;
			}
		}
		
		throw new NegotiationException(this, IbrError.NOT_SUPPORTED);
	}

}

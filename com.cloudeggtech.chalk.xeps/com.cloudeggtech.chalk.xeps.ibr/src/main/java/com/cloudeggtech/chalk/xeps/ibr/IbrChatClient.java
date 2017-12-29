package com.cloudeggtech.chalk.xeps.ibr;

import java.util.ArrayList;
import java.util.List;

import javax.security.cert.X509Certificate;

import com.cloudeggtech.basalt.protocol.core.ProtocolChain;
import com.cloudeggtech.basalt.protocol.core.stream.Features;
import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionParserFactory;
import com.cloudeggtech.basalt.xeps.ibr.Register;
import com.cloudeggtech.chalk.AbstractChatClient;
import com.cloudeggtech.chalk.core.stream.AbstractStreamer;
import com.cloudeggtech.chalk.core.stream.IStreamNegotiant;
import com.cloudeggtech.chalk.core.stream.IStreamer;
import com.cloudeggtech.chalk.core.stream.StandardStreamConfig;
import com.cloudeggtech.chalk.core.stream.StreamConfig;
import com.cloudeggtech.chalk.core.stream.negotiants.InitialStreamNegotiant;
import com.cloudeggtech.chalk.core.stream.negotiants.tls.IPeerCertificateTruster;
import com.cloudeggtech.chalk.core.stream.negotiants.tls.TlsNegotiant;
import com.cloudeggtech.chalk.network.IConnection;

class IbrChatClient extends AbstractChatClient {
	
	private IPeerCertificateTruster peerCertificateTruster;

	public IbrChatClient(StreamConfig streamConfig) {
		super(streamConfig);
	}
	
	public void setPeerCertificateTruster(IPeerCertificateTruster peerCertificateTruster) {
		this.peerCertificateTruster = peerCertificateTruster;
	}

	public IPeerCertificateTruster getPeerCertificateTruster() {
		return peerCertificateTruster;
	}

	@Override
	protected IStreamer createStreamer(StreamConfig streamConfig) {
		IbrStreamer streamer = new IbrStreamer(getStreamConfig());
		streamer.setConnectionListener(this);
		streamer.setNegotiationListener(this);
		
		if (peerCertificateTruster != null) {
			streamer.setPeerCertificateTruster(peerCertificateTruster);
		} else {
			// always trust peer certificate
			streamer.setPeerCertificateTruster(new IPeerCertificateTruster() {				
				@Override
				public boolean accept(X509Certificate[] certificates) {
					return true;
				}
			});
		}
		
		return streamer;
	}

	private class IbrStreamer extends AbstractStreamer {
		private IPeerCertificateTruster certificateTruster;
		
		public IbrStreamer(StreamConfig streamConfig) {
			this(streamConfig, null);
		}
		
		public IbrStreamer(StreamConfig streamConfig, IConnection connection) {
			super(streamConfig, connection);
		}
		
		@Override
		protected List<IStreamNegotiant> createNegotiants() {
			List<IStreamNegotiant> negotiants = new ArrayList<>();
			
			InitialStreamNegotiant initialStreamNegotiant = createIbrSupportedInitialStreamNegotiant();
			negotiants.add(initialStreamNegotiant);
			
			TlsNegotiant tls = createIbrSupportedTlsNegotiant();
			negotiants.add(tls);
			
			IbrNegotiant ibr = createIbrNegotiant();
			negotiants.add(ibr);
			
			setNegotiationReadResponseTimeout(negotiants);
			
			return negotiants;
		}

		private IbrNegotiant createIbrNegotiant() {
			return new IbrNegotiant();
		}
		
		public void setPeerCertificateTruster(IPeerCertificateTruster certificateTruster) {
			this.certificateTruster = certificateTruster;
		}

		private InitialStreamNegotiant createIbrSupportedInitialStreamNegotiant() {
			return new IbrSupportedInitialStreamNegotiant(streamConfig.getHost(), streamConfig.getLang());
		}
		
		private TlsNegotiant createIbrSupportedTlsNegotiant() {
			TlsNegotiant tls = new IbrSupportedTlsNegotiant(streamConfig.getHost(), streamConfig.getLang(),
					((StandardStreamConfig)streamConfig).isTlsPreferred());
			tls.setPeerCertificateTruster(certificateTruster);
			return tls;
		}
	}
	
	private static class IbrSupportedInitialStreamNegotiant extends InitialStreamNegotiant {
		
		static {
			oxmFactory.register(ProtocolChain.first(Features.PROTOCOL).next(Register.PROTOCOL),
					new NamingConventionParserFactory<>(Register.class));
		}

		public IbrSupportedInitialStreamNegotiant(String hostName, String lang) {
			super(hostName, lang);
		}
		
	}
	
	private static class IbrSupportedTlsNegotiant extends TlsNegotiant {
		
		static {
			oxmFactory.register(ProtocolChain.first(Features.PROTOCOL).next(Register.PROTOCOL),
					new NamingConventionParserFactory<>(Register.class));
		}

		public IbrSupportedTlsNegotiant(String hostName, String lang, boolean tlsPreferred) {
			super(hostName, lang, tlsPreferred);
		}
		
	}
	
}

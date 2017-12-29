package com.cloudeggtech.chalk.core.stream;

import com.cloudeggtech.chalk.core.stream.negotiants.tls.IPeerCertificateTruster;

public interface IStandardStreamer extends IStreamer {
	void setPeerCertificateTruster(IPeerCertificateTruster certificateTruster);
	IPeerCertificateTruster getPeerCertificateTruster();
}

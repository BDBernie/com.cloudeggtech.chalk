package com.cloudeggtech.chalk.xeps.xdata;

import java.util.Properties;

import com.cloudeggtech.basalt.protocol.oxm.convention.NamingConventionTranslatorFactory;
import com.cloudeggtech.basalt.xeps.xdata.XData;
import com.cloudeggtech.chalk.IChatSystem;
import com.cloudeggtech.chalk.IPlugin;

public class XDataPlugin implements IPlugin {

	@Override
	public void init(IChatSystem chatSystem, Properties properties) {
		chatSystem.registerTranslator(XData.class,
				new NamingConventionTranslatorFactory<>(
						XData.class
				)
		);
	}

	@Override
	public void destroy(IChatSystem chatSystem) {
		chatSystem.unregisterTranslator(XData.class);
	}

}

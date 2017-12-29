package com.cloudeggtech.chalk.demo.standard;

import com.cloudeggtech.chalk.core.ErrorException;
import com.cloudeggtech.chalk.core.stream.StandardStreamConfig;
import com.cloudeggtech.chalk.demo.Demo;
import com.cloudeggtech.chalk.xeps.muc.events.Invitation;
import com.cloudeggtech.chalk.xeps.muc.events.InvitationEvent;
import com.cloudeggtech.chalk.xeps.muc.events.RoomEvent;

public class JellyMobile extends StandardClient {

	public JellyMobile(Demo demo) {
		super(demo, "Jelly/mobile");
	}

	@Override
	protected StandardStreamConfig getStreamConfig() {
		StandardStreamConfig config = new StandardStreamConfig(host, port);
		config.setResource("mobile");
		
		return config;
	}

	@Override
	protected String[] getUserNameAndPassword() {
		return new String[] {"jelly", "another_pretty_girl"};
	}
	
	@Override
	public void received(RoomEvent<?> event) {
		super.received(event);
		
		if (event instanceof InvitationEvent) {
			Invitation invitation = (Invitation)event.getEventObject();
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				joinRoom(invitation);
			} catch (ErrorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

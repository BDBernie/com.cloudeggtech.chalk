package com.cloudeggtech.chalk.demo.standard;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.chalk.core.ErrorException;
import com.cloudeggtech.chalk.core.stream.StandardStreamConfig;
import com.cloudeggtech.chalk.demo.Demo;
import com.cloudeggtech.chalk.xeps.disco.IServiceDiscovery;
import com.cloudeggtech.chalk.xeps.muc.IRoom;
import com.cloudeggtech.chalk.xeps.muc.events.Invitation;
import com.cloudeggtech.chalk.xeps.muc.events.InvitationEvent;
import com.cloudeggtech.chalk.xeps.muc.events.RoomEvent;
import com.cloudeggtech.chalk.xeps.muc.events.RoomMessageEvent;

public class DonggerOffice extends StandardClient {

	public DonggerOffice(Demo demo) {
		super(demo, "Dongger/office");
	}

	@Override
	protected StandardStreamConfig getStreamConfig() {
		StandardStreamConfig config = new StandardStreamConfig(host, port);
		config.setResource("office");
		config.setTlsPreferred(true);
		
		return config;
	}

	@Override
	protected String[] getUserNameAndPassword() {
		return new String[] {"dongger", "a_stupid_man"};
	}
	
	@Override
	public void approved(JabberId contact) {
		super.approved(contact);
		
		IServiceDiscovery disco = chatClient.createApi(IServiceDiscovery.class);
		
		try {
			if (disco.discoImServer()) {
				print("IM server discovered.");
			}
			
			JabberId agilestJid = JabberId.parse("agilest@" + chatClient.getStreamConfig().getHost());
			if (disco.discoAccount(agilestJid)) {
				print(String.format("Registered account %s discovered.", agilestJid));
			}
			
			JabberId[] availableResources = disco.discoAvailableResources(agilestJid);
			if (availableResources != null && availableResources.length > 0) {
				for (JabberId availableResource : availableResources) {
					print(String.format("Avaialbe resource %s for %s discovered.", availableResource,
							availableResource.getBareIdString()));
				}
			}
			
		} catch (ErrorException e) {
			throw new RuntimeException("Disco error.", e);
		}
	}
	
	@Override
	public void received(RoomEvent<?> event) {
		super.received(event);
		
		if (event instanceof InvitationEvent) {
			Invitation invitation = (Invitation)event.getEventObject();
			
			if (!invitation.getInvitor().getName().equals("smartsheep"))
				return;
			
			try {
				Thread.sleep(500);
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
		} else if (event instanceof RoomMessageEvent) {
			RoomMessageEvent messageEvent = ((RoomMessageEvent)event);
			if ("room0605".equals(messageEvent.getRoomJid().getName()) &&
					"smartsheep".equals(messageEvent.getEventObject().getNick())) {	
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				IRoom room = muc.getRoom(new JabberId("room0605", mucHost.getDomain()));
				try {
					room.changeNick("yangdong");
				} catch (ErrorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

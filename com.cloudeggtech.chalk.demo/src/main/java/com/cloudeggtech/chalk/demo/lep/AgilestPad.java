package com.cloudeggtech.chalk.demo.lep;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.im.roster.Item;
import com.cloudeggtech.basalt.protocol.im.roster.Roster;
import com.cloudeggtech.basalt.protocol.im.stanza.Message;
import com.cloudeggtech.basalt.protocol.im.stanza.Presence;
import com.cloudeggtech.basalt.xeps.muc.RoomConfig;
import com.cloudeggtech.basalt.xeps.muc.RoomConfig.WhoIs;
import com.cloudeggtech.chalk.core.ErrorException;
import com.cloudeggtech.chalk.core.stream.StandardStreamConfig;
import com.cloudeggtech.chalk.demo.Demo;
import com.cloudeggtech.chalk.xeps.muc.IRoom;
import com.cloudeggtech.chalk.xeps.muc.StandardRoomConfigurator;
import com.cloudeggtech.chalk.xeps.muc.events.EnterEvent;
import com.cloudeggtech.chalk.xeps.muc.events.Invitation;
import com.cloudeggtech.chalk.xeps.muc.events.InvitationEvent;
import com.cloudeggtech.chalk.xeps.muc.events.RoomEvent;
import com.cloudeggtech.chalk.xeps.muc.events.RoomMessageEvent;

public class AgilestPad extends LepClient {

	public AgilestPad(Demo demo) {
		super(demo, "Agilest/pad");
	}

	@Override
	protected StandardStreamConfig getStreamConfig() {
		StandardStreamConfig config = new StandardStreamConfig(host, port);
		config.setResource("pad");
		config.setTlsPreferred(true);
		
		return config;
	}

	@Override
	protected String[] getUserNameAndPassword() {
		return new String[] {"agilest", "a_good_guy"};
	}
	
	@Override
	public void updated(Roster roster) {
		super.updated(roster);
		
		Item item = roster.getItem(new JabberId("dongger", host));
		if (item == null || item.getSubscription() != Item.Subscription.BOTH)
			return;
		
		setPresenceAndSendMessageToDongger();
	}

	private void setPresenceAndSendMessageToDongger() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Presence presence = new Presence();
		presence.setShow(Presence.Show.CHAT);
		presence.setPriority(64);
		
		im.send(presence);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		presence = new Presence();
		presence.setShow(Presence.Show.AWAY);
		presence.setTo(BARE_JID_DONGGER);
		
		im.send(presence);
		
		Message message = new Message("Hello, Dongger!");
		
		message.setTo(JID_DONGGER_HOME);
		
		im.send(message);
	}
	
	@Override
	public void received(Message message) {
		super.received(message);
		
		demo.startClient(this.getClass(), SmartSheepMobile.class);
		demo.startClient(this.getClass(), JellyMobile.class);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			IRoom room = muc.createReservedRoom(new JabberId("first_room_of_agilest", mucHost.getDomain()), "agilest",
					new StandardRoomConfigurator() {
						
						@Override
						protected RoomConfig configure(RoomConfig roomConfig) {
							roomConfig.setRoomName("Agilest's first room");
							roomConfig.setRoomDesc("Hope you have happy hours here!");
							roomConfig.setMembersOnly(true);
							roomConfig.setAllowInvites(true);
							roomConfig.setPasswordProtectedRoom(true);
							roomConfig.setRoomSecret("simple");
							roomConfig.getGetMemberList().setParticipant(false);
							roomConfig.getGetMemberList().setVisitor(false);
							roomConfig.setWhoIs(WhoIs.MODERATORS);
							roomConfig.setModeratedRoom(true);
							
							return roomConfig;
						}
					});
			
			room.invite(BARE_JID_SMARTSHEEP, "Let's discuss our plan.");
		} catch (ErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void received(RoomEvent<?> event) {
		super.received(event);
		
		if (event instanceof InvitationEvent) {
			Invitation invitation = (Invitation)event.getEventObject();
			try {
				Thread.sleep(200);
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
		} else if (event instanceof EnterEvent) {
			EnterEvent enterEvent = (EnterEvent)event;
			if ("first_room_of_agilest".equals(enterEvent.getRoomJid().getName()) &&
					enterEvent.getEventObject().getNick().equals("smartsheep")) {
				IRoom room = muc.getRoom(new JabberId("first_room_of_agilest", mucHost.getDomain()));
				room.send(new Message("Hello, Smartsheep!"));
			}
		} else if (event instanceof RoomMessageEvent) {
			RoomMessageEvent messageEvent = ((RoomMessageEvent)event);
			if ("first_room_of_agilest".equals(messageEvent.getRoomJid().getName()) &&
					"smartsheep".equals(messageEvent.getEventObject().getNick()) &&
					"Hello, Agilest!".equals(messageEvent.getEventObject().getMessage())) {
				IRoom room = muc.getRoom(messageEvent.getRoomJid());
				room.setSubject("Let's discuss our plan.");
				room.send(new Message("Waiting for a minute. I will invite your brother to join the room."));
				
				try {
					room.invite(BARE_JID_DONGGER, "Let's discuss our plan.");
				} catch (ErrorException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} if ("room0605".equals(messageEvent.getRoomJid().getName()) &&
					"smartsheep".equals(messageEvent.getEventObject().getNick()) &&
						"Hi, guys!".equals(messageEvent.getEventObject().getMessage())) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				IRoom room = muc.getRoom(messageEvent.getRoomJid());
				room.exit();
				
				im.send(JID_DONGGER_HOME, new Message("I'll leave for a while."));
				
			} else if ("first_room_of_agilest".equals(messageEvent.getRoomJid().getName()) &&
					"dongger".equals(messageEvent.getEventObject().getNick())) {
				IRoom room = muc.getRoom(messageEvent.getRoomJid());
				room.send(new Message("Hello, Dongger!"));
			}
		}
	}

}

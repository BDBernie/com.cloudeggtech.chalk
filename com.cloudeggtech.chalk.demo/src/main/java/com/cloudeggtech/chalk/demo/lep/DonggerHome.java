package com.cloudeggtech.chalk.demo.lep;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.core.LangText;
import com.cloudeggtech.basalt.protocol.im.roster.Roster;
import com.cloudeggtech.basalt.protocol.im.stanza.Message;
import com.cloudeggtech.basalt.protocol.im.stanza.Presence;
import com.cloudeggtech.basalt.protocol.im.stanza.Presence.Show;
import com.cloudeggtech.chalk.AuthFailureException;
import com.cloudeggtech.chalk.core.ErrorException;
import com.cloudeggtech.chalk.core.stream.StandardStreamConfig;
import com.cloudeggtech.chalk.demo.Demo;
import com.cloudeggtech.chalk.network.ConnectionException;
import com.cloudeggtech.chalk.xeps.muc.IRoom;
import com.cloudeggtech.chalk.xeps.muc.events.EnterEvent;
import com.cloudeggtech.chalk.xeps.muc.events.Invitation;
import com.cloudeggtech.chalk.xeps.muc.events.InvitationEvent;
import com.cloudeggtech.chalk.xeps.muc.events.RoomEvent;
import com.cloudeggtech.chalk.xeps.muc.events.RoomMessageEvent;
import com.cloudeggtech.chalk.xeps.ping.IPing;
import com.cloudeggtech.chalk.xeps.ping.PingPlugin;

public class DonggerHome extends LepClient {

	public DonggerHome(Demo demo) {
		super(demo, "Dongger/home");
	}
	
/*	@Override
	public void received(String message) {
		print(String.format("received: %s", message));
	}

	@Override
	public void sent(String message) {
		print(String.format("sent: %s", message));
	}*/

	@Override
	public void retrieved(Roster roster) {
		super.retrieved(roster);
		
		if (getRunCount().intValue() == 1) {
			ping();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			im.getSubscriptionService().subscribe(BARE_JID_AGILEST, "Hello!");
			
			demo.startClient(this.getClass(), AgilestMobile.class);
		}
	}
	
	@Override
	public void approved(JabberId contact) {
		super.approved(contact);
		
		setPresence();
	}

	private void setPresence() {
		Presence presence = new Presence();
		presence.setShow(Show.DND);
		presence.getStatuses().add(new LangText("I'm in a meeting."));
		
		im.send(presence);
	}
	
	@Override
	public void refused(JabberId contact, String reason) {
		super.refused(contact, reason);
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		demo.stopClient(this.getClass(), AgilestMobile.class);
		
		im.getSubscriptionService().subscribe(BARE_JID_AGILEST, "I'm Dongger!");
		
		demo.startClient(this.getClass(), AgilestMobile.class);
		demo.startClient(this.getClass(), AgilestPad.class);
	}

	private void ping() {
		IPing ping = chatClient.createApi(IPing.class);
		
		IPing.Result result = ping.ping();
		print(String.format("ping result: %s", result));
	}

	@Override
	protected StandardStreamConfig getStreamConfig() {
		StandardStreamConfig config = new StandardStreamConfig(host, port);
		config.setResource("home");
		config.setTlsPreferred(true);
		
		return config;
	}
	
	@Override
	protected void registerPlugins() {
		super.registerPlugins();
		
		chatClient.register(PingPlugin.class);
	}

	@Override
	protected String[] getUserNameAndPassword() {
		return new String[] {"dongger", "a_clever_man"};
	}
	
	@Override
	protected void processAuthFailure(AuthFailureException e) throws AuthFailureException, ConnectionException {
		chatClient.connect("dongger", "a_stupid_man");
	}
	
	@Override
	public void received(Message message) {
		super.received(message);
		
		if (!JID_AGILEST_PAD.equals(message.getFrom())) {
			return;
		}
		
		if (message.getBodies().get(0).getText().indexOf("Hello") != -1) {
			Message reply = new Message("Hello, Agilest!");
			reply.setTo(BARE_JID_AGILEST);
			
			im.send(reply);
		} else {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			demo.stopClient(this.getClass(), AgilestMobile.class);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			demo.stopClient(this.getClass(), AgilestPad.class);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			demo.stopClient(this.getClass(), SmartSheepMobile.class);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			demo.stopClient(this.getClass(), JellyMobile.class);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			im.sendMessageReadAck(JID_AGILEST_MOBILE, message.getId());
			
			im.send(JID_AGILEST_MOBILE, new Message("Are you still online?"));
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			demo.startClient(this.getClass(), AgilestMobile.class);
		}
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
		}  else if (event instanceof EnterEvent) {
			EnterEvent enterEvent = (EnterEvent)event;
			if ("first_room_of_agilest".equals(enterEvent.getRoomJid().getName()) &&
					"dongger".equals(enterEvent.getEventObject().getNick()) &&
					enterEvent.getEventObject().getSessions() == 1) {
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				IRoom room = muc.getRoom(new JabberId("first_room_of_agilest", mucHost.getDomain()));
				room.send(new Message("Hello, everyone!"));
			}
		} else if (event instanceof RoomMessageEvent) {
			RoomMessageEvent messageEvent = ((RoomMessageEvent)event);
			if ("first_room_of_agilest".equals(messageEvent.getRoomJid().getName()) &&
					"agilest".equals(messageEvent.getEventObject().getNick())) {
			
				IRoom room = muc.getRoom(new JabberId("first_room_of_agilest", mucHost.getDomain()));
				room.send("smartsheep", new Message("How has my nephew been recently?"));
				
				room.send(new Presence(Presence.Show.AWAY));
			}
		}
	}
}

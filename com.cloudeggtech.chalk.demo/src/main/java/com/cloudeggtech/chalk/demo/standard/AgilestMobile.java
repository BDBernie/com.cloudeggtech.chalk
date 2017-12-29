package com.cloudeggtech.chalk.demo.standard;

import com.cloudeggtech.basalt.protocol.core.JabberId;
import com.cloudeggtech.basalt.protocol.im.roster.Item;
import com.cloudeggtech.basalt.protocol.im.roster.Roster;
import com.cloudeggtech.basalt.protocol.im.stanza.Message;
import com.cloudeggtech.chalk.core.ErrorException;
import com.cloudeggtech.chalk.core.stream.StandardStreamConfig;
import com.cloudeggtech.chalk.demo.Demo;
import com.cloudeggtech.chalk.xeps.muc.events.Invitation;
import com.cloudeggtech.chalk.xeps.muc.events.InvitationEvent;
import com.cloudeggtech.chalk.xeps.muc.events.RoomEvent;

public class AgilestMobile extends StandardClient {

	public AgilestMobile(Demo demo) {
		super(demo, "Agilest/mobile");
	}

	@Override
	public void asked(JabberId user, String message) {
		super.asked(user, message);
		
		if (getRunCount().intValue() == 1) {
			im.getSubscriptionService().refuse(user, "Who are you?");
		} else {
			Roster roster = new Roster();
			Item item = new Item();
			item.setJid(user);
			item.setName("Boss");
			item.getGroups().add("Buddies of CloudEgg Tech.");
			roster.addOrUpdate(item);
			
			im.getRosterService().add(roster);
			
			new Thread(new ApproveAndSubscribeThread(user)).start();
		}
	}
	
	private class ApproveAndSubscribeThread implements Runnable {
		private JabberId jid;
		
		public ApproveAndSubscribeThread(JabberId jid) {
			this.jid = jid;
		}

		@Override
		public void run() {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			im.getSubscriptionService().approve(jid);
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			im.getSubscriptionService().subscribe(jid, "Add you as my friend too.");
		}
		
	}

	@Override
	protected StandardStreamConfig getStreamConfig() {
		StandardStreamConfig config = new StandardStreamConfig(host, port);
		config.setResource("mobile");
		config.setTlsPreferred(true);
		
		return config;
	}

	@Override
	protected String[] getUserNameAndPassword() {
		return new String[] {"agilest", "a_good_guy"};
	}
	
	@Override
	public void received(RoomEvent<?> event) {
		super.received(event);
		
		if (event instanceof InvitationEvent) {
			Invitation invitation = (Invitation)event.getEventObject();
			try {
				Thread.sleep(300);
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
	
	@Override
	public void received(Message message) {
		super.received(message);
		
		if (!"Are you still online?".equals(message.getBodies().get(0).getText()))
			return;
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		demo.stopClient(this.getClass(), DonggerOffice.class);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		demo.stopClient(this.getClass(), DonggerHome.class);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		demo.stopClient(this.getClass(), AgilestMobile.class);
	}

}

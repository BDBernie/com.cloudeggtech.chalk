package com.cloudeggtech.chalk.examples;

import com.cloudeggtech.basalt.xeps.ibr.IqRegister;
import com.cloudeggtech.basalt.xeps.ibr.RegistrationField;
import com.cloudeggtech.basalt.xeps.ibr.RegistrationForm;
import com.cloudeggtech.chalk.AuthFailureException;
import com.cloudeggtech.chalk.IChatClient;
import com.cloudeggtech.chalk.StandardChatClient;
import com.cloudeggtech.chalk.core.stream.StandardStreamConfig;
import com.cloudeggtech.chalk.core.stream.UsernamePasswordToken;
import com.cloudeggtech.chalk.network.ConnectionException;
import com.cloudeggtech.chalk.network.IConnectionListener;
import com.cloudeggtech.chalk.xeps.ibr.IRegistration;
import com.cloudeggtech.chalk.xeps.ibr.IRegistrationCallback;
import com.cloudeggtech.chalk.xeps.ibr.IbrPlugin;
import com.cloudeggtech.chalk.xeps.ibr.RegistrationException;
import com.cloudeggtech.chalk.xeps.ping.IPing;
import com.cloudeggtech.chalk.xeps.ping.PingPlugin;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class PingExample implements Example, IConnectionListener {

	@Override
	public void run(Options options) throws RegistrationException {
		IChatClient chatClient = new StandardChatClient(creatStreamConfig(options));
		chatClient.register(IbrPlugin.class);
		chatClient.register(PingPlugin.class);
		
		IRegistration registration = chatClient.createApi(IRegistration.class);
		registration.addConnectionListener(this);
		registration.register(new IRegistrationCallback() {

			@Override
			public Object fillOut(IqRegister iqRegister) {
				if (iqRegister.getRegister() instanceof RegistrationForm) {
					RegistrationForm form = new RegistrationForm();
					form.getFields().add(new RegistrationField("username", "dongger"));
					form.getFields().add(new RegistrationField("password", "a_stupid_man"));
					
					return form;
				} else {
					throw new RuntimeException("Can't get registration form.");
				}
			}
			
		});
		
		chatClient.close();
		
		chatClient.addConnectionListener(this);
		try {
			chatClient.connect(new UsernamePasswordToken("dongger", "a_stupid_man"));
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (AuthFailureException e) {
			e.printStackTrace();
		}
		
		IPing ping = chatClient.createApi(IPing.class);
		ping.setTimeout(5 * 60 * 1000);
		
		IPing.Result result = ping.ping();
		if (result == IPing.Result.PONG) {
			System.out.println("Ping Result: Pong.");
		} else if (result == IPing.Result.SERVICE_UNAVAILABLE) {
			System.out.println("Ping Result: Service Unavailable.");
		} else {
			System.out.println("Ping Result: Timeout.");
		}
		
		chatClient.close();
	}

	private StandardStreamConfig creatStreamConfig(Options options) {
		StandardStreamConfig streamConfig = new StandardStreamConfig(options.host, options.port);
		streamConfig.setTlsPreferred(true);
		streamConfig.setResource("chalk_ping_example");
		
		return streamConfig;
	}

	@Override
	public void occurred(ConnectionException exception) {}

	@Override
	public void received(String message) {
		System.out.println("<- " + message);
	}

	@Override
	public void sent(String message) {
		System.out.println("-> " + message);
	}

	@Override
	public void cleanDatabase(MongoDatabase database) {
		database.getCollection("users").deleteOne(Filters.eq("name", "dongger"));
	}

}

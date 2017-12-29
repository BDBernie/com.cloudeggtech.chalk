package com.cloudeggtech.chalk.examples;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class Main {
	private static final String[] EXAMPLE_NAMES = new String[] {"ibr", "auth", "ping", "im"};
	private static final Class<?>[] EXAMPLE_CLASS = new Class<?>[] {IbrExample.class, AuthExample.class, PingExample.class, ImExample.class};
	
	public static void main(String[] args) throws Exception {
		System.setProperty("chalk.negotiation.read.response.timeout", Integer.toString(10 * 60 * 1000));
		
		new Main().run(args);
	}
	
	private void run(String[] args) throws Exception {
		Options options;
		try {
			options = parseOptions(args);
		} catch (IllegalArgumentException e) {
			printUsage();
			return;
		}
		
		MongoClient dbClient = createDatabase(options);
		MongoDatabase database = dbClient.getDatabase(options.dbName);
		
		if (options.examples == null) {
			options.examples = EXAMPLE_NAMES;
		}
		
		for (int i = 0; i < options.examples.length; i++) {
			Class<Example> exampleClass = getExampleClass(options.examples[i]);
			
			if (exampleClass == null)
				throw new RuntimeException(String.format("Unsupported example: %s. Supported examples are: %s.", options.examples[i], getExampleNames()));
			
			Example example = exampleClass.newInstance();
			try {
				example.run(options);
			} catch (Exception e) {
				throw e;
			} finally {
				example.cleanDatabase(database);
			}
		}
		
		dbClient.close();
	}

	private MongoClient createDatabase(Options options) {
		MongoClient client = new MongoClient(new ServerAddress(options.dbHost, options.dbPort),
				Collections.singletonList(MongoCredential.createCredential(options.dbUser, options.dbName, options.dbPassword.toCharArray())));
		return client;
	}

	private String getExampleNames() {
		String exampleNames = null;
		for (String exampleName : EXAMPLE_NAMES) {
			if (exampleNames == null) {
				exampleNames = exampleName;
			} else {
				exampleNames = exampleNames + ", " + exampleName;
			}
		}
		
		return exampleNames;
	}

	@SuppressWarnings("unchecked")
	private Class<Example> getExampleClass(String exampleName) {
		for (int i = 0; i < EXAMPLE_NAMES.length; i++) {
			if (EXAMPLE_NAMES[i].equals(exampleName))
				return (Class<Example>)EXAMPLE_CLASS[i];
		}
		
		return null;
	}

	private Options parseOptions(String[] args) throws IllegalArgumentException {
		Options options = new Options();
		
		Map<String, String> mArgs = new HashMap<>();
		for (int i = 0; i < args.length; i++) {
			if (!args[i].startsWith("--")) {
				String[] examples = Arrays.copyOfRange(args, i, args.length);
				options.examples = examples;
				continue;
			}
			
			String argName = null;
			String argValue = null;
			int equalMarkIndex = args[i].indexOf('=');
			if (equalMarkIndex == -1) {
				argName = args[i];
				argValue = "TRUE";
			} else {
				argName = args[i].substring(2, equalMarkIndex);
				argValue = args[i].substring(equalMarkIndex + 1, args[i].length());
			}
			
			if (mArgs.containsKey(argName)) {
				throw new IllegalArgumentException();
			}
			
			mArgs.put(argName, argValue);
		}
		
		for (Map.Entry<String, String> entry : mArgs.entrySet()) {
			if ("host".equals(entry.getKey())) {
				options.host = entry.getValue();
			} else if ("port".equals(entry.getKey())) {
				options.port = Integer.parseInt(entry.getValue());
			} else if ("db-host".equals(entry.getKey())) {
				options.dbHost = entry.getValue();
			} else if ("db-port".equals(entry.getKey())) {
				options.dbPort = Integer.parseInt(entry.getValue());
			} else if ("db-name".equals(entry.getKey())) {
				options.dbName = entry.getValue();
			} else if ("db-user".equals(entry.getKey())) {
				options.dbUser = entry.getValue();
			} else if ("db-password".equals(entry.getKey())) {
				options.dbPassword = entry.getValue();
			} else {
				throw new IllegalArgumentException(String.format("Unknown option %s.", entry.getKey()));
			}
		}
		
		return options;
	}
	
	private void printUsage() {
		System.out.println("java com.cloudeggtech.chalk.demo.Examples [OPTIONS] <EXAMPLES>");
		System.out.println("OPTIONS:");
		System.out.println("--host=[]           Server address.");
		System.out.println("--port=[]           Server port.");
		System.out.println("--db-host=[]        Database host.");
		System.out.println("--db-port=[]        Database port.");
		System.out.println("--db-name=[]        Database name.");
		System.out.println("--db-user=[]        Database user.");
		System.out.println("--db-password=[]    Database password.");
		System.out.println("EXAMPLES:");
		System.out.println("ibr                 In-Band Registration protocol example.");
		System.out.println("auth                User authentication example.");
	}
}

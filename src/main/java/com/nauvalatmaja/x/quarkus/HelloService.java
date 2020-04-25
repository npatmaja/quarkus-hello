package com.nauvalatmaja.x.quarkus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logmanager.LogManager;

@ApplicationScoped
public class HelloService {
	private static final java.util.logging.Logger LOG = LogManager.getLogManager().getLogger(HelloService.class.getCanonicalName());
	
	@ConfigProperty(name = "greeting.message", defaultValue = "selamat malam")
	private String greeting;
	
	@Inject
	private GreetingConfiguration greetingConfiguration;
	
	public String politeHello(String name) {
		LOG.info("Logging polite hello");
		return "Hello Mr/Mrs " + name;
	}
	
	public String greeting(String name) {
		return greeting + " " + name;
	}
	
	public String advanceGreeting(String name) {
		return String.format("%s, welcome to %s, %s!", greetingConfiguration.getMessage(), greetingConfiguration.getApp(), name);
	}
}

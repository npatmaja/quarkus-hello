package com.nauvalatmaja.x.quarkus;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.logmanager.LogManager;

@ApplicationScoped
public class HelloService {
	private static final java.util.logging.Logger LOG = LogManager.getLogManager().getLogger(HelloService.class.getCanonicalName());
	
	public String politeHello(String name) {
		LOG.info("Logging polite hello");
		return "Hello Mr/Mrs " + name;
	}
}

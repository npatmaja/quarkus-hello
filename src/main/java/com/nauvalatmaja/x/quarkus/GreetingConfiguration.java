package com.nauvalatmaja.x.quarkus;

import io.quarkus.arc.config.ConfigProperties;
import lombok.Getter;
import lombok.Setter;

@ConfigProperties(prefix = "greeting")
@Getter
@Setter
public class GreetingConfiguration {
	private String message;
	private String app;
}

package com.nauvalatmaja.x.quarkus;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

import io.vertx.core.http.HttpServerRequest;

public class LoggingFilter implements ContainerRequestFilter{
	private static final Logger LOG = Logger.getLogger(LoggingFilter.class);

	@Context
	UriInfo info;
	
	@Context
	HttpServerRequest request;
	
	@Override
	public void filter(ContainerRequestContext context) throws IOException {
		final String method = context.getMethod();
        final String path = info.getPath();
        final String address = request.remoteAddress().toString();

        LOG.infof("Request %s %s from IP %s", method, path, address);
	}

}

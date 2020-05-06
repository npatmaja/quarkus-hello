package com.nauvalatmaja.x.quarkus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/exceptions")
public class ExceptionController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/server-error-general")
	public String generalServerException() throws Exception {
		throw new Exception();
	}
}

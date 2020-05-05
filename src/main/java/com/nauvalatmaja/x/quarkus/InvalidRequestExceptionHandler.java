package com.nauvalatmaja.x.quarkus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidRequestExceptionHandler implements ExceptionMapper<InvalidRequestException> {

	@Override
	public Response toResponse(InvalidRequestException exception) {
		return Response.status(400).entity(new ErrorResponse(exception.getErrorCode(), exception.getErrorDescription())).build();
	}	
}

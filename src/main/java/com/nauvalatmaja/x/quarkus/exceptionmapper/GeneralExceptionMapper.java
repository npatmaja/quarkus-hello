package com.nauvalatmaja.x.quarkus.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nauvalatmaja.x.quarkus.rest.model.ErrorResponse;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {
	
	@Override
	public Response toResponse(Exception exception) {
		return Response.status(500).entity(new ErrorResponse("500x01", "General internal server error")).build();
	}

}

package com.nauvalatmaja.x.quarkus.exceptionmapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.nauvalatmaja.x.quarkus.InvalidRequestException;
import com.nauvalatmaja.x.quarkus.rest.model.ErrorResponse;

@Provider
public class InvalidRequestExceptionHandler implements ExceptionMapper<InvalidRequestException> {

	@Override
	public Response toResponse(InvalidRequestException exception) {
		return Response.status(400).entity(new ErrorResponse(exception.getErrorCode(), exception.getErrorDescription())).build();
	}	
}

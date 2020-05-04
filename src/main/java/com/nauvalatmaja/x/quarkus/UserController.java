package com.nauvalatmaja.x.quarkus;

import java.util.UUID;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
	@Inject
	UserService service;
	
	@POST
	@Path("/create")
	@Transactional
	public CreateUserResponse createUser(CreateUserRequest request) {
		UUID id = service.addUser(request);
		return CreateUserResponse.builder().userId(id.toString()).build();
	}
}

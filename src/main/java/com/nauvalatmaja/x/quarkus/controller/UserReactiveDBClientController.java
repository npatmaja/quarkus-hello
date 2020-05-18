package com.nauvalatmaja.x.quarkus.controller;

import java.util.UUID;
import java.util.function.Function;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import com.nauvalatmaja.x.quarkus.db.model.User;
import com.nauvalatmaja.x.quarkus.db.repository.UserReactiveRepository;
import com.nauvalatmaja.x.quarkus.rest.model.CreateUserRequest;
import com.nauvalatmaja.x.quarkus.rest.model.CreateUserResponse;

import io.smallrye.mutiny.Uni;

@Path("/reactive-db/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserReactiveDBClientController {
	private static final Logger LOG = Logger.getLogger(UserReactiveDBClientController.class);
	
	@Inject
	UserReactiveRepository repository;
	
	@POST
	@Path("/create")
	@Transactional
	public Uni<CreateUserResponse> createUser(CreateUserRequest request) {
		Function<UUID, CreateUserResponse> mapper = id -> CreateUserResponse
				.builder()
				.userId(id.toString())
				.build();
		return repository.persist(new User(UUID.randomUUID(), request.getUsername()))
				.onItem().apply(mapper)
				.onFailure().invoke(e -> LOG.errorf(e, "Error while createUser %s", request.getUsername()));
	}
}

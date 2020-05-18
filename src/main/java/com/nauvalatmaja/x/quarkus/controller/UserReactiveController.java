package com.nauvalatmaja.x.quarkus.controller;

import java.util.UUID;
import java.util.function.Function;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nauvalatmaja.x.quarkus.InvalidRequestException;
import com.nauvalatmaja.x.quarkus.InvalidRequestRuntimeException;
import com.nauvalatmaja.x.quarkus.rest.model.CreateUserRequest;
import com.nauvalatmaja.x.quarkus.rest.model.CreateUserResponse;
import com.nauvalatmaja.x.quarkus.service.UserReactiveService;
import com.nauvalatmaja.x.quarkus.service.UserService;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;

@Path("/reactive/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserReactiveController {

	@Inject
	UserReactiveService reactiveService;
	@Inject
	UserService service;

	@POST
	@Path("/create")
	@Transactional
	public Uni<CreateUserResponse> createUser(CreateUserRequest request) {
		Function<UUID, CreateUserResponse> mapper = id -> CreateUserResponse
				.builder()
				.userId(id.toString())
				.build();
		return reactiveService
				.createUser(request)
				.onItem().apply(mapper);
	}
	
	@POST
	@Path("/create2")
	@Transactional
	public Uni<CreateUserResponse> createUser2(CreateUserRequest request) {
		Function<UUID, CreateUserResponse> mapper = id -> CreateUserResponse
				.builder()
				.userId(id.toString())
				.build();
		
		return Uni.createFrom().item(() -> {
			try {
				return service.addUser(request);
			} catch (InvalidRequestException e) {
				throw new InvalidRequestRuntimeException(e.getErrorCode(), e.getErrorDescription());
			}
		})
		.subscribeOn(Infrastructure.getDefaultWorkerPool())
		.on().item().apply(mapper);
	}
}

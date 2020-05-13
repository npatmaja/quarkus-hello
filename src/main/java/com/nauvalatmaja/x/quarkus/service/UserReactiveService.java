package com.nauvalatmaja.x.quarkus.service;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.nauvalatmaja.x.quarkus.InvalidRequestRuntimeException;
import com.nauvalatmaja.x.quarkus.db.model.User;
import com.nauvalatmaja.x.quarkus.db.repository.UserRepository;
import com.nauvalatmaja.x.quarkus.rest.model.CreateUserRequest;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;

@ApplicationScoped
public class UserReactiveService {
	
	@Inject
	UserRepository repository;
	
	public Uni<UUID> createUser(CreateUserRequest request) {
		User user = User.builder()
				.id(UUID.randomUUID())
				.username(request.getUsername())
				.build();
		return Uni.createFrom().item(() -> {
			long userCount = repository.count("username", request.getUsername());
			if (userCount > 0) {
				throw new InvalidRequestRuntimeException("400x01", "User already exists");
			}
			repository.persist(user);
			return user.getId();
		}).subscribeOn(Infrastructure.getDefaultWorkerPool());
	}
}

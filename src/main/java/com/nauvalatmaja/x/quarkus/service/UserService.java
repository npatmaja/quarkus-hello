package com.nauvalatmaja.x.quarkus.service;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import com.nauvalatmaja.x.quarkus.InvalidRequestException;
import com.nauvalatmaja.x.quarkus.db.model.User;
import com.nauvalatmaja.x.quarkus.db.repository.UserRepository;
import com.nauvalatmaja.x.quarkus.rest.model.CreateUserRequest;

@ApplicationScoped
public class UserService {
	private static final Logger LOG = Logger.getLogger(UserService.class);
	
	@Inject
	private UserRepository repository;

	public UUID addUser(CreateUserRequest request) throws InvalidRequestException {
		User user = User.builder()
				.id(UUID.randomUUID())
				.username(request.getUsername())
				.build();
		LOG.info("Creating user: " + request.getUsername());
		long userCount = repository.count("username", request.getUsername());
		if (userCount > 0) {
			throw new InvalidRequestException("400x01", "User already exists");
		}
		repository.persist(user);			
		return user.getId();
	}
}

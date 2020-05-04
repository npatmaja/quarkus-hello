package com.nauvalatmaja.x.quarkus;

import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.nauvalatmaja.x.quarkus.db.model.User;
import com.nauvalatmaja.x.quarkus.db.repository.UserRepository;

@ApplicationScoped
public class UserService {
	@Inject
	private UserRepository repository;

	public UUID addUser(CreateUserRequest request) {
		User user = User.builder()
				.id(UUID.randomUUID())
				.username(request.getUsername())
				.build();
		repository.persist(user);
		return user.getId();
	}
}

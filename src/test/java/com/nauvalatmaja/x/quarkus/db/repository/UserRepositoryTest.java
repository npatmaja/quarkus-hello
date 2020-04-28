package com.nauvalatmaja.x.quarkus.db.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nauvalatmaja.x.quarkus.db.model.User;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class UserRepositoryTest {
	
	@Inject
	UserRepository repository;
	
	@Transactional
	@BeforeEach
	void setUp() {
		repository.deleteAll();
	}

	@Transactional
	@AfterEach
	void cleanUp() {
		repository.deleteAll();
	}
	
	@Transactional
	@Test
	void givenValidUser_whenPersist_shouldPersistToDB() {
		User user = User.builder()
				.id(UUID.randomUUID())
				.username("john.doe")
				.build();
		
		repository.persistAndFlush(user);
		User result = repository.findById(user.getId());
		assertEquals(result, user);
	}

}

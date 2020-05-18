package com.nauvalatmaja.x.quarkus.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.UUID;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.nauvalatmaja.x.quarkus.DatabaseResource;
import com.nauvalatmaja.x.quarkus.db.model.User;
import com.nauvalatmaja.x.quarkus.db.repository.UserReactiveRepository;
import com.nauvalatmaja.x.quarkus.db.repository.UserRepository;
import com.nauvalatmaja.x.quarkus.rest.model.CreateUserRequest;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(DatabaseResource.class)
public class UserReactiveDBClientControllerTest {
	
	@Inject
	UserReactiveRepository repository;
	
	@Transactional
	@BeforeEach
	void setUp() {
		repository.deleteAll();
	}

	@Test
	void givenValidCreateUserRequest_whenCreateReactiveDB_shouldCreateUserRecord() {
		CreateUserRequest createUserRequest = CreateUserRequest.builder()
				.username("john.doe")
				.build();
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(createUserRequest)
			.when().post("/reactive-db/users/create")
			.peek()
			.then()
				.statusCode(200)
				.body("userId", notNullValue(String.class));
	}
	
	@Disabled
	@Test
	void givenDuplicateUsername_whenCreate_shouldReturnUserAlreadyExistError() {
		createUser(new User(UUID.randomUUID(), "john.doe"));
		
		CreateUserRequest createUserRequest = CreateUserRequest.builder()
				.username("john.doe")
				.build();
		
		given()
		.contentType(MediaType.APPLICATION_JSON)
		.body(createUserRequest)
		.when().post("/reactive-db/users/create")
		.then()
			.statusCode(400)
			.body("errorCode", equalTo("400x01"))
			.body("errorDescription", equalTo("User already exists"));
	}

	
	@Transactional
	void createUser(User user) {
		repository.persist(user);
	}
}

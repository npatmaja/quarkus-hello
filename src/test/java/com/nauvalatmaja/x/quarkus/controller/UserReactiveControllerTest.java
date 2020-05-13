package com.nauvalatmaja.x.quarkus.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.UUID;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.nauvalatmaja.x.quarkus.db.model.User;
import com.nauvalatmaja.x.quarkus.db.repository.UserRepository;
import com.nauvalatmaja.x.quarkus.rest.model.CreateUserRequest;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
class UserReactiveControllerTest {

	@Inject
	UserRepository repository;
	
	
	@Transactional
	@BeforeEach
	void setUp() {
		repository.deleteAll();
	}

	@Test
	void givenValidCreateUserRequest_whenCreate_shouldCreateUserRecord() {
		CreateUserRequest createUserRequest = CreateUserRequest.builder()
				.username("john.doe")
				.build();
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(createUserRequest)
			.when().post("/reactive/users/create")
			.then()
				.statusCode(200)
				.body("userId", notNullValue(String.class));
	}
	
	@Test
	void givenValidCreateUserRequest_whenCreate2_shouldCreateUserRecord() {
		CreateUserRequest createUserRequest = CreateUserRequest.builder()
				.username("john.doe")
				.build();
		given()
			.contentType(MediaType.APPLICATION_JSON)
			.body(createUserRequest)
			.when().post("/reactive/users/create2")
			.then()
				.statusCode(200)
				.body("userId", notNullValue(String.class));
	}
	
	@Test
	void givenDuplicateUsername_whenCreate_shouldReturnUserAlreadyExistError() {
		createUser(new User(UUID.randomUUID(), "john.doe"));
		
		CreateUserRequest createUserRequest = CreateUserRequest.builder()
				.username("john.doe")
				.build();
		
		given()
		.contentType(MediaType.APPLICATION_JSON)
		.body(createUserRequest)
		.when().post("/reactive/users/create")
		.then()
			.statusCode(400)
			.body("errorCode", equalTo("400x01"))
			.body("errorDescription", equalTo("User already exists"));
	}
	
	@Test
	void givenDuplicateUsername_whenCreate2_shouldReturnUserAlreadyExistError() {
		createUser(new User(UUID.randomUUID(), "john.doe"));
		
		CreateUserRequest createUserRequest = CreateUserRequest.builder()
				.username("john.doe")
				.build();
		
		given()
		.contentType(MediaType.APPLICATION_JSON)
		.body(createUserRequest)
		.when().post("/reactive/users/create2")
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

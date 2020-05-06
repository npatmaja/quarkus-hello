package com.nauvalatmaja.x.quarkus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
class ExceptionMapperTest {

	@Test
	void givenException_whenCalled_shouldReturnErrorResponseCode500x() {
		given()
		.when().get("/exceptions/server-error-general")
		.then()
			.statusCode(500)
			.body("errorCode", equalTo("500x01"))
			.body("errorDescription", equalTo("General internal server error"));
	}
}

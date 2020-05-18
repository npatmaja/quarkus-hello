package com.nauvalatmaja.x.quarkus.controller;

import org.junit.jupiter.api.Test;

import com.nauvalatmaja.x.quarkus.DatabaseResource;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@QuarkusTestResource(DatabaseResource.class)
public class UserReactiveDBClientControllerTest {
	@Test
	void test() {
		
	}
}

package com.nauvalatmaja.x.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ExampleResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

    @Test
    public void testGreetingEndpoint() {
    	given()
    		.when().get("/hello/greeting/john")
    		.then()
    			.statusCode(200)
    			.body(is("halo john"));
    }
    
    
    @Test
    public void testAdvanceGreetingEndpoint() {
    	given()
    		.when().get("/hello/advance-greeting/john")
    		.then()
    			.statusCode(200)
    			.body(is("halo, welcome to quarkus, john!"));
    }
}
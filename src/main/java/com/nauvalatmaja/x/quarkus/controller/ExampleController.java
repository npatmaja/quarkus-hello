package com.nauvalatmaja.x.quarkus.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nauvalatmaja.x.quarkus.service.HelloService;

@Path("/hello")
public class ExampleController {
	
	@Inject
	private HelloService helloService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/polite/{name}")
    public String polite(@PathParam("name") String name) {
    	return helloService.politeHello(name);
    }
    
    @GET
    @Path("/greeting/{name}")
    public String greeting(@PathParam("name") String name) {
    	return helloService.greeting(name);
    }
    
    @GET
    @Path("/advance-greeting/{name}")
    public String advanceGreeting(@PathParam("name") String name) {
    	return helloService.advanceGreeting(name);
    }
}
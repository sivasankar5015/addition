package com.mkyong.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/user")
public class HelloWorldService {

	@POST
	@Path("/add")
	public Response addUser(
		@FormParam("num1") int number1,
		@FormParam("num2") int number2) {

		int add=number1+number2;
		return Response.status(200)
			.entity("addition : " + number1 + ", age : " + number2+"result"+add)
			.build();

	}

}

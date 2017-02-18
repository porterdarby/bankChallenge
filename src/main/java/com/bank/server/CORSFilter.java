package com.bank.server;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

public class CORSFilter implements ContainerResponseFilter {

	public static final String ORIGIN = "Access-Control-Allow-Origin";
	public static final String METHODS = "Access-Control-Allow-Methods";
	
	@Override
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {

		ResponseBuilder responseBuilder = Response.fromResponse(response.getResponse());

		responseBuilder
			.header(ORIGIN, "*")
			.header(METHODS, "GET, POST")
			.header("Access-Control-Max-Age", "151200")
			.header("Access-Control-Allow-Headers", "x-requested-with,Content-Type");

		String requestHeader = request.getHeaderValue("Access-Control-Request-Headers");

		if (requestHeader != null) {
			responseBuilder.header("Access-Control-Allow-Headers", requestHeader);
		}

		response.setResponse(responseBuilder.build());
		return response;
	}
}

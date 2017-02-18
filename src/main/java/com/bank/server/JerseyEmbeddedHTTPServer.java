package com.bank.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

/**
 * A simple Jersey Embedded HTTP server.
 * 
 * @author Porter Darby
 *
 */
public class JerseyEmbeddedHTTPServer {
	public static final int PORT = 8085;
	private static final String RESOURCE_PACKAGE = "com.bank.server.resources";
	
	// Singleton
	private static JerseyEmbeddedHTTPServer server;

	private HttpServer httpServer;

	public static JerseyEmbeddedHTTPServer getInstance() {
		if (server == null) {
			try {
				server = new JerseyEmbeddedHTTPServer();
			} catch (IllegalArgumentException | IOException e) {
				e.printStackTrace();
			}
		}
		return server;
	}

	private JerseyEmbeddedHTTPServer() throws IllegalArgumentException, IOException {
		System.out.println("\nStarting the HTTP server...");

		HttpServer httpServer = createHTTPServer();
		httpServer.start();

		System.out.println("Server Started");
	}

	private HttpServer createHTTPServer() throws IllegalArgumentException, IOException {
		ResourceConfig resourceConfig = new PackagesResourceConfig("com.bank.server");
		resourceConfig.getContainerResponseFilters().add(new CORSFilter());
		return HttpServerFactory.create(getServerURI(), resourceConfig);
	}

	private URI getServerURI() {
		return UriBuilder.fromUri("http://" + getHostname() + "/").port(PORT).build();
	}

	private String getHostname() {
		String hostname = "localhost";
		try {
			hostname = InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return hostname;
	}
}
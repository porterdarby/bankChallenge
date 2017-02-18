package com.bank.server;

import java.io.Closeable;
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
 * Adapted from: http://crunchify.com/how-to-start-embedded-http-jersey-server-during-java-application-startup/
 * 
 * @author Porter Darby
 *
 */
public class JerseyEmbeddedHTTPServer implements Closeable {
	public static final int PORT = 8085;
	private static final String RESOURCE_PACKAGE = "com.bank.server.resources";

	// Singleton
	private static JerseyEmbeddedHTTPServer server;

	private HttpServer httpServer;
	private boolean closed;

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

		httpServer = createHTTPServer();
		httpServer.start();
		this.closed = false;

		System.out.println("Server Started");
	}

	private HttpServer createHTTPServer() throws IllegalArgumentException, IOException {
		ResourceConfig resourceConfig = new PackagesResourceConfig(RESOURCE_PACKAGE);
		resourceConfig.getContainerResponseFilters().add(new CORSFilter());
		return HttpServerFactory.create(getServerURI(), resourceConfig);
	}

	private URI getServerURI() {
		URI uri = UriBuilder.fromUri("http://" + getHostname() + "/").port(PORT).build();
		System.out.println(uri);
		return uri;
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

	@Override
	public void close() throws IOException {
		httpServer.stop(0);
		this.closed = true;
		server = null;
	}

	public boolean isClosed() {
		return this.closed;
	}
}
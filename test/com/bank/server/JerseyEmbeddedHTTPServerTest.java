package com.bank.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

public class JerseyEmbeddedHTTPServerTest {

	@After
	public void teardown() throws IOException {
		JerseyEmbeddedHTTPServer.getInstance().close();
	}

	@Test
	public void testSingleton() {
		JerseyEmbeddedHTTPServer server1 = JerseyEmbeddedHTTPServer.getInstance();
		JerseyEmbeddedHTTPServer server2 = JerseyEmbeddedHTTPServer.getInstance();

		assertEquals(server1, server2);
	}

	@Test
	public void testClosable() throws IOException {
		JerseyEmbeddedHTTPServer.getInstance();
		assertFalse(JerseyEmbeddedHTTPServer.getInstance().isClosed());
		JerseyEmbeddedHTTPServer.getInstance().close();
	}
}

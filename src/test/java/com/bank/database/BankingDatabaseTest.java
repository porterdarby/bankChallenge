package com.bank.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

public class BankingDatabaseTest {

	@After
	public void teardown() throws IOException {
		BankingDatabase.getInstance().close();
	}

	@Test
	public void testSingleton() {
		BankingDatabase database1 = BankingDatabase.getInstance();
		BankingDatabase database2 = BankingDatabase.getInstance();
		
		assertNotNull(database1);
		assertEquals(database1, database2);
	}

	@Test
	public void testClose() throws IOException {
		BankingDatabase database = BankingDatabase.getInstance();
		assertFalse(database.isClosed());

		database.close();
		assertTrue(database.isClosed());
	}
}

package com.bank.server.resources;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bank.database.BankingDatabase;
import com.bank.models.Transaction;

public class TransactionResourceTest {

	@Before
	public void setup() {
		BankingDatabase.getInstance();
	}

	@After
	public void teardown() throws IOException {
		BankingDatabase.getInstance().close();
	}

	@Test
	public void testSetGetSingle() throws SQLException {
		int transactionId = 1;

		Transaction t = new Transaction(transactionId, 1, "200.00");
		TransactionResource.createTransaction(t);

		Transaction t2 = BankingDatabase.getInstance().getTransactionDao().queryForId(transactionId);

		assertEquals(t, t2);
		assertEquals(1, BankingDatabase.getInstance().getTransactionDao().queryForAll().size());
	}
}

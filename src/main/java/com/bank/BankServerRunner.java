package com.bank;

import java.io.IOException;
import java.sql.SQLException;

import com.bank.database.BankingDatabase;
import com.bank.server.JerseyEmbeddedHTTPServer;

/**
 * Starts the embedded Jersey Server and Database.
 * 
 * @author Porter
 */
public class BankServerRunner {
	public static void main(String[] args)
	    throws IllegalArgumentException, IOException, ClassNotFoundException, SQLException {
		// Getting an instance starts the database.
		BankingDatabase.getInstance();

		// Getting an instance starts starts the server.
		JerseyEmbeddedHTTPServer.getInstance();

		//
		// Test Code
		// TransactionResource tr = new TransactionResource();
		//
		// Transaction t = new Transaction();
		// t.setAccountId(1);
		// t.setAmount(new BigDecimal("10.50"));
		// t.setId(1);
		// tr.createTransaction(t);
		//
		// Transaction t2 = new Transaction();
		// t2.setAccountId(1);
		// t2.setAmount(new BigDecimal("-10.50"));
		// t2.setId(1);
		// tr.createTransaction(t);
	}
}

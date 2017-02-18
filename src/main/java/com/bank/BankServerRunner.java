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
	}
}

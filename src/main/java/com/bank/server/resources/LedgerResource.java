package com.bank.server.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.bank.models.Transaction;

@Path("ledger")
public class LedgerResource {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void postLedger(List<Transaction> ledger) throws SQLException {
		for(Transaction t : ledger) {
			TransactionResource.createTransaction(t);
		}
	}
}

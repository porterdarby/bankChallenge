package com.bank.server.resources;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.bank.models.Transaction;

@Path("ledger")
public class LedgerResource {
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Map<String, List<Transaction>> postLedger(List<Transaction> ledger) throws SQLException {
		Map<String, List<Transaction>> results = new HashMap<>();
		results.put("success", new ArrayList<>());
		results.put("error", new ArrayList<>());
		
		for(Transaction t : ledger) {
			try {
				TransactionResource.createTransaction(t);
				results.get("success").add(t);
			} catch (Exception e) {
				e.printStackTrace();
				results.get("error").add(t);
			}
		}
		return results;
	}
}

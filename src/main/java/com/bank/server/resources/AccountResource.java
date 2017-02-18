package com.bank.server.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bank.database.BankingDatabase;
import com.bank.models.Account;

@Path("account")
public class AccountResource {
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> getAccounts() throws SQLException {
		return BankingDatabase.getInstance().getAccountDao().queryForAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Account getAccount(@PathParam("id") int id) throws SQLException {
		return BankingDatabase.getInstance().getAccountDao().queryForId(id);
	}
}
package com.bank.server.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.bank.database.BankingDatabase;
import com.bank.database.exception.DuplicateTransactionException;
import com.bank.models.Transaction;
import com.j256.ormlite.dao.Dao;

@Path("transaction")
public class TransactionResource {
	private static Dao<Transaction, Integer> dao = BankingDatabase.getInstance().getTransactionDao();

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public static List<Transaction> getAll() throws SQLException {
		return BankingDatabase.getInstance().getTransactionDao().queryForAll();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public static Transaction get(@PathParam("id") int transactionId) {
		Transaction tr = null;

		try {
			tr = dao.queryForId(transactionId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tr;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public static Transaction createTransaction(Transaction transaction) throws SQLException {
		try {
			dao.create(transaction);
		} catch (SQLException e) {
			if (e.getCause().getMessage().contains("unique")) {
				throw new DuplicateTransactionException(transaction);
			}
			e.printStackTrace();
		}
		return get(transaction.getTransactionId());
	}
}

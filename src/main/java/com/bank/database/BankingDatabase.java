package com.bank.database;

import java.sql.SQLException;

import com.bank.models.Account;
import com.bank.models.Transaction;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class BankingDatabase {
	private Dao<Transaction, Integer> transactionDao;
	private Dao<Account, Integer> accountDao;
	private static BankingDatabase bankingDatabase;

	public static BankingDatabase getInstance() {
		if (bankingDatabase == null) {
			try {
				bankingDatabase = new BankingDatabase();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}

		return bankingDatabase;
	}

	private BankingDatabase() throws ClassNotFoundException, SQLException {
		ConnectionSource connectionSource = getConnectionSource();
		createDaos(connectionSource);
		createTables();
	}

	private ConnectionSource getConnectionSource() throws ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver");

		String connectionString = "jdbc:hsqldb:mem:bankdb";

		ConnectionSource connectionSource = new JdbcConnectionSource(connectionString);

		return connectionSource;
	}

	private void createDaos(ConnectionSource connectionSource) throws SQLException {
		transactionDao = DaoManager.createDao(connectionSource, Transaction.class);
		accountDao = DaoManager.createDao(connectionSource, Account.class);
	}

	private void createTables() throws SQLException {
		TableUtils.createTable(transactionDao);
		TableUtils.createTable(accountDao);
	}

	public Dao<Transaction, Integer> getTransactionDao() {
		return transactionDao;
	}

	public Dao<Account, Integer> getAccountDao() {
		return accountDao;
	}
}

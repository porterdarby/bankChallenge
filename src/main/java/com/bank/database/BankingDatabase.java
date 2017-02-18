package com.bank.database;

import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;

import com.bank.models.Account;
import com.bank.models.Transaction;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class BankingDatabase implements Closeable {
	private Dao<Transaction, Integer> transactionDao;
	private Dao<Account, Integer> accountDao;
	private ConnectionSource connectionSource;
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
		connectionSource = getConnectionSource();
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

	private void createTables() {
		try {
			TableUtils.createTable(transactionDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			TableUtils.createTable(accountDao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Dao<Transaction, Integer> getTransactionDao() {
		return transactionDao;
	}

	public Dao<Account, Integer> getAccountDao() {
		return accountDao;
	}

	@Override
	public void close() throws IOException {
		connectionSource.close();
		bankingDatabase = null;
	}

	public boolean isClosed() {
		return !connectionSource.isOpen("");
	}
}

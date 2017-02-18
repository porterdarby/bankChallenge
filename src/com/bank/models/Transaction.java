package com.bank.models;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.bank.database.BankingDatabase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;

@JsonIgnoreProperties({"account"})
public class Transaction {
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", account=" + account + ", amount=" + amount + "]";
	}

	@DatabaseField(id = true)
	private int transactionId;

	@DatabaseField(foreign = true)
	private Account account;

	@DatabaseField
	private BigDecimal amount;

	public Transaction() {

	}

	public Transaction(int id, int accountId, String amount) {
		this(id, accountId, new BigDecimal(amount));
	}

	public Transaction(int id, int accountId, BigDecimal amount) {
		this.setTransactionId(id);
		this.setAccountId(id);
		this.setAmount(amount);
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int id) {
		this.transactionId = id;
	}

	public int getAccountId() {
		return this.getAccount().getId();
	}

	public void setAccountId(int accountId) {
		Dao<Account, Integer> dao = BankingDatabase.getInstance().getAccountDao();
		try {
			this.account = dao.queryForId(accountId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(this.account == null) {
			this.account = new Account(accountId);
			try {
				dao.create(this.account);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return this.account;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + transactionId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (transactionId != other.transactionId)
			return false;
		return true;
	}
}

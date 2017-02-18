package com.bank.models;

import java.math.BigDecimal;

import com.j256.ormlite.field.DatabaseField;

public class Transaction {
	@DatabaseField(id = true)
	private int id;
	@DatabaseField
	private int accountId;

	@DatabaseField
	private BigDecimal amount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", accountId=" + accountId + ", amount=" + amount + "]";
	}
}

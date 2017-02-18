package com.bank.database.exception;

import com.bank.models.Transaction;

public class DuplicateTransactionException extends RuntimeException {

	public DuplicateTransactionException(Transaction transaction) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

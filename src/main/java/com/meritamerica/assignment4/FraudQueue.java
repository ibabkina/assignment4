/**
 * 
 */
package com.meritamerica.assignment4;

import java.util.List;

/**
 * @author irinababkina
 *
 */
public class FraudQueue {
	
	Transaction transaction; // list?
	List Transactions; 

	/**
	 * 
	 */
	public FraudQueue() {
		// TODO Auto-generated constructor stub
	}

	public void addTransaction(Transaction transaction) {
		
	}

	/**
	 * @return the transaction
	 */
	public Transaction getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction the transaction to set
	 */
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	/**
	 * @return the transactions
	 */
	public List getTransactions() {
		return Transactions;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List transactions) {
		Transactions = transactions;
	}
	
}

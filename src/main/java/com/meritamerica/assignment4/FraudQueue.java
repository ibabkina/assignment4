/**
 * 
 */
package com.meritamerica.assignment4;

import java.util.ArrayList;

/**
 * @author irinababkina
 *
 */
public class FraudQueue {
	
//	Transaction transaction; 
	ArrayList<Transaction> transactions; // = new ArrayList<Transaction>(); 

//	/**
//	 * Default constructor
//	 */
//	public FraudQueue() {}
	
	public FraudQueue() {
	transactions = new ArrayList<Transaction>(); 
	}
	
//	public FraudQueue(Transaction transaction) { this.transaction = transaction; }

	public void addTransaction(Transaction transaction) { this.transactions.add(transaction); }
	
	/**
	 * @return the transactions
	 */
	public ArrayList<Transaction> getTransactions() { return this.transactions; }

	/**
	 * @return the transaction
	 */
	public Transaction getTransaction() { return this.transactions.get(transactions.size()-1); }

	/**
	 * @param transaction the transaction to set
	 */
//	public void setTransaction(Transaction transaction) { this.transaction = transaction; }	

//	/**
//	 * @param transactions the transactions to set
//	 */
//	public void setTransactions(List transactions) {
//		Transactions = transactions;
//	}
	
}

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
	
	ArrayList<Transaction> transList; 
	int size;
	
	public FraudQueue() {
		this.transList = new ArrayList<Transaction>(); 
		this.size  = transList.size();
	}

	public void addTransaction(Transaction transaction) { 
		this.transList.add(transaction); 
		this.size = transList.size();
	}
	
//	/**
//	 * @return the transactions
//	 */
//	public ArrayList<Transaction> getTransactions() { return this.transactions; }

	/**
	 * @return the transaction
	 */
	public Transaction getTransaction() { 
		if (transList.size() == 0) {return null;}
		
//		for(Transaction t : this.transList)
//			System.out.println(t);
		
		Transaction temp = this.transList.get(0);
		temp.setProcessedByFraudTeam(true);
		this.transList.remove(0);
		this.size = transList.size();
		
		return temp; }
	
	/**
	 * @return the size
	 */
	public int getFraudQueueSize() { return this.size; }


//	/**
//	 * @param transactions the transactions to set
//	 */
//	public void setTransactions(List transactions) {
//		this.transList = transactions;
//	}
	
}

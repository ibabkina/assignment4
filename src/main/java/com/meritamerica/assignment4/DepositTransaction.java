/**
 * 
 */
package com.meritamerica.assignment4;

/**
 * @author irinababkina
 *
 */
public class DepositTransaction extends Transaction {

	/**
	 * Default constructor
	 */
	public DepositTransaction() { super(); }

	/**
	 * @param targetAccount
	 * @param amount
	 */
	public DepositTransaction(BankAccount targetAccount, double amount) {
		super(targetAccount, amount);
	}
	
	public void process() throws NegativeAmountException,
	ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		
	}
}

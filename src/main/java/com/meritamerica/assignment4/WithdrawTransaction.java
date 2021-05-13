/**
 * 
 */
package com.meritamerica.assignment4;

/**
 * @author irinababkina
 *
 */
public class WithdrawTransaction extends Transaction {

	/**
	 * Default constructor
	 */
	public WithdrawTransaction() { super(); }

	/**
	 * @param targetAccount
	 * @param amount
	 */
	public WithdrawTransaction(BankAccount targetAccount, double amount) {
		super(targetAccount, amount);
	}
	
	public void process() throws NegativeAmountException,
	ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		
	}

}

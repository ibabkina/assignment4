package com.meritamerica.assignment4;

public class TransferTransaction extends Transaction {
	
	/**
	 * Default constructor
	 */
	public TransferTransaction() { super(); }

	/**
	 * @param targetAccount
	 * @param amount
	 */
	public TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount)  {
		super(sourceAccount, targetAccount, amount);
	}
	
	public void process() throws NegativeAmountException,
	ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		
	}

}

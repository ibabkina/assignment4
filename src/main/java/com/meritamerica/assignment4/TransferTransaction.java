package com.meritamerica.assignment4;

import java.util.Date;

public class TransferTransaction extends Transaction {
	
	/**
	 * Default constructor
	 */
	public TransferTransaction() { super(); }
	
//	public TransferTransaction(long sourceAccNum, long targetAccNum, Double amount, Date transDate, boolean isProcessed) {
//		super(sourceAccNum, targetAccNum, amount, transDate, isProcessed);
//	}

	
	public TransferTransaction(long sourceNum, long targetNum, Double amount, Date transDate) {
		super(sourceNum, targetNum, amount, transDate);
	}

	/**
	 * @param targetAccount
	 * @param amount
	 */
	public TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount)  {
		super(sourceAccount, targetAccount, amount);
	}
	
	public void process() throws NegativeAmountException, 
								ExceedsAvailableBalanceException, 
								ExceedsFraudSuspicionLimitException {
	
//		boolean WithdrawIsProcessed = false;
//		WithdrawIsProcessed = this.getSourceAccount().withdraw(this.getAmount()); 
//		boolean DepositIsProcessed = false;	
//		DepositIsProcessed = this.getTargetAccount().deposit(this.getAmount());	
		
		if(this.getSourceAccount().withdraw(this.getAmount()) 
			&& this.getTargetAccount().deposit(this.getAmount())) {
//			System.out.println("Transfer Is Processed = " + getIsProcessed());
			this.getSourceAccount().addTransaction(this);
			this.getTargetAccount().addTransaction(this);
		}		
	}
}

package com.meritamerica.assignment4;

import java.util.List;
import java.util.Date;

public class TransferTransaction extends Transaction {
	
	/**
	 * Default constructor
	 */
	public TransferTransaction() { super(); }
	
	public TransferTransaction(long sourceAccNum, long targetAccNum, Double amount, Date transDate, boolean isProcessed) {
		super(sourceAccNum, targetAccNum, amount, transDate, isProcessed);
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
	
//		try {
		boolean WithdrawIsProcessed = false;
		

//		System.out.println("This is = " + this);
//		long accN = this.sourceAccount.getAccountNumber();
//		System.out.println("Source AccNum = " + accN);
//		System.out.println("The Source Account is: " + this.getSourceAccount());
		
		WithdrawIsProcessed = this.getSourceAccount().withdraw(this.getAmount()); 
//		System.out.println("WithdrawIsProcessed = " + WithdrawIsProcessed);
		boolean DepositIsProcessed = false;	
		DepositIsProcessed = this.getTargetAccount().deposit(this.getAmount());	
//		System.out.println("DepositIsProcessed = " + DepositIsProcessed);
		
		if(WithdrawIsProcessed && DepositIsProcessed) {
			setIsProcessed(true);
//			System.out.println("Transfer Is Processed = " + getIsProcessed());
			this.getSourceAccount().addTransaction(this);
			this.getTargetAccount().addTransaction(this);
//			List<Transaction> ls = this.getSourceAccount().getTransactions();
//			for(Transaction t : ls) {
//				System.out.println(t);
//			}
//			ls = this.getTargetAccount().getTransactions();
//			for(Transaction t : ls) {
//				System.out.println(t);
//			}
		}	
//	
//		catch(NegativeAmountException e){ System.out.println(e); }
//		catch(ExceedsAvailableBalanceException e){ System.out.println(e); }
//		catch(ExceedsFraudSuspicionLimitException e){ System.out.println(e); }
	
	}
}

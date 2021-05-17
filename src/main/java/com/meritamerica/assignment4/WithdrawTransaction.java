/**
 * 
 */
package com.meritamerica.assignment4;

import java.util.Date;
import java.util.List;

/**
 * @author irinababkina
 *
 */
public class WithdrawTransaction extends Transaction {

	/**
	 * Default constructor
	 */
	public WithdrawTransaction() { super(); }
	
	public WithdrawTransaction(long sourceAccNum, long targetAccNum, Double amount, Date transDate, boolean isProcessed) {
		super(sourceAccNum, targetAccNum, amount, transDate, isProcessed);
	}

	/**
	 * @param targetAccount
	 * @param amount
	 */
	public WithdrawTransaction(BankAccount targetAccount, double amount) {
		super(targetAccount, amount);
	}
	

	public void process() throws NegativeAmountException, 
								ExceedsAvailableBalanceException, 
								ExceedsFraudSuspicionLimitException {
	
//	 	try {

		boolean isProcessed = false;
		

//		System.out.println("This is = " + this);
//		long accN = this.sourceAccount.getAccountNumber();
//		System.out.println("Source AccNum = " + accN);
//		System.out.println("The Source Account is: " + this.getSourceAccount());
		
		isProcessed = this.getTargetAccount().withdraw(this.getAmount()); 
//		System.out.println("WithdrawIsProcessed = " + WithdrawIsProcessed);
		
		if(isProcessed) {
			setIsProcessed(true);
//			System.out.println("Transfer Is Processed = " + getIsProcessed());
//			this.getSourceAccount().addTransaction(this);
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
//		}
//		catch(NegativeAmountException e){ System.out.println(e); }
//		catch(ExceedsAvailableBalanceException e){ System.out.println(e); }	
	}
}

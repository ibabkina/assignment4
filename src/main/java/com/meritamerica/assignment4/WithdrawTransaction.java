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
	
//	public WithdrawTransaction(long sourceAccNum, long targetAccNum, Double amount, Date transDate, boolean isProcessed) {
//		super(sourceAccNum, targetAccNum, amount, transDate, isProcessed);
//	}
	
	public WithdrawTransaction(long sourceNum, long targetNum, Double amount, Date transDate) {
		super(sourceNum, targetNum, amount, transDate);
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
	
//		boolean isProcessed = false;
//		isProcessed = this.getTargetAccount().withdraw(this.getAmount()); 
//		if(isProcessed) {
//			setIsProcessed(true);
//			this.getTargetAccount().addTransaction(this);
//		}
		
		if(this.getTargetAccount().withdraw(this.getAmount()) == true) { 	
			this.getTargetAccount().addTransaction(this);
		}	
	}
}

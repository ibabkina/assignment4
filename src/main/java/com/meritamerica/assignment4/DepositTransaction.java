/**
 * 
 */
package com.meritamerica.assignment4;

import java.util.Date;

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
	 * @param sourceAccNum
	 * @param targetAccNum
	 * @param amount
	 * @param transDate
	 * @param isProcessed
	 */
//	public DepositTransaction(long sourceAccNum, long targetAccNum, Double amount, Date transDate, boolean isProcessed) {
//		super(sourceAccNum, targetAccNum, amount, transDate, isProcessed);
//	}
	
	public DepositTransaction(long sourceNum, long targetNum, Double amount, Date transDate) {
		super(sourceNum, targetNum, amount, transDate);
	}
		
	/**
	 * @param targetAccount
	 * @param amount
	 */
	public DepositTransaction(BankAccount targetAccount, double amount) {
		super(targetAccount, amount);
	}
	
	public void process() throws NegativeAmountException, 
								ExceedsAvailableBalanceException, 
								ExceedsFraudSuspicionLimitException {
		
//		try {		
		if(this.getTargetAccount().deposit(this.getAmount()) == true) { 	
			this.getTargetAccount().addTransaction(this);

		}	
		
//		catch(NegativeAmountException e){ System.out.println(e); }		
//		catch(ExceedsAvailableBalanceException e){ System.out.println(e); }		
//		catch(ExceedsFraudSuspicionLimitException e){ System.out.println(e); }
		
	}
}

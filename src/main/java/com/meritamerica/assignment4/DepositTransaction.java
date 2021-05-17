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
public class DepositTransaction extends Transaction {

	/**
	 * Default constructor
	 */
	public DepositTransaction() { super(); }
	
	
	public DepositTransaction(long sourceAccNum, long targetAccNum, Double amount, Date transDate, boolean isProcessed) {
		super(sourceAccNum, targetAccNum, amount, transDate, isProcessed);
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
		boolean isProcessed = false;
		

//		System.out.println("This is = " + this);
//		long accN = this.sourceAccount.getAccountNumber();
//		System.out.println("Source AccNum = " + accN);
//		System.out.println("The Source Account is: " + this.getSourceAccount());
		
		isProcessed = this.getTargetAccount().deposit(this.getAmount()); 
//		System.out.println("WithdrawIsProcessed = " + WithdrawIsProcessed);
//		System.out.println("DepositIsProcessed = " + DepositIsProcessed);
		
		if(isProcessed) {
			setIsProcessed(true);
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
//		
//		catch(ExceedsFraudSuspicionLimitException e){ System.out.println(e); }
		
	}
}

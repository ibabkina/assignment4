/**
 * 
 */
package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author irinababkina
 *
 */
public abstract class Transaction {
	
//	private String description;
	protected long sourceAccNum;
	protected long targetAccNum;
	protected BankAccount sourceAccount;
	protected BankAccount targetAccount;
	protected double amount;
	protected Date transDate;
	protected boolean isProcessed;
	protected boolean isProcessedByFraudTeam;
	protected String rejectionReason; //reason

	/**
	 * Default constructor
	 */
	public Transaction() {}
	
	public Transaction (long sourceAccNum, long targetAccNum, Double amount, Date transDate, boolean isProcessed) {		
		this.sourceAccNum = sourceAccNum;
		this.targetAccNum = targetAccNum;
		this.sourceAccount = MeritBank.getBankAccount(sourceAccNum);
		this.targetAccount= MeritBank.getBankAccount(targetAccNum);
		this.amount = amount;
		this.transDate = transDate;	
		this.isProcessed = isProcessed;
	}
	
	/**
	 * @param targetAccount
	 * @param amount
	 */
	public Transaction(BankAccount targetAccount, double amount) {
		this.sourceAccNum = targetAccount.getAccountNumber();
		this.targetAccNum = targetAccount.getAccountNumber();
		this.sourceAccount = targetAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transDate = new Date();
		this.isProcessed = false;
	}

	public Transaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		this.sourceAccNum = sourceAccount.getAccountNumber();
		this.targetAccNum = targetAccount.getAccountNumber();
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transDate = new Date();
		this.isProcessed = false;
	}
	
	public static Transaction readFromString(String transDataString) 
			throws NumberFormatException, ParseException {
		Transaction transaction;
		String[] args = transDataString.split(",");
//		System.out.println(args[0]);
		double amount = Double.parseDouble(args[2]);
		
		if(args[0].length() > 1) {
			if(amount > 0) {
				transaction = new DepositTransaction(Long.parseLong(args[1]), // sourceAccNum == targetAccountNum/ Long.parseLong(args[1]
													Long.parseLong(args[1]), 
													Double.parseDouble(args[2]),
													new SimpleDateFormat("dd/MM/yyyy").parse(args[3]),
													true);
			}
			else {
				transaction = new WithdrawTransaction(Long.parseLong(args[1]), // sourceAccNum == targetAccountNum/ Long.parseLong(args[1]
													Long.parseLong(args[1]),  // Long.parseLong(args[1]
													Math.abs(Double.parseDouble(args[2])),
													new SimpleDateFormat("dd/MM/yyyy").parse(args[3]),
													true);
			}
		}
		
		else {
			transaction = new TransferTransaction(Long.parseLong(args[0]), 
												Long.parseLong(args[1]), 
												Double.parseDouble(args[2]),
												new SimpleDateFormat("dd/MM/yyyy").parse(args[3]),
												true);
		}
//		System.out.println(transaction.toString());
		return transaction;
	}
	
	
	
	// Should throw a java.lang.NumberFormatException if String cannot be correctly parsed
//		public static BankAccount readFromString(String accountData) 
//				throws NumberFormatException, ParseException {
//			String[] args = accountData.split(",");
//			BankAccount acc = new BankAccount(Long.parseLong(args[0]), Double.parseDouble(args[1]), 
//			Double.parseDouble(args[2]), new SimpleDateFormat("MM/dd/yyyy").parse(args[3]));
//			System.out.println(acc.toString());
//			return acc;
//		}
	
	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the sourceAccount
	 */
	public BankAccount getSourceAccount() {
		return this.sourceAccount;
	}

	/**
	 * @param sourceAccount the sourceAccount to set
	 */
	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
	}

	/**
	 * @return the targetAccount
	 */
	public BankAccount getTargetAccount() {
		return this.targetAccount;
	}

	/**
	 * @param targetAccount the targetAccount to set
	 */
	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount = targetAccount;
	}

	/**
	 * @return the transDate
	 */
	public Date getTransactionDate() {
		return this.transDate;
	}

	/**
	 * @param transDate the transDate to set
	 */
	public void setTransactionDate(Date transDate) {
		this.transDate = transDate;
	}

	/**
	 * @return the isProcessed
	 */
	public boolean getIsProcessed() {
		return this.isProcessed;
	}

	/**
	 * @param isProcessed the isProcessed to set
	 */
	public void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}
	
	/**
	 * @return the isProcessedByFraudTeam
	 */
	public boolean isProcessedByFraudTeam() {
		return this.isProcessedByFraudTeam;
	}

	/**
	 * @param isProcessedByFraudTeam the isProcessedByFraudTeam to set
	 */
	public void setProcessedByFraudTeam(boolean isProcessed) {
		this.isProcessedByFraudTeam = isProcessed;
	}

	/**
	 * @return the rejectionReason
	 */
	public String getRejectionReason() {
		return this.rejectionReason;
	}

	/**
	 * @param rejectionReason the rejectionReason to set
	 */
	public void setRejectionReason(String reason) {
		this.rejectionReason = reason;
	}
	
	public abstract void process() throws NegativeAmountException,
	ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException;

	@Override
	public String toString() {
		return "Transaction \ngetSourceAccNum = " + this.sourceAccNum 
				+ ", getTargetAccNum = " + this.targetAccNum
				+ ", getAmount()=" + getAmount() 
				+ ", getTransactionDate()=" + getTransactionDate()
				+ ", isProcessed =" + getIsProcessed();
//				+ ", getSourceAccount()=" + getSourceAccount()
//				+ ", getTargetAccount()=" + getTargetAccount() 
//				+ ", isProcessedByFraudTeam()=" + isProcessedByFraudTeam() + ", getRejectionReason()="
//				+ getRejectionReason() + "]";
	}	

}

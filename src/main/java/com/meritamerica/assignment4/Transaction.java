/**
 * 
 */
package com.meritamerica.assignment4;

import java.util.Date;

/**
 * @author irinababkina
 *
 */
public abstract class Transaction {
	
//	private String description;
	private BankAccount sourceAccount;
	private BankAccount targetAccount;
	private double amount;
	private Date transDate;
	boolean isProcessedByFraudTeam;
	private String rejectionReason; //reason

	/**
	 * 
	 */
	public Transaction() {}
	
	public Transaction(BankAccount sourceAccount, double amount) {
		super();
		this.sourceAccount = sourceAccount;
		this.amount = amount;
		this.transDate = new Date();
	}

	public Transaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transDate = new Date();
	}
	
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
		return "Transaction [getAmount()=" + getAmount() + ", getSourceAccount()=" + getSourceAccount()
				+ ", getTargetAccount()=" + getTargetAccount() + ", getTransactionDate()=" + getTransactionDate()
				+ ", isProcessedByFraudTeam()=" + isProcessedByFraudTeam() + ", getRejectionReason()="
				+ getRejectionReason() + "]";
	}	

}

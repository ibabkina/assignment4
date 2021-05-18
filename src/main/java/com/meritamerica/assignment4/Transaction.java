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
	
	protected long sourceNum;
	protected long targetNum;
	protected BankAccount sourceAccount;
	protected BankAccount targetAccount;
	protected double amount;
	protected Date transDate;
	protected boolean isProcessedByFraudTeam;
	protected String rejectionReason; //reason

	/**
	 * Default constructor
	 */
	public Transaction() {}
	
	// The sourceNum = -1 for deposit and withdraw transactions
	public Transaction (long sourceNum, long targetNum, Double amount, Date transDate) {
		this.sourceNum = sourceNum;
		this.targetNum = targetNum;
		this.sourceAccount = null;
		this.targetAccount = null;
		this.amount = amount;
		this.transDate = transDate;	
	}	
	
	/**
	 * @param targetAccount
	 * @param amount
	 */
	public Transaction(BankAccount targetAccount, double amount) {
		this.sourceNum = -1; //targetAccount.getAccountNumber();
		this.targetNum = targetAccount.getAccountNumber();
		this.sourceAccount = targetAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transDate = new Date();
	}

	public Transaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		this.sourceNum = sourceAccount.getAccountNumber();
		this.targetNum = targetAccount.getAccountNumber();
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transDate = new Date();
	}
	
	public static Transaction readFromString(String transDataString) 
			throws NumberFormatException, ParseException {
		Transaction transaction;
		String[] args = transDataString.split(",");
//		for(String s : args) {
//			System.out.println(s);
//		}
		double amount = Double.parseDouble(args[2]);
		
		if(args[0].length() > 1) {
			if(amount > 0) {
				transaction = new DepositTransaction(Long.parseLong(args[0]), //  = -1
													Long.parseLong(args[1]), 
													Double.parseDouble(args[2]),
													new SimpleDateFormat("MM/dd/yyyy").parse(args[3]));
			}
			else {
				transaction = new WithdrawTransaction(Long.parseLong(args[0]), //  = -1
													Long.parseLong(args[1]),  
													Math.abs(Double.parseDouble(args[2])),
													new SimpleDateFormat("MM/dd/yyyy").parse(args[3]));
			}
		}
		
		else {
			transaction = new TransferTransaction(Long.parseLong(args[0]), 
												Long.parseLong(args[1]), 
												Double.parseDouble(args[2]),
												new SimpleDateFormat("MM/dd/yyyy").parse(args[3]));
		}
		return transaction;
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
		return "Transaction \ngetSourceAccNum = " + this.getSourceAccount().getAccountNumber()
				+ ", getTargetAccNum = " + this.getTargetAccount().getAccountNumber()
				+ ", getAmount()=" + this.getAmount() 
				+ ", getTransactionDate()=" + this.getTransactionDate();
//				+ ", isProcessed =" + getIsProcessed();
//				+ ", getSourceAccount()=" + getSourceAccount()
//				+ ", getTargetAccount()=" + getTargetAccount() 
//				+ ", isProcessedByFraudTeam()=" + isProcessedByFraudTeam() + ", getRejectionReason()="
//				+ ", getRejectionReason() + "]";
	}

	public String writeToString() {
		String amnt;

		if(this instanceof WithdrawTransaction) { amnt = "-" + String.format("%.0f", this.getAmount()); }
		else {amnt = String.format("%.0f", this.getAmount());}

		return Long.toString(this.sourceNum) + "," 
				+ Long.toString(this.getTargetAccount().getAccountNumber()) + "," 
				+ amnt + ","
				+ new SimpleDateFormat("MM/dd/yyyy").format(this.getTransactionDate());					
	}
}

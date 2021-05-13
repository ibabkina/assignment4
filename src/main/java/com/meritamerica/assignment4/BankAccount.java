package com.meritamerica.assignment4;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This program creates an account for a client.
 * 
 * @author Irina Babkina 
 * 
 */
 
public class BankAccount {
	
	protected double balance;
	protected double interestRate; 
	protected long accountNumber;
	protected Date accountOpenedOn; // java.util.Date 
	List<Transaction> transactions;
	
	/**
	 * Default constructor 
	 */
	public BankAccount() {}

	/**
	 * @param balance
	 * @param interestRate 
	 */
	public BankAccount(double balance, double interestRate) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountNumber = MeritBank.getNextAccountNumber();
		this.accountOpenedOn = new Date();
	}
	
	/**
	 * @param balance
	 * @param interestRate 
	 * @param accountOpenedOn
	 */
	public BankAccount(double balance, double interestRate, Date accountOpenedOn) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountNumber = MeritBank.getNextAccountNumber();
		this.accountOpenedOn = accountOpenedOn;	
	}
	
	/**
	 * @param accountNumber
	 * @param balance
	 * @param interestRate 
	 */
	public BankAccount(long accountNumber, double balance, double interestRate, Date accountOpenedOn) {
		this.balance = balance;
		this.interestRate = interestRate;
		this.accountNumber = accountNumber;
		this.accountOpenedOn = accountOpenedOn;
	}
	
	// Should throw a java.lang.NumberFormatException if String cannot be correctly parsed
	public static BankAccount readFromString(String accountData) 
			throws NumberFormatException, ParseException {
		String[] args = accountData.split(",");
		BankAccount acc = new BankAccount(Long.parseLong(args[0]), Double.parseDouble(args[1]), 
		Double.parseDouble(args[2]), new SimpleDateFormat("MM/dd/yyyy").parse(args[3]));
		System.out.println(acc.toString());
		return acc;
	}
	
	/**
	 * @return the account number
	 */
	public long getAccountNumber() { return accountNumber; }
	
	/**
	 * @return the accountOpenedOn
	 */
	public Date getOpenedOn() { return accountOpenedOn; }
	
	/**
	 * @return the balance
	 */
	public double getBalance() { return balance; }

	/**
	 * @return the interestRate
	 */
	public double getInterestRate() { return interestRate; }
	
	/**
	 * @param amount
	 * @return boolean: success or fail
	 */
	boolean withdraw(double amount) {
		if (amount <= 0.0 || amount > balance) {  	// don't forget to check for == 0
			return false;
		}
		balance -= amount;
		return true;	
	}
	
	/**
	 * @param amount
	 * @return boolean: success or fail
	 */
	boolean deposit(double amount) {				// don't forget to check for == 0
		if (amount <= 0.0) { return false; }
		balance += amount;
		return true;	
	}
	
	/**
	 * Calculates the future value of the account balance based on the interest 
	 * and number of years
	 * @param years: number of periods in years
	 * @return the future value
	 */
	double futureValue(int years) { return this.balance * Math.pow(1 + interestRate, years); }
	
//	/**
//	 * Calculates the future value of the account balance based on the interest 
//	 * and number of years
//	 * @param years: number of periods in years
//	 * @return the future value
//	 */
//	abstract double futureValue(int years);
	
	public void addTransaction(Transaction transaction) {
		
	}
	
	public List<Transaction> getTransaction() {
		return this.transactions;
	}

	@Override
	public String toString() {
		return "\nAccount Balance: $" + String.format("%.2f", this.getBalance())
			+ "\nAccount Interest Rate: " + String.format("%.4f", this.getInterestRate())
			+ "\nAccount Balance in 3 years: " + String.format("%.2f", this.futureValue(3))
			+ "\nAccount Opened Date " + this.getOpenedOn();
	}	
	
	public String writeToString() {
		return Long.toString(this.getAccountNumber()) + "," 
				+ String.format("%.0f", this.getBalance()) + ","
				+ String.format("%.4f", this.getInterestRate()) + ","
				+ new SimpleDateFormat("MM/dd/yyyy").format(this.accountOpenedOn);	
	}
}	
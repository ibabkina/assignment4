package com.meritamerica.assignment4;


import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This program creates a savings account for a client.
 * 
 * @author Irina Babkina 
 * 
 */

public class SavingsAccount extends BankAccount {
	
//	private double interestRate = 0.01;
	
	/**
	 * Default constructor 
	 */
	public SavingsAccount() { super(); }
	
	/**
	 * @param openingBalance
	 */
	public SavingsAccount(double openingBalance) {
		super(openingBalance, 0.01);
	}
	
	/**
	 * @param openingBalance
	 * @param interestRate
	 */
	public SavingsAccount(double openingBalance, double interestRate){
		super(openingBalance, interestRate);
	}
	
	/**
	 * @param accNumber
	 * @param openingBalance
	 * @param interestRate
	 */
	public SavingsAccount(long accNumber, double openingBalance, double interestRate, java.util.Date accountOpenedOn){
		super(accNumber, openingBalance, interestRate, accountOpenedOn);
	}
	
	// Should throw a java.lang.NumberFormatException if String cannot be correctly parsed
	public static SavingsAccount readFromString(String accountData) 
			throws NumberFormatException, ParseException {
		String[] args = accountData.split(",");
		SavingsAccount acc = new SavingsAccount(Long.parseLong(args[0]), Double.parseDouble(args[1]), 
		Double.parseDouble(args[2]), new SimpleDateFormat("dd/MM/yyyy").parse(args[3]));
		return acc;
	}
	
//	/**
//	 * @return the interestRate
//	 */
//	public double getInterestRate() { return this.interestRate; }
//	
//	/**
//	 * Calculates the future value of the account balance based on the interest 
//	 * and number of years
//	 * @param years: number of periods in years
//	 * @return the future value
//	 */
//	double futureValue(int years){ return this.balance * pow(1 + interestRate, years); }
	
	@Override
	public String toString() {
		return "\nSavings Account Number: " + this.getAccountNumber()
			+ "\nSavings Account Balance: $" + String.format("%.2f", this.getBalance())
			+ "\nSavings Account Interest Rate: " + String.format("%.4f", this.getInterestRate())
			+ "\nSavings Account Balance in 3 years: " + String.format("%.2f", this.futureValue(3));
	}	
	
	@Override
	public String writeToString() {
		return Long.toString(this.getAccountNumber()) + "," 
				+ String.format("%.0f", this.getBalance()) + ","
				+ String.format("%.2f", this.getInterestRate()) + ","
				+ new SimpleDateFormat("dd/MM/yyyy").format(this.accountOpenedOn);	
	}
}
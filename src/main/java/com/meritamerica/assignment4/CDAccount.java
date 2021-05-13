package com.meritamerica.assignment4;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This program creates a CD account for a client.
 * 
 * @author Irina Babkina 
 * 
 */


public class CDAccount extends BankAccount{
	
	private int term;
	
	/**
	 * @param offering
	 * @param openingBalance 
	 */
	public CDAccount(CDOffering offering, double openingBalance) {
		super(openingBalance, offering.getInterestRate()); 
		this.term = offering.getTerm();
	}
	
	/**
	 * @param accNumber
	 * @param openingBalance
	 * @param interestRate
	 */
	public CDAccount(long accNumber, 
					double openingBalance, 
					double interestRate, 
					Date accountOpenedOn, 
					int term){
		super(accNumber, openingBalance, interestRate, accountOpenedOn);
		this.term = term;
	}
	
	public static CDAccount readFromString(String accountData) 
			throws NumberFormatException, ParseException {
		String[] args = accountData.split(",");
    	
		CDAccount acc = new CDAccount(Long.parseLong(args[0]), 
							Double.parseDouble(args[1]), 
							Double.parseDouble(args[2]), 
							new SimpleDateFormat("dd/MM/yyyy").parse(args[3]), 
							Integer.parseInt(args[4])); 
		return acc;
	}
	
	@Override
	public boolean deposit(double amount) { return false; }
	
	@Override
	public boolean withdraw(double amount) { return false; }
	
	/**
	 * @return the interestRate
	 */
	public double getInterestRate() { return this.interestRate; }
	
	/**
	 * @return the term
	 */
	int getTerm() { return term; }

//	/**
//	 * @return the startDate 
//	 */
//	public java.util.Date getStartDate() { return startDate; }
		
	/**
	 * Calculates the future value of the account balance based on the interest 
	 * and number of years
	 * @param years: number of periods in years
	 * @return the future value
	 */
	double futureValue(){ return this.balance * Math.pow(1 + interestRate, term); }
	
	/**
	 * @param term the term to set
	 */
	public void setTerm(int term) { this.term = term; }

	@Override
	public String toString() {
		return "\nCD Account Number: " + this.getAccountNumber()
			+ "\nCD Account Balance: $" + String.format("%.2f", this.getBalance())
			+ "\nCD Account Interest Rate: " + String.format("%.4f", this.getInterestRate())
			+ "\nCD Account Term: " + this.getTerm()
			+ "\nCD Account Balance in " + this.getTerm() + " years: " + String.format("%.2f", this.futureValue(this.getTerm()))
			+ "\nCD Account Opened Date " + this.getOpenedOn();
	}
	
	@Override
	public String writeToString() {
		return Long.toString(this.getAccountNumber()) + "," 
				+ String.format("%.0f", this.getBalance()) + ","
				+ String.format("%.3f", this.getInterestRate()) + ","
				+ new SimpleDateFormat("MM/dd/yyyy").format(this.accountOpenedOn) + ","
				+ Integer.toString(this.term);
	}
}
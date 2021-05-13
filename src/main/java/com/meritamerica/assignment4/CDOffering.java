package com.meritamerica.assignment4;

/**
 * This program creates CD offerings for a bank.
 * 
 * @author Irina Babkina 
 * 
 */

public class CDOffering {
	
	private int term;
	private double interestRate;
	
	/**
	 * Default constructor 
	 */
	public CDOffering(){}
	
	/**
	 * @param term
	 * @param interestRate
	 */
	public CDOffering(int term, double interestRate){
		this.term = term;
		this.interestRate = interestRate;
	}

	// Doesn't Throw a java.lang.NumberFormatException if String cannot be correctly parsed
	// because of test cases (the test cases files are not in the try/catch block)
	public static CDOffering readFromString(String cdOfferingDataString) {
		String[] args = cdOfferingDataString.split(",");
		CDOffering offering = new CDOffering(Integer.parseInt(args[0]), Double.parseDouble(args[1]));
		return offering;
		}
	
	/**
	 * @return the term
	 */
	public int getTerm() { return term; }

	/**
	 * @param term the term to set
	 */
	public void setTerm(int term) { this.term = term; }

	/**
	 * @return the interestRate
	 */
	public double getInterestRate() { return interestRate; }

	/**
	 * @param interestRate the interestRate to set
	 */
	public void setInterestRate(double interestRate) { this.interestRate = interestRate; }
	
	@Override
	public String toString() {
		return "\nCD Offering Term : " + this.getTerm()+ "\nCD Offering Rate : " + this.getInterestRate();
		}
	
	public String writeToString() {
		return Integer.toString(this.getTerm()) + "," 
				+ Double.toString(this.getInterestRate()); 
	}
}
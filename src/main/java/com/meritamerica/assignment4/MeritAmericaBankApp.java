package com.meritamerica.assignment4;

public class MeritAmericaBankApp {
	public static void main(String[] args) {
		MeritBank.readFromFile("src/test/testMeritBank_good.txt");
		MeritBank.writeToFile("newData.txt");	
		
//		AccountHolder accountHolder = new AccountHolder(
//	        	"Sadiq",
//	        	"",
//	        	"Manji",
//	        	"123456789");       
//	        
//	        CDAccount cdAccount = accountHolder.addCDAccount(new CDOffering(5, 0.03), 10000);
//
//	        double fv = MeritBank.futureValue(10000, 5, 0.03);
//	        System.out.println("cdAccount.futureValue() = " + cdAccount.futureValue());
//	        System.out.println("FV = " + fv);
	}
}
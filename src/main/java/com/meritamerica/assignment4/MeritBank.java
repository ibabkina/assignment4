package com.meritamerica.assignment4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.lang.Exception;
import java.lang.NumberFormatException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * This program creates account holders for a bank  and adds his accounts. It provides information 
 * about an account holder and their accounts.
 * 
 * @author Irina Babkina 
 */

public class MeritBank {
	
	private static AccountHolder[] accountHolders = new AccountHolder[0]; 
	private static CDOffering[] cdOfferings; 
	private static long nextAccountNumber = 1000;
	private static final double MAX_TRANSACTION_AMOUNT = 1000.00;
	private static FraudQueue fraudQueue = new FraudQueue();

	static boolean readFromFile(String fileName) {
		clearAccountHolders();	
		clearCDOfferings();
		try {
			Scanner sc = new Scanner(new File(fileName));
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				setNextAccountNumber(Integer.parseInt(line));
				line = sc.nextLine();
				int numOfCDOfferings = Integer.parseInt(line);
				cdOfferings = new CDOffering[numOfCDOfferings];
				for (int i = 0; i < numOfCDOfferings; i++) {
					if(!sc.hasNextLine()) { break; }
					line = sc.nextLine();
					cdOfferings[i] = CDOffering.readFromString(line);
				}
				line = sc.nextLine();
				int numOfAccholders = Integer.parseInt(line);
				accountHolders = new AccountHolder[0];
				for (int i = 0; i < numOfAccholders; i++) {
					line = sc.nextLine();
					addAccountHolder(AccountHolder.readFromString(line));
					line = sc.nextLine();
					int numOfChckAccounts = Integer.parseInt(line);
					for (int j = 0; j < numOfChckAccounts; j++) {
						line = sc.nextLine();
//						accountHolders[i].addCheckingAccount(CheckingAccount.readFromString(line)); 
						CheckingAccount acc = (CheckingAccount.readFromString(line));
						accountHolders[i].addCheckingAccount(acc); 
						line = sc.nextLine();
						int numOfTransactions = Integer.parseInt(line);
						for (int k = 0; k < numOfTransactions; k++) {
							line = sc.nextLine();
							Transaction t = Transaction.readFromString(line);
							acc.addTransaction(t);
						}
					}
					line = sc.nextLine(); 
					int numOfSvgsAccounts = Integer.parseInt(line);
					for (int j = 0; j < numOfSvgsAccounts; j++) {
						line = sc.nextLine();
						SavingsAccount acc = (SavingsAccount.readFromString(line));
						accountHolders[i].addSavingsAccount(acc); 
						line = sc.nextLine();
						int numOfTransactions = Integer.parseInt(line);
						for (int k = 0; k < numOfTransactions; k++) {
							line = sc.nextLine();
							Transaction t = Transaction.readFromString(line);
							acc.addTransaction(t);
						}		
					}
					line = sc.nextLine();
					
					int numOfCDAccounts = Integer.parseInt(line);
					for (int j = 0; j < numOfCDAccounts; j++) {
						line = sc.nextLine();
						CDAccount acc = (CDAccount.readFromString(line));
						accountHolders[i].addCDAccount(acc); 
						line = sc.nextLine();
						int numOfTransactions = Integer.parseInt(line);
						for (int k = 0; k < numOfTransactions; k++) {
							line = sc.nextLine();
							Transaction t = Transaction.readFromString(line);
							acc.addTransaction(t);
						}				
					}
				}
			
				for(AccountHolder accH: accountHolders) {
					for(CheckingAccount acc : accH.getCheckingAccounts()) {
						for(Transaction t : acc.getTransactions()) {
							if(t.sourceNum == -1) {
								t.setSourceAccount(MeritBank.getBankAccount(t.targetNum));
							}
							else { 
								t.setSourceAccount(MeritBank.getBankAccount(t.sourceNum));
							}
							t.setTargetAccount(MeritBank.getBankAccount(t.targetNum));
						}
					}
					for(SavingsAccount acc : accH.getSavingsAccounts()) {
						for(Transaction t : acc.getTransactions()) {
							if(t.sourceNum == -1) {
								t.setSourceAccount(MeritBank.getBankAccount(t.targetNum));
							}
							else { 
								t.setSourceAccount(MeritBank.getBankAccount(t.sourceNum));
							}
							t.setTargetAccount(MeritBank.getBankAccount(t.targetNum));
						}
					}
					for(CDAccount acc : accH.getCDAccounts()) {
						for(Transaction t : acc.getTransactions()) {
							if(t.sourceNum == -1) { t.setSourceAccount(MeritBank.getBankAccount(t.targetNum)); }
							else { t.setSourceAccount(MeritBank.getBankAccount(t.sourceNum)); }
							t.setTargetAccount(MeritBank.getBankAccount(t.targetNum));
						}
					}
				}
				line = sc.nextLine();
				int numOfFraudTrans = Integer.parseInt(line);
				for (int i = 0; i < numOfFraudTrans; i++) {
					line = sc.nextLine();
					
					Transaction t = Transaction.readFromString(line);
					
					// set source account
					if(t.sourceNum == -1) { t.setSourceAccount(MeritBank.getBankAccount(t.targetNum)); }
					else { t.setSourceAccount(MeritBank.getBankAccount(t.sourceNum)); }
					
					// set target account
					t.setTargetAccount(MeritBank.getBankAccount(t.targetNum));
					
					fraudQueue.addTransaction(t);	
				}			
			}
		sc.close(); 
		}
		catch(IOException e) {
			System.out.println("IO Issue");
			return false;
		}
		catch(NumberFormatException e) {
			System.out.println("Number Format Issue");
			return false;
		}
		catch(ParseException e) {
			System.out.println("Parse Issue");
			return false;
		}
		catch(Exception e) {
			System.out.println("Some Other Issue when read the file");
			return false;
		}
		return true;
	}
		
	static boolean writeToFile(String fileName) {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
			bw.write(String.valueOf(getNextAccountNumber()));
			bw.newLine();
			bw.write(String.valueOf(cdOfferings.length));
			bw.newLine();
			for(int i = 0; i < cdOfferings.length; i++) {
				bw.write(cdOfferings[i].writeToString());
				bw.newLine();
			}
			bw.write(String.valueOf(accountHolders.length));
			bw.newLine();
			for(int i = 0; i < accountHolders.length; i++) {
				bw.write(accountHolders[i].writeToString());
				bw.newLine();
				bw.write(String.valueOf(accountHolders[i].getNumberOfCheckingAccounts()));
				bw.newLine();
				for(int j = 0; j < accountHolders[i].getNumberOfCheckingAccounts(); j++) {
					bw.write(accountHolders[i].getCheckingAccounts()[j].writeToString());
					bw.newLine();
					bw.write(String.valueOf(accountHolders[i].getCheckingAccounts()[j].getTransactions().size()));
					bw.newLine();
					for(int k = 0; k < accountHolders[i].getCheckingAccounts()[j].getTransactions().size(); k++) {
						bw.write(accountHolders[i].getCheckingAccounts()[j].getTransactions().get(k).writeToString());
						bw.newLine();
					}
				}
				bw.write(String.valueOf(accountHolders[i].getNumberOfSavingsAccounts()));
				bw.newLine();
				for(int j = 0; j < accountHolders[i].getNumberOfSavingsAccounts(); j++) {
					bw.write(accountHolders[i].getSavingsAccounts()[j].writeToString());
					bw.newLine();
					bw.write(String.valueOf(accountHolders[i].getSavingsAccounts()[j].getTransactions().size()));
					bw.newLine();
					for(int k = 0; k < accountHolders[i].getSavingsAccounts()[j].getTransactions().size(); k++) {
						bw.write(accountHolders[i].getSavingsAccounts()[j].getTransactions().get(k).writeToString());
						bw.newLine();
					}
				}
				
				bw.write(String.valueOf(accountHolders[i].getNumberOfCDAccounts()));
				bw.newLine();
				for(int j = 0; j < accountHolders[i].getNumberOfCDAccounts(); j++) {
					
					bw.write(accountHolders[i].getCDAccounts()[j].writeToString());
					bw.newLine();
					bw.write(String.valueOf(accountHolders[i].getCDAccounts()[j].getTransactions().size()));
					bw.newLine();
					for(int k = 0; k < accountHolders[i].getCDAccounts()[j].getTransactions().size(); k++) {
						bw.write(accountHolders[i].getCDAccounts()[j].getTransactions().get(k).writeToString());
						bw.newLine();
					}
				}
			}
			
			bw.write(String.valueOf(fraudQueue.getFraudQueueSize()));
			int qSize = fraudQueue.getFraudQueueSize();
			for(int i = 0; i < qSize; i++) { 
				bw.newLine();
				bw.write(fraudQueue.getTransaction().writeToString());	
			}
			bw.close();
		}
		
		catch (Exception e) {
            System.out.println(e);
            return false;
        }
		return true;	
	}
		
	/**
	 * @param accountHolder
	 */
	public static void addAccountHolder(AccountHolder accountHolder) {
		AccountHolder[] temp = new AccountHolder[accountHolders.length + 1]; 
		for(int i = 0; i < accountHolders.length; i++) {
			temp[i] = accountHolders[i];
		}
		temp[temp.length - 1] = accountHolder;
		accountHolders = temp;
	}
	
	/**
	 * @return the accountHolder[] 
	 */
	static AccountHolder[]getAccountHolders() { return accountHolders; }
	
	/**
	 * @param depositAmount
	 * @return the bestOffering
	 */
	public static CDOffering getBestCDOffering(double depositAmount) {
		if(cdOfferings == null) {
			System.out.println("Sorry there are no offerings at this time");
			return null;
		}
		CDOffering best = null;
		for(CDOffering offering : cdOfferings) {
			if(best == null) { best = offering;}
			if (offering.getInterestRate() >= best.getInterestRate()) { best = offering; }
		}
		System.out.println("The Best is : " + best.getInterestRate());
		return best;
	}	

	/**
	 * @param depositAmount
	 * @return the secondBestCDOffering
	 */
	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		CDOffering best = getBestCDOffering(depositAmount);
		if(best == null) { return null; }
		CDOffering secondBest = null;
		for(CDOffering offering : cdOfferings) {
			if(secondBest == null) { secondBest = offering; }
			if (offering.getInterestRate() >= secondBest.getInterestRate() 
				&& offering.getInterestRate() < best.getInterestRate()) {
			secondBest = offering;	
			}
		}	
		System.out.println("Second best is : " + secondBest.getInterestRate());
		return secondBest;
	}		
	
//	public static CDOffering getSecondBestCDOffering(double depositAmount) {
//		
//		if (cdOfferings == null || MeritBank.cdOfferings.length <= 0) {
//			System.out.println("Sorry there are no offerings at this time");
//			return null;
//		}
//		CDOffering temp;
//	      //sort the array
//	      for (int i = 0; i < cdOfferings.length; i++) {
//	         for (int j = i + 1; j < cdOfferings.length; j++) {
//	            if (cdOfferings[i].getInterestRate() > cdOfferings[j].getInterestRate()) {
//	               temp = cdOfferings[i];
//	               cdOfferings[i] = cdOfferings[j];
//	               cdOfferings[j] = temp;
//	            }
//	         }
//	      }
//	      
//	      //return second largest element
//	      return cdOfferings[cdOfferings.length - 2];
//	}
	
	/**
	 * @return the cdOfferings
	 */
	public static CDOffering[] getCDOfferings() { return cdOfferings; }
	
	/**
	 * @param cdOfferings the cdOfferings to set
	 */
	public static void setCDOfferings(CDOffering[] offerings) { MeritBank.cdOfferings = offerings; }
	
	/**
	 * Removes all offerings
	 */
	public static void clearCDOfferings() { cdOfferings = null; }
	
	/**
	 * Removes all account holders
	 */
	public static void clearAccountHolders() { accountHolders = null; }
	
	/**
	 * @return the nextAccountNumber
	 */
	public static long getNextAccountNumber() {
//		nextAccountNumber++;
		return nextAccountNumber++; //first returns, then increments
	}
	
	/**
	 * @param accountNumber the nextAccountNumber to set
	 */
	public static void setNextAccountNumber(long accountNumber) { nextAccountNumber = accountNumber; }
						
	/**
	 * @return the balance of all accounts
	 */
	public static double totalBalances() {
		double balance = 0.0;
		for(AccountHolder ah : accountHolders) {
			balance += ah.getCombinedBalance();
		}
		return balance;
	}
	
	/**
	 * Calculates the future value of the account balance based on the interest 
	 * and number of years
	 * @param presentValue
	 * @param interestRate
	 * @param term: number of periods in years
	 * @return the future value
	 */
	public static double futureValue(double presentValue, int years, double intRate) {
		
		    if(years == 0) {
		    	return 1;
		    }
		    else if (years == 1) {
		    	return presentValue * (1 + intRate);
		    }
			// recursive call to futureValue()
		    else {
		    	return (1 + intRate) * futureValue(presentValue, years - 1, intRate);
		    }
	
//		return presentValue * Math.pow(1 + interestRate, term); 
	}
	
	/**
	 * Sorts the account holders in ascending order by the combined balance of their accounts 
	 * 
	 * @return the account holders
	 */
	public static AccountHolder[] sortAccountHolders() {
		Arrays.sort(accountHolders);
//		for(AccountHolder a : accountHolders)
//			System.out.println(a.toString());
		return accountHolders;
	}	
	
	public static boolean processTransaction(Transaction trans) //{
			throws NegativeAmountException, 
					ExceedsAvailableBalanceException,
					ExceedsFraudSuspicionLimitException {
		
			if(trans.getAmount() < 0) { 
				throw new NegativeAmountException("Amount must be > $0.00"); 
			} 
			
			if(trans.getAmount() > MAX_TRANSACTION_AMOUNT) { 
				getFraudQueue().addTransaction(trans);
				throw new ExceedsFraudSuspicionLimitException("Amount must be <= $1000.00"); 
			} 
			
			if(trans instanceof WithdrawTransaction) {
				if(trans.getAmount() > trans.getTargetAccount().getBalance()) {
					throw new ExceedsAvailableBalanceException("Withdraw amount > Account Balance");
				}		
			}
			
			if(trans instanceof TransferTransaction) {
				if(trans.getAmount() > trans.getSourceAccount().getBalance()) {
					throw new ExceedsAvailableBalanceException("Transfer amount > Source Account Balance");
				}
			}	

			trans.process();
		
		return true;
	}
	
	public static FraudQueue getFraudQueue() {
		FraudQueue q = new FraudQueue();
		return q;
	}
	
	public static BankAccount getBankAccount(long accountID) {
		for(AccountHolder accH : accountHolders){
			for(CheckingAccount acc : accH.getCheckingAccounts()){
//				System.out.println(acc.getAccountNumber());
				if(accountID == acc.getAccountNumber()){ return acc; }
			}
			for(SavingsAccount acc : accH.getSavingsAccounts()){
				if(accountID == acc.getAccountNumber()){ return acc; }
			}
			for(CDAccount acc : accH.getCDAccounts()){
				if(accountID == acc.getAccountNumber()){ return acc; }
			}
		}
		
		return null; 	
	}
}
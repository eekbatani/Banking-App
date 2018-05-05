/**
     * @author Ehsan Ekbatani
     */

package com.little.bank;
import org.finance.accounts.Account;
//@author Ehsan Ekbatani

import java.math.BigDecimal;
import java.util.*;

public class Bank {
	protected String bankName;
	protected ArrayList<Account> Accounts;

	
	
//Zero Argument constructor
	 /**
     * Default constructor for Bank Class
     */
	public Bank() {
		// initializes a Bank object with default "Seneca@York"
		this.bankName = "Seneca@York";
		this.Accounts = new ArrayList<Account>();
		}
	
//One Argument constructor
	/**
     * constructor that takes one argument
     * @param name
     */
	public Bank(String name) {
		if(name != null) {
		this.bankName = name;
		}
		else {
		this.bankName = "";
		}
		this.Accounts = new ArrayList<Account>();
	}
	/**
    * getter for bank name
    * @return string that contains the bank name
    */
	public String getName() {
		return this.bankName;
	}
	
	public ArrayList<Account> getAccounts() {
		return this.Accounts;
	}
	
	//Add accounts to arrayList if it doesnt already exist and is not null
	/**
     * add account to the bank
     * @param New Account
     * @return true if added, false if it already exists or null argument
     */
	public boolean addAccount( Account newAccount) {
		//create flag variable for conditions to have only one exit point
		boolean result = true;
		
		//check for null argument
		if(newAccount == null) {
			result = false;
		}
		else {
			//loop through all accounts to check for duplicate accounts
			for(int i =0; i <Accounts.size(); i++) {
				if(Accounts.get(i).getAccountNumber().equals(newAccount.getAccountNumber())) {
					result = false;
				}
			}
			//If all checks are passed proceed to add account to arraylist
		if (result == true) {
			Accounts.add(newAccount);
			}
		}
		return result;
	}
	 /**
     * Check if object is equals to account
     */
	public boolean equals(Bank src) {
		//create flag variable for conditions to have only one exit point
		boolean result = true;
		if (src instanceof Bank) {
			 if(this.bankName == src.bankName) {
				//loop through all accounts to ensure account 
					for(int i =0; i <Accounts.size(); i++) {
						//check if this instances account number matches passed in acc num
						if(this.Accounts.get(i).equals(src.Accounts.get(i)) == false) {
							//result is false if they do not match
							result = false;
						}
					}
			 }
			 else {
				 result = false;
			 }
		}
		else {
			result = false;
		}
		return result;
	}
	/**
	* searches accounts for the balance
    * @param balance on the account
    * @return array of the accounts that  match the search parameters
    */
	public Account[] searchByBalance(BigDecimal balance) {
		//construct array for return
		Account results[];
		if(balance.equals(null)) {
			results = null;
		}
		else {
		//int value for tracking number of matches and array index
		int matches = 0;
		
		//Loop through just to count the number of matches
		for(int i =0; i <Accounts.size(); i++) {
			if(this.Accounts.get(i).getAccountBalance().compareTo(balance)==0) {
				//increment number of matches to count total number of matches for creating array size
				matches++;
			}
		}
		//Create array of accounts with the size of matches counted
		results = new Account[matches];
		
		//Reset number of matches for next loop
		matches = 0;
		
		for(int i =0; i <Accounts.size(); i++) {
			if(this.Accounts.get(i).getAccountBalance().compareTo(balance)==0) {
				//Assign matches to array of search results
				results[matches] = this.Accounts.get(i);
				//increment number of matches and array index for next potential match
				matches++; 
			}
		}
		//If no matches after looping through entire array
		if (matches == 0) {
			results = null;
			}
		}
		return results;
	}
	/**
	* searches accounts for the account name
    * @param Name on the account
    * @return array of the accounts that  match the search parameters
    */
	public Account[] searchByAccountName(String AccountName) {
		//construct array for return
		Account results[];
		if(AccountName == null) {
			results = null;
		}
		else {
		//int value for tracking number of matches and array index
		int matches = 0;
		
		//Loop through just to count the number of matches
		for(int i =0; i <Accounts.size(); i++) {
			if(this.Accounts.get(i).getFullName().equals(AccountName)) {
				//increment number of matches to count total number of matches for creating array size
				matches++;
			}
		}
		//Create array of accounts with the size of matches counted
		results = new Account[matches];
		
		//Reset number of matches for next loop
		matches = 0;
		
		for(int i =0; i <Accounts.size(); i++) {
			if(this.Accounts.get(i).getFullName().equals(AccountName)) {
				//Assign matches to array of search results
				results[matches] = this.Accounts.get(i);
				//increment number of matches and array index for next potential match
				matches++; 
			}
		}
		//If no matches after looping through entire array
		if (matches == 0) {
			results = null;
			}
		}
		return results;
	}
	
	/**
	* searches accounts for the account number
    * @param Account Number
    * @return one account that  matches the search parameters  because account numbers are unique
    */
	public Account searchByAccountNumber(String AccountNumber) {
		
		//construct account variable for return
		Account results = null;
		if(AccountNumber == null) {
			results = null;
		}
		else {
		//int value for tracking number of matches and array index
		int matches = 0;
		
		for(int i =0; i <Accounts.size(); i++) {
			if(this.Accounts.get(i).getAccountNumber().equals(AccountNumber)) {
				//Assign matches to array of search results
				results = this.Accounts.get(i);
				//increment number of matches and array index for next potential match
				matches++; 
			}
		}
		//If no matches after looping through entire array
		if (matches == 0) {
			results = null;
			}
		}
		return results;
	}
	/**
     * remove account from the bank
     * @param accountNumber
     * @return removed account
     */
public Account removeAccount(String AccountNumber) {
		
		//construct account variable for return
		Account results = null;
		if(AccountNumber == null) {
			results = null;
		}
		else {
		//int value for tracking number of matches and array index
		int matches = 0;
		
		//Reset number of matches for next loop
		matches = 0;
		
		for(int i =0; i <Accounts.size(); i++) {
			if(this.Accounts.get(i).getAccountNumber().equals(AccountNumber)) {
				//Assign matches to array of search results
				results = this.Accounts.get(i);
				this.Accounts.remove(i);
				//increment number of matches and array index for next potential match
				matches++; 
			}
		}
		//If no matches after looping through entire array
		if (matches == 0) {
			results = null;
			}
		}
		return results;
	}
	
	
/**
 * create display string for output
 */
	
	public String toString() {
		String output;
		
		output = " *** Welcome to the Bank of "+ this.bankName +" *** \n"
				+ "It has " + Accounts.size() + " accounts. \n";
		for(int i =0; i <Accounts.size(); i++) {
			output +=  i+1 + ". number: " + Accounts.get(i).getAccountNumber() 
					   + ", name: " + Accounts.get(i).getFullName()
					   + ", balance: $" + Accounts.get(i).getAccountBalance() + "\n";
		}
		return output;
	}
	
	
}


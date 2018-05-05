/**
     * @author Ehsan Ekbatani
     */


package org.finance.accounts;
import java.math.BigDecimal;

public class Savings extends Account {

	private BigDecimal interestRate;
	//use default constructor notation
		/**
	     * zero arguments constructor
	     */
	public Savings() {
		super();
		this.interestRate = new BigDecimal(0.3);
	}
	/**
     * constructor with arguments
     * @param name
     * @param number
     * @param balance
     * @param InterestRate
     */
	//@param Account Holder's name, Account Number, Initial Balance, interest rate
	public Savings(String name, String accNumber, String balance, String IR) {
		super(name, accNumber, balance);
		this.interestRate = new BigDecimal(IR);
	}

/**
 * create display string for output
 */
	public String toString() {
		
		String output;

		output = super.toString() + "type: SAVINGS \n" +
				"annual interest rate: " + this.interestRate + "%\n";
		
		return output;
	}
	
	/**
	 * Overloaded equals() method
	 * check if object is the same as Chequing
	 * @param takes an object, should be an account for correction functionality
	 * @return true if objects are equal, false if not equal or null 
	 */
	public boolean equals(Object src) {
		boolean result = false;

		Savings temp = (Savings) src;

		if (src instanceof Savings && src != null) {
			if (super.getFullName() != null && super.getAccountNumber() != null) {
				if (super.getFullName().equals(temp.getFullName())
						&& super.getAccountNumber().equals(temp.getAccountNumber())
						&& super.getAccountBalance().equals(temp.getAccountBalance())
						&& this.interestRate.compareTo(temp.interestRate)==0) {
					result = true;
				}
			}
		}
		return result;
	}

}

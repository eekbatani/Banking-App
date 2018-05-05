/**
     * @author Ehsan Ekbatani
     */

package org.finance.accounts;

import java.math.BigDecimal;


public class GIC extends Account{
	private int investmentPeriod;
	private BigDecimal interestRate;

    /**
     * zero arguments constructor
     */
	public GIC() {
		super();
		this.investmentPeriod = 1;
		this.interestRate = new BigDecimal(0.0125);
	}
	
	/**
	 * constructor with arguments
	 * @param Name
	 * @param Number
	 * @param Balance
	 * @param Investment Period
	 * @param Interes Rate
	 */
	public GIC(String name, String accNumber, String balance, int IP, String IR) {
		super(name, accNumber, balance);
		this.investmentPeriod = IP;
		this.interestRate = new BigDecimal(IR);
	}
	
	/**
    * no deposits allowed for GIC 
    * @param amount of money
    * @return always false
    */
	public boolean deposit(BigDecimal amount) {
		return false;
	}
	
	/**
	    * no withdrawals allowed for GIC 
	    * @param amount of money
	    * @return always false
	    */
	public boolean withdraw(BigDecimal amount) {
		return false;
	}
	
	 /**
     * get Balance At Maturity
     * @return balance
     */
	//MUST SUPRESS DEPRICATION WARNINGS IN ORDER TO SUCCESSFULLY COMPILE
	@SuppressWarnings("deprecation")
	public BigDecimal getBalanceAtMaturity() {
		//convert percentage to decimal value
		BigDecimal decRate = interestRate.divide(new BigDecimal(100));
		//Use BigDecimal library functions to apply compound interest formula
		BigDecimal value = this.accBalance.multiply(decRate.add(new BigDecimal("1")).pow(investmentPeriod));
		value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		return value;
	}
	/**
	 * create display string for output
	 */
	public String toString() {
		
		String output;
		
		output = super.toString() + 
				"type: GIC \n" +
				"annual Interest Rate: " + this.interestRate + "%\n" +
				"period of Investment: "+ this.investmentPeriod +"\n" +
				"new Balance at Maturity: $"+ this.getBalanceAtMaturity() + "\n";
		
		return output;			
	}
	/**
	 * Overloaded equals() method
	 * check if object is the same as GIC
	 * @param takes an object, should be an account for correction functionality
	 * @return true if objects are equal, false if not equal or null 
	 */
	public boolean equals(Object src) {
		boolean result = false;
		
		GIC temp = (GIC) src;
		
		if (src instanceof GIC && src != null) {
			if(super.getFullName() != null && super.getAccountNumber() != null) {
				if(super.getFullName().equals(temp.getFullName()) &&
					super.getAccountNumber().equals(temp.getAccountNumber())&&
					super.getAccountBalance().equals(temp.getAccountBalance())&&
					this.investmentPeriod == (temp.investmentPeriod) &&
					this.interestRate.compareTo(temp.interestRate)==0) {
					result = true;
				}
			}
		}
		return result;
	}
	
	
}

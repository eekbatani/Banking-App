/**
     * @author Ehsan Ekbatani
     */

package org.finance.accounts;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Chequing extends Account{
	
	protected BigDecimal serviceCharge;
	protected BigDecimal totalCharge;
	private ArrayList<BigDecimal> transactions;
	
	
	//@param Account Holder's name, Account Number, Initial Balance, Service Charge rate
	@SuppressWarnings("deprecation")
	/**
    * constructor with arguments
    * @param name
    * @param number
    * @param balance
    * @param charge
    */
	public Chequing(String name, String accNumber, String balance, String SC) {
		super(name, accNumber, balance);
		this.serviceCharge = new BigDecimal(SC);
		this.transactions = new ArrayList <BigDecimal>() ;
		this.totalCharge = new BigDecimal("0").setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	/**
     * zero argument constructor
     */
	@SuppressWarnings("deprecation")
	public Chequing() {
		super();
		this.serviceCharge = new BigDecimal(0.25);
		this.transactions = new ArrayList <BigDecimal>() ;
		this.totalCharge = new BigDecimal("0").setScale(2, BigDecimal.ROUND_HALF_UP);;
	}
	/**
     * getter for service charge
     * @return a service charge value
     */
	public BigDecimal getSC() {
		return this.serviceCharge;
	}
	/**
     * getter for service transactions
     * @return arraylist of transactions
     */
	public ArrayList<BigDecimal> getTrans() {
		return this.transactions;
	}
	
	/**
	 * deposit money to account 
	 * @param amount
	 * @return true if successful 
	 */
	  public boolean deposit(BigDecimal amount) {
	  boolean result = true;
	  
	  if (amount.signum() == -1){
	   result = false;
	  }
	  else{
	   accBalance = accBalance.add(amount);
	   transactions.add(amount);
	  }
	  return result;
	 }
	
	/**
	 * withdraw money from account 
	 * @param amount
	 * @return true if successful, false if amount larger than balance or negative value
	 * */
	public boolean withdraw(BigDecimal amount) {
	  boolean result = true;
	  
	  if (amount.signum() == -1 || this.accBalance.subtract(amount.add(serviceCharge)).signum() == -1){
	   result = false;
	  }
	  else{
	   this.accBalance = this.accBalance.subtract(amount.add(serviceCharge)); //Deducting withdrawl amount plus service charge from chequing account
	   transactions.add(amount.negate());
	   totalCharge.add(this.serviceCharge);
	  }
	  
	  return result;
	 }
	
public String toString() {
		
		String output;
		
		output = super.toString() + 
				"type: CHEQUING\n" +
				"service charge: $" + this.serviceCharge + "\n" +
				"number of transactions: "+ this.transactions.size() +"\n" +
				"total amount of service charge: $"+totalCharge +"\n";
		
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
	
	Chequing temp = (Chequing) src;
	
	if (src instanceof Chequing && src != null) {
		if(super.getFullName() != null && super.getAccountNumber() != null) {
			if(super.getFullName().equals(temp.getFullName()) &&
				super.getAccountNumber().equals(temp.getAccountNumber())&&
				super.getAccountBalance().equals(temp.getAccountBalance())&&
				this.serviceCharge.compareTo(temp.serviceCharge)==0 &&
				this.transactions.equals(temp.transactions)) {
				result = true;
			}
		}
	}
	return result;
}

}


/**
     * @author Ehsan Ekbatani
     */

package org.finance.accounts;
import java.math.BigDecimal;
import java.lang.StringBuilder;
import java.math.RoundingMode;
public class Account {
	protected String fullName;
	protected String accNum;
	protected BigDecimal accBalance;
	protected BigDecimal startingBalance;


//use default constructor notation
	/**
     * zero arguments constructor
     */
public Account() { this("", "", "0.00");}

/**
 * constructor with arguments
 * @param Name
 * @param Number
 * @param Currentbalance
 * @param StartingBalance
 */
@SuppressWarnings("deprecation")
public Account(String name, String accNumber, String balance) {
	//call setter functions to assign values b/c they contain if 
	//statements for handling invalid parameter values 
	this.setFullName(name);
	this.setAccNumber(accNumber);
	//set the scale so that all values are represented to two decimal places as currency
	this.setAccountBalance(new BigDecimal(balance).setScale(2, BigDecimal.ROUND_HALF_UP));
	this.startingBalance = new BigDecimal(this.accBalance.toPlainString()).setScale(2, BigDecimal.ROUND_HALF_UP);
}

/**
 * create display string for output
 */
public String toString() {
	String output;
	
	output = "number: " + this.accNum + ", " +
			 "name: " + this.fullName + "\n" +
			 "starting Balance: $" + this.startingBalance +", " + "current Balance: $" + this.accBalance +"\n" ;
	
	return output;
}

/**
 *  setter for name of the customer
 * @param name
 */

public void setFullName(String name) {
	if(name == null) {
		this.fullName = "";
	}
	this.fullName = name;
}

/**
 * setter for account number of the customer
 * @param number
 */
public void setAccNumber(String number) {
	if(number == null) {
		this.accNum = "";
	}
	this.accNum = number;
}
//@param account balance
public void setAccountBalance(BigDecimal bal) {
	if (bal.signum() == -1 || bal ==null) {
		this.accBalance = new BigDecimal(0);
	}
	else {
	this.accBalance = bal;
	}
}


//Getter methods

/**
 * getter for name of the customer
 * @return name of the customer
 */
public String getFullName() {
	return this.fullName;
}
/**
 * getter for account number 
 * @return Account number 
 */
public String getAccountNumber() {
	return this.accNum;
}
/**
 * getter for current account balance 
 * @return current account balance 
 */
public BigDecimal getAccountBalance() {
	return this.accBalance;
}
/**
 * getter for starting account balance 
 * @return starting account balance
 */
public BigDecimal getStartingAccountBalance()
{
    return this.startingBalance;
}

/**
 * Overloaded equals() method
 * check if object is the same as Account
 * @param takes an object, should be an account for correction functionality
 * @return true if objects are equal, false if not equal or null 
 */
public boolean equals(Object src) {
	boolean result = false;
	
	if (src instanceof Account) {
		
		Account temp = (Account) src;
		//check for nulls
		
			if (this.fullName.equals(temp.fullName)
				&& this.accNum.equals(temp.accNum)
				&& this.accBalance.compareTo(temp.accBalance)==0) {
				result = true;
			
		}
			
	}
	return result;
	}

/**
 * deposit money to account 
 * @param amount
 * @return true if successful 
 */
public boolean deposit(BigDecimal amount){
	boolean result = true;
	//check to ensure negative value is not passed in
	if (amount.signum() == -1){
		result = false;
	}
	else{
		accBalance = accBalance.add(amount);
	}
	return result;
	}


/**
 * withdraw money from account 
 * @param amount
 * @return true if successful, false if amount larger than balance or negative value
 */public boolean withdraw(BigDecimal amount){
	boolean result = true;
	//check to ensure withdrawl is not greated than balance
	if (amount.signum() == -1 ||accBalance.compareTo(amount)== -1){
	 result = false;
}
	else{
		accBalance = accBalance.subtract(amount); //Deducting withdrawl amount plus service charge from chequing account
	}
	return result;

	}

}










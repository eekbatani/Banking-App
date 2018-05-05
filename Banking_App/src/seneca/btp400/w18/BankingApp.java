
//@author Ehsan Ekbatani
package seneca.btp400.w18;
import org.finance.accounts.*;
import org.finance.accounts.GIC;
import org.finance.accounts.Savings;
import org.finance.accounts.Account;
import com.little.bank.Bank;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.lang.StringBuilder;


public class BankingApp {
	
	
	public static void main(String[] args) {
		//create variable for receiving user input
		Scanner input = new Scanner(System.in);
		
		//create string for storing user input information(s)
		String info;
		
		//create string to store account type for identifying user input
		String accType;
		
		//create bank object with school name
		Bank bank = new Bank("Ehsan Ekbatani");
	
		//create integer for storing user menu selection
		int mainChoice;
		//call load bank to populate bank accounts per assignment specs
		loadBank(bank);
		
		
		//create flag variable to indicate when the program should continue/stop running
		boolean run = true;
		
		//while loop to continue running program until run is false
		while(run == true) {
			
			//call display menu to ouput menu options
			displayMenu(bank.getName());
			
			//call menuSelect function to capture user input
			mainChoice = menuSelect();
			
			//use switch statement, ideal for menu cases
			switch(mainChoice) {
			
				//OPEN AN ACCOUNT
				case 1:  System.out.println("\nPlease enter information(e.g. account type, name,\n" + 
						"account number, balance, interest rate ) at one line:");
						
						//store whole line of user input into string
						info = input.nextLine();
						//create to scanner object to parse out the account type
						Scanner parser = new Scanner(info);
						
						//set delimiter for token to be any whitepace around a comma
						parser.useDelimiter("\\s*,\\s*");
						
						//store the account type token into a string
						accType = parser.next();
						
						//convert to upper case in case user enters lower case input chars
						accType = accType.toUpperCase();
						
						//check if user wants Savings account (SAV)
						if(accType.equals("SAV")) {
							//call customer made parse function to parse line information and construct account. store in temporary variable
							Savings temp = parseSavings(info);
							//if the parse is successful
							if(temp != null) {
								//add the newly created account to the arrayList of accounts in the bank
								//store it in boolean to check if account doesnt already exists
								boolean openned = bank.addAccount(temp);
								
								//if it does not alraedy exist, the account is sucessfull added
								if (openned== true) {
									//call function to display output for newly openned account
								openAccMsg(temp);
								}
								//if it exists, display failed error message
								else {
									openFailed();
								}
							}
							else {
								openFailed();
							}
						}
						//check if user wants Chequings account (CHQ)
						else if(accType.equals("CHQ")) {
							//call customer made parse function to parse line information and construct account. store in temporary variable
							Chequing temp = parseCHQ(info);
							//if the parse is successful
							if(temp != null) {
								//add the newly created account to the arrayList of accounts in the bank
								//store it in boolean to check if account doesnt already exists
								boolean openned = bank.addAccount(temp);
								
								//if it does not alraedy exist, the account is sucessfull added
								if (openned== true) {
									//call function to display output for newly openned account
								openAccMsg(temp);
								}
								//if it exists, display failed error message
								else {
									openFailed();
								}
							}
							else {
								openFailed();
							}
						}
						//check if user wants Chequings account (CHQ)
						else if(accType.equals("GIC")) {
							//call customer made parse function to parse line information and construct account. store in temporary variable
							GIC temp = parseGIC(info);
							if(temp != null) {
								///add the newly created account to the arrayList of accounts in the bank
								//store it in boolean to check if account doesnt already exists
								boolean openned = bank.addAccount(temp);
								
								//if it does not alraedy exist, the account is sucessfull added
								if (openned== true) {
									//call function to display output for newly openned account
								openAccMsg(temp);
								}
								//if it exists, display failed error message
								else {
									openFailed();
								}
							}
							else {
								openFailed();
							}
						}
						else{
							openFailed();
						}
						break;
						
				case 2: //REMOVE ACCOUNT
						System.out.println("Please enter account number:");
						//receive account number from 
						info = input.nextLine();
						info = info.toUpperCase().trim();
						//call the remove function an temporarily store the one it returns for display purposes
						Account temp = bank.removeAccount(info);
						//if an account is not found and it returns null
						if(temp == null) {
							
							System.out.println("\n*** FAILED: ACCOUNT CANNOT BE CLOSED! ***\n");
						}
						//if the account is successfully found and removed
						else {
							//display information of the closed account
							System.out.println("\n+ Account Closed:\n" + temp.toString());
							
						}
						break;		

				case 3: //UPDATE ACCOUNT
						//display subMenu
						System.out.println("a. Deposit Money. \nb. Withdraw Money.");
						//get user input
						info = input.nextLine();
						//trim whitespace in case user accidentally inputs
						info = info.trim();
						//if option a selected
						if(info.equals("a")) {
							System.out.println("Please enter the account number and a amount (e.g. A1234, 1500): ");
							info = input.nextLine();
							Scanner parser2 = new Scanner(info);
							parser2.useDelimiter("\\s*,\\s*");
							String AccNum = parser2.next();
							AccNum = AccNum.toUpperCase();
							String amount = parser2.next();
							amount = amount.trim();
							Account temp2 = bank.searchByAccountNumber(AccNum);
						
							if(temp2 != null) {
								boolean success = temp2.deposit(new BigDecimal(amount));
								if(success == true) {
									StringBuilder updated = new StringBuilder("\n+ Account Updated:\n").append(temp2.toString());
									System.out.println(updated);
								}
								else {
									System.out.println("\n*** FAILED: ACCOUNT CANNOT BE UPDATED! ***\n");
								}	
							}
							else {
								System.out.println("\n*** FAILED: ACCOUNT CANNOT BE UPDATED! ***\n");
							}	
						}
						if(info.equals("b")) {
							System.out.println("Please enter the account number and an amount (e.g. A1234, 1500):");
							info = input.nextLine();
							Scanner parser2 = new Scanner(info);
							parser2.useDelimiter("\\s*,\\s*");
							String AccNum = parser2.next();
							AccNum = AccNum.toUpperCase();
							String amount = parser2.next();
							amount = amount.trim();
							Account temp2 = bank.searchByAccountNumber(AccNum);
							if(temp2 != null) {
								boolean success = temp2.withdraw(new BigDecimal(amount));
								if(success == true) {
									//use string builder as per assignment specs to append account info to notifcation
									StringBuilder updated = new StringBuilder("\n+ Account Updated:\n").append(temp2.toString());
									System.out.println(updated);
								}
								else {
									System.out.println("\n*** FAILED: ACCOUNT CANNOT BE UPDATED! ***\n");
								}	
							}
							else {
								System.out.println("\n*** FAILED: ACCOUNT CANNOT BE UPDATED! ***\n");
							}	
							parser2.close();
						}
						
						break;
				
				case 4:	//SEARCH FOR ACCOUNTS
						//display subMenu
						System.out.println("a. Search by account balance. \nb. Search by account name. \nc. Search by account number");
						
						//receive user input
						info = input.nextLine();
						info = info.trim();
						
						if(info.equals("a")) {
							//prompt user to input balance
							System.out.println("Please enter the balance on the account (e.g. 1500):");
							info = input.nextLine();
							//trim potential whitespace
							info = info.trim();
							//call search by balance and temporarily store the results in an array
							Account[] tempArr = bank.searchByBalance(new BigDecimal(info));
							//if the array contains results (not empty)
							if(tempArr.length > 0) {
								//display accounts in array
								System.out.println("\n+ Account(s) Retrieved:" );
								//use list accounts to display each account in array
								listAccounts(tempArr);
							}
							else {
								System.out.println("\n*** FAILED: ACCOUNT CANNOT BE RETRIEVED! ***\n");
							}
						}
						if(info.equals("b")) {
							//prompt user to input balance
							System.out.println("Please enter the name on the account (e.g. John Doe):");
							info = input.nextLine();
							//trim potential whitespace
							info = info.trim();
							//call search by balance and temporarily store the results in an array
							Account[] tempArr = bank.searchByAccountName(info);
							//if the array contains results (not empty)
							if(tempArr.length > 0) {
								//display accounts in array
								System.out.println("\n+ Account(s) Retrieved:" );
								listAccounts(tempArr);
							}
							else {
								System.out.println("\n*** FAILED: ACCOUNT CANNOT BE RETRIEVED! ***\n");
							}
						}
						if(info.equals("c")) {
							//prompt user to input balance
							System.out.println("Please enter the account number (e.g. A1234):");
							info = input.nextLine();
							info = info.toUpperCase().trim();
							//call search by balance and temporarily store the results in an temporary account object
							Account tempRes = bank.searchByAccountNumber(info);
							if(tempRes != null) {
								//display found account information
								System.out.println("\n+ Account Retrieved:" );
								displayAccount(tempRes);
							}
							else {
								System.out.println("\n*** FAILED: ACCOUNT CANNOT BE RETRIEVED! ***\n");
							}
						}
						break;
						
				case 5: //DISPLAY ACCOUNTS
						//loop through entire arrayList of accounts and display all their information
						for(int i =0; i < bank.getAccounts().size() ; i++) {
						displayAccount(bank.getAccounts().get(i));
						}
						break;
					
				case 6: //EXIT
						System.out.println("\nThank you!\n" );
						run = false;
						break;
			
			}
		
		}
		input.close();
		
		
		
		
	}
	 /**
     * display menu with bank name, time and options
     * @param bankName
     */
	public static void displayMenu(String bankName) {
		//store local date n time in variable
		LocalDateTime date_ = LocalDateTime.now(); 
		//set date formatter object to match output from assignment specs
		DateTimeFormatter format = DateTimeFormatter.ofPattern("hh:mm a LLLL d, YYYY");
		
		
		StringBuilder output = new StringBuilder ("### Welcome to the Bank of ").append(bankName).append(" ###\n");
				//apply format to current date and time
				output.append(date_.format(format)).append("\n" +
				"1. Open an account." + "\n" +
				"2. Close an account." + "\n" +
				"3. Update an Account." + "\n" +
				"4. Search." + "\n" +
				"5. List all accounts." + "\n" +
				"6. Exit" + "\n");

		System.out.println(output);
	}
	
	/**
     * information about many accounts
     * @param listOfAccounts
     */
	public static void listAccounts(Account[] listOfAccounts) {
		  for( int i = 0; i < listOfAccounts.length; i++) {
		   displayAccount(listOfAccounts[i]);
		  }  
	}
	/**
     * information about single account
     * @param account
     */
	public static void displayAccount(Account acc) {
		  System.out.println(acc.toString());
		  }  
	
	public static int menuSelect() {
		System.out.println("Please enter your choice: ");
		Scanner input = new Scanner(System.in);
		int selection = input.nextInt();
		return selection;
	} 
	/*
    * parses string contanting account into tokens and passes into constructor to open the account
    * @param accountInfo
    * @returns Savings Account
    */
	public static Savings parseSavings(String accountInfo){
		
		String [] accInfo = accountInfo.split(",");
		if(accInfo.length == 5){
			Savings newAcc = new Savings(accInfo[1].trim(), accInfo[2].trim(), accInfo[3].trim(), (accInfo[4].trim()));
			return newAcc;
		}
		else {
			return null;
		}
	}
	/*
	    * parses string containing account into tokens and passes into constructor to open the account
	    * @param accountInfo
	    * @returns Chequings Account
	    */
	public static Chequing parseCHQ(String accountInfo){
		
		String [] accInfo = accountInfo.split(",");
		if(accInfo.length == 5){
			Chequing newAcc = new Chequing(accInfo[1].trim(), accInfo[2].trim(), accInfo[3].trim(), (accInfo[4].trim()));
			return newAcc;
		}
		else {
			return null;
		}
	}
	/*
	    * parses string containing account into tokens and passes into constructor to open the account
	    * @param accountInfo
	    * @returns GIC Account
	    */
	public static GIC parseGIC(String accountInfo){
	
		String [] accInfo = accountInfo.split(",");
		if(accInfo.length == 6){
			GIC newAcc = new GIC(accInfo[1].trim(), accInfo[2].trim(), accInfo[3].trim(), Integer.parseInt(accInfo[4].trim()), (accInfo[5].trim()));
			return newAcc;
	}
	else {
		return null;
	}
}
	/*
	    * displays output for successfully openning an account
	    * @param Account
	    */
	public static void openAccMsg(Account acc){
		System.out.println("\n+ Account Opened:");
		 displayAccount(acc);
	}
	/*
	    * displays output for successfully failing to open an account
	    */
	public static void openFailed(){
		System.out.println("\n*** FAILED: ACCOUNT CANNOT BE OPENED! ***\n");
	}
	 /**
     * adds two Savings, two Chequing, two GIC accounts to the bank for testing purposes as per assignment specs
     * @param bank
     */
	public static void loadBank(Bank bank) {
		  //adds pre-defined account objects to the bank's arraylist of accounts as per assignment specs.
		  bank.addAccount(new GIC("John Doe", "D1234","6000.00",2,"1.5"));
		  bank.addAccount(new Chequing("John Doe", "E5678","15000.00", "0.75"));
		  bank.addAccount(new Savings("John Doe", "F9801", "8000.00", "0.15"));
		  
		  
		  bank.addAccount(new GIC("Mary Ryan", "A1234", "15000.00", 4, "2.5"));
		  bank.addAccount(new Chequing("Mary Ryan", "B5678", "15000.00","0.75"));
		  bank.addAccount(new Savings("Mary Ryan", "C9012", "8000.00", "0.15"));
	}
}
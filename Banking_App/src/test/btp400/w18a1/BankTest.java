package test.btp400.w18a1;
import java.math.BigDecimal;
import static org.junit.Assert.*;
import org.finance.accounts.Savings;
import org.finance.accounts.Account;

import org.finance.accounts.GIC;
import com.little.bank.Bank;

import seneca.btp400.w18.BankingApp;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;


public class BankTest {
	
	private static Bank b1= new Bank("Ehsan Ekbatani");
	private static Bank b1Copy= new Bank("Ehsan Ekbatani");
	private static Bank b2= new Bank();
	private static Bank bAcc = new Bank("Tester");
	private static Bank bAccCopy= new Bank("Tester");
	private static Bank bRem= new Bank("Remmover");
	private static Bank bStr= new Bank("String Tester");
	private static Bank bNull= new Bank(null);
	private static Bank bSearch;

	
	private static Account acc1 = new Account("Ehsan Ekbatani", "A1234", "-1");
	private static Account acc2 = new Account("John Doe", "B1234","6000.00");
	private static Account acc2Copy = new Account("John Doe", "B1234","6000.00");
	private static Account acc3 = new Account("Peter Liu", "C1234", "5000.00");
	private static Account acc4 = new GIC("John Doe", "D1234","6000.00",2,"1.5");
	//private static Account[] AccArr
	
	
	@Before
	public void setUp() {
		
		
		bSearch = new Bank("Search Tester");
		BankingApp.loadBank(bSearch);
	}
	
	@Test
	public void testgetName() {
		assertEquals("Ehsan Ekbatani", b1.getName());
		assertEquals("Seneca@York", b2.getName());
		
		
		//Null input should set name as  an empty string
		assertEquals("", bNull.getName());
	}
	
	
	@Test
	public void testAddAccount() {
		assertEquals(true, bAcc.addAccount(acc1));
		assertEquals(true, bAcc.addAccount(acc2));
		assertEquals(true, bAcc.addAccount(acc3));
		//should return false if duplicate account tries to be added, all account numbers must be unique
		assertEquals(false, bAcc.addAccount(acc2Copy));
		
		assertEquals(true, bAccCopy.addAccount(acc1));
		assertEquals(true, bAccCopy.addAccount(acc2));
		assertEquals(true, bAccCopy.addAccount(acc3));
	}
	
	@Test
	public void testRemoveAccount() {
		assertEquals(true, bRem.addAccount(acc1));
		assertEquals(true, bRem.addAccount(acc2));
		assertEquals(true, bRem.addAccount(acc3));
		
		assertEquals(acc1, bRem.removeAccount("A1234"));
		assertEquals(acc2, bRem.removeAccount("B1234"));
		assertEquals(acc3, bRem.removeAccount("C1234"));
		//should return null if account does not exits
		assertEquals(null, bRem.removeAccount("XXXX"));
	}
	
	//tests SearchByBalance(), SearchByAccountName(), SearchByAccountNumber() with VALID search parameters
	@Test
	public void testSuccessfulSearches() {
		
		//create array storing  search results 
		Account[] SbAccArr = bSearch.searchByBalance(new BigDecimal(6000.00));
		
		//create array of holding what search results should be
		Account[] searchBalanceResults = new Account[] {acc4};
		
		//test if search results match expected data
		assertTrue(SbAccArr.length == 1);
		assertArrayEquals(searchBalanceResults, SbAccArr);
		
		//create array storing  search results 
		Account[] SnAccArr = bAcc.searchByAccountName("Ehsan Ekbatani");
		//create array of holding what search results should be
		Account[] searchNameResults = new Account[]  {acc1};
		
		//test if search results match expected data
		assertTrue(SnAccArr.length == 1);
		assertArrayEquals(searchNameResults, SnAccArr);
		assertEquals(acc2, bAcc.searchByAccountNumber("B1234"));
		
		
		
	}
	//tests SearchByBalance(), SearchByAccountName(), SearchByAccountNumber() with VALID search parameters
	@Test
	public void testFailedSearches() {
		
		//failed searches should return null
		assertNull(bAcc.searchByBalance((new BigDecimal(5678))));
		assertNull(bAcc.searchByAccountNumber("-1"));
		assertArrayEquals(null, bAcc.searchByAccountName("NoName"));
		
		
	}
	
	
	@Test
	public void testEqualsObject() {
		assertEquals(true, b1.equals(b1Copy));
		assertEquals(true, bAcc.equals(bAccCopy));
		assertEquals(false, b1.equals(b2));		
	}
	
	
	
	@Test
	public void testToString() {
		
		
		String s =  " *** Welcome to the Bank of Ehsan Ekbatani *** \nIt has 0 accounts. \n"; 
		assertEquals(s, b1.toString());
		
		assertEquals(true, bStr.addAccount(acc1));
		assertEquals(true, bStr.addAccount(acc2));
		assertEquals(true, bStr.addAccount(acc3));
		
		String s2 = " *** Welcome to the Bank of String Tester *** \n" + 
				"It has 3 accounts. \n" + 
				"1. number: A1234, name: Ehsan Ekbatani, balance: $0\n" + 
				"2. number: B1234, name: John Doe, balance: $6000.00\n" + 
				"3. number: C1234, name: Peter Liu, balance: $5000.00\n";
		
		assertEquals(s2, bStr.toString());
		
		
	}

	
	
}
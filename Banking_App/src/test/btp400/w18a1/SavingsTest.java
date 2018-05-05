/**
     * @author Ehsan Ekbatani
     */
package test.btp400.w18a1;
import java.math.BigDecimal;
import static org.junit.Assert.*;
import org.finance.accounts.Savings;


import org.junit.Test;




public class SavingsTest {
	
	private static Savings s1 = new Savings("Ehsan Ekbatani", "A1234", "1500.00", "3.6");
	private static Savings s1Copy = new Savings("Ehsan Ekbatani", "A1234", "1500.00", "3.6");
	private static Savings s2 = new Savings();
	private static Savings sNeg = new Savings("Ehsan Ekbatani", "B3545", "-1", "3.6");
	private static Savings sw = new Savings("Ehsan Ekbatani", "A1234", "2000.00", "5.5");
	private static Savings sd = new Savings("Ehsan Ekbatani", "A1234", "2000.00", "2.1");
	
	@Test
	

	public void testSavings2() {
		assertEquals(s2.getFullName(), "");
		assertEquals(s2.getAccountNumber(), "");
		assertEquals(0, s2.getAccountBalance().compareTo(new BigDecimal(0.00)));
		
	}

	@Test
	public void SavingsNegativeBalance() {
		assertEquals(0, sNeg.getAccountBalance().compareTo(BigDecimal.ZERO));
		
	}


	@Test
	public void testEqualsObject() {
		assertEquals(s1, s1Copy);
		assertEquals(false, s1.equals(s2));
	}
	
	@Test
	public void testGetters() {
		assertEquals(s1, s1Copy);
		assertEquals(s1.getFullName(), s1Copy.getFullName());
		assertEquals(s1.getAccountNumber(), s1Copy.getAccountNumber());
		assertEquals(0,s1.getAccountBalance().compareTo(s1Copy.getAccountBalance()));
	}
	
	

   
	@Test
	public void testToString() {
		
		String s =   "number: A1234," + " name: Ehsan Ekbatani\n" + 
					"starting Balance: $1500.00," + " current Balance: $1500.00\n" +
					 "type: SAVINGS \n" +
					"annual interest rate: 3.6%\n";
					

		assertEquals(s, s1.toString());
	}
	
	@Test
	public void testWithdraw() {		

		sw.withdraw(new BigDecimal(500.00));
		
		// out of bounds value should return false
		assertEquals(false,sw.withdraw(new BigDecimal(-1)));
		
		//ensure amounts were withdrawn correctly
		//this is the way to corrects assert equals for a BigDecimal according to documentation - to prevent scale inequality issues
		assertEquals(0, new BigDecimal(1500.00).compareTo(sw.getAccountBalance()));
		
	}
	@Test
	public void testDeposit() {		

		sd.deposit(new BigDecimal(500.00));
		//ensure amounts were withdrawn correctly
		//this is the way to corrects assert equals for a BigDecimal according to documentation - to prevent scale inequality issues
		assertEquals(false, sd.deposit(new BigDecimal(-1)));
		assertEquals(0, new BigDecimal(2500).compareTo(sd.getAccountBalance()));
	}
}

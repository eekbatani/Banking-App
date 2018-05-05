/**
     * @author Ehsan Ekbatani
     */
package test.btp400.w18a1;
import static org.junit.Assert.*;
import org.junit.Test;
import java.math.BigDecimal;
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import org.finance.accounts.Chequing;


public class ChequingTest {


	private static Chequing c1 = new Chequing("Ehsan Ekbatani", "A1234", "2000.00", "0.50");
	private static Chequing c1Copy = new Chequing("Ehsan Ekbatani", "A1234", "2000.00", "0.50");
	private static Chequing cw = new Chequing("Ehsan Ekbatani", "A1234", "2000.00", "0.50");
	private static Chequing cd = new Chequing("Ehsan Ekbatani", "A1234", "2000.00", "0.50");
	private static Chequing c2 = new Chequing();
	private static Chequing cNeg = new Chequing("Ehsan Ekbatani", "A1234", "-1", "0.50");
	
	@Test
	public void testChequing2() {
		assertEquals(c2.getFullName(), "");
		assertEquals(c2.getAccountNumber(), "");
	
		assertEquals(c2.getSC(), (new BigDecimal(0.25)));
		assertEquals(c2.getTrans(), new ArrayList <BigDecimal>());
		
	}
	@Test
	public void ChequingNegativeBalance() {
		assertEquals(0, cNeg.getAccountBalance().compareTo(new BigDecimal (0)));
		
	}

	@Test
	public void testEqualsObject() {
		assertEquals(c1, c1Copy);
		assertEquals(false, c1.equals(c2));
		//negative value passed in for balance should set balance = 0;
		
	}

	@Test
	public void testToString() {
		
		String s =  "number: A1234, name: Ehsan Ekbatani\n" + 
				"starting Balance: $2000.00, current Balance: $2000.00\n" + 
				"type: CHEQUING\n" + 
				"service charge: $0.50\n" + 
				"number of transactions: 0\n" + 
				"total amount of service charge: $0.00\n";
					

		assertEquals(s, c1.toString());
	}

	@Test
	public void testWithdraw() {		

		cw.withdraw(new BigDecimal(500.00));
		
		// out of bounds value should return false
		assertEquals(false,cw.withdraw(new BigDecimal(-1)));
		
		//ensure amounts were withdrawn correctly
		//this is the way to corrects assert equals for a BigDecimal according to documentation - to prevent scale inequality issues
		assertEquals(0, new BigDecimal(1499.50).compareTo(cw.getAccountBalance()));
		
	}
	@Test
	public void testDeposit() {		

		cd.deposit(new BigDecimal(500.00));
		//ensure amounts were withdrawn correctly
		//this is the way to corrects assert equals for a BigDecimal according to documentation - to prevent scale inequality issues
		assertEquals(false,cd.deposit(new BigDecimal(-1)));
		assertEquals(0, new BigDecimal(2500).compareTo(cd.getAccountBalance()));
	}
	
	

}

	

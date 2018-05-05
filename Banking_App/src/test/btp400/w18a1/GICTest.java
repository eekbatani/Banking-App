/**
     * @author Ehsan Ekbatani
     */
package test.btp400.w18a1;

import static org.junit.Assert.*;
import org.finance.accounts.GIC;
import org.junit.Test;
import java.math.BigDecimal;

public class GICTest {
		
		private static GIC g1 = new GIC("Ehsan Ekbatani", "A1234", "1500.00", 2, "3.6");
		private static GIC g1Copy = new GIC("Ehsan Ekbatani", "A1234", "1500.00", 2, "3.6");
		private static GIC g2 = new GIC();
		private static GIC gNeg = new GIC("Ehsan Ekbatani", "B3545", "-1", 4, "3.6");
		private static GIC gw = new GIC("Ehsan Ekbatani", "A1234", "2000.00", 3,  "5.5");
		private static GIC gd = new GIC("Ehsan Ekbatani", "A1234", "2000.00", 2, "2.1");
		
		@Test
		public void testGIC2() {
			assertEquals(g2.getFullName(), "");
			assertEquals(g2.getAccountNumber(), "");
			assertEquals(0, g2.getAccountBalance().compareTo(new BigDecimal(0.00)));
			
		}
		
		@Test
		public void GICNegativeBalance() {
			assertEquals(0, gNeg.getAccountBalance().compareTo(BigDecimal.ZERO));
			
		}

		@SuppressWarnings("deprecation")
		@Test
		public void GICgetBalanceAtMaturity() {
			assertEquals(new BigDecimal(1609.94).setScale(2, BigDecimal.ROUND_HALF_UP), g1.getBalanceAtMaturity());
			assertEquals(new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP), gNeg.getBalanceAtMaturity());
		}
		

		@Test
		public void testEqualsObject() {
			assertEquals(g1, g1Copy);
			assertEquals(false, g1.equals(g2));
		}
		
		@Test
		public void testGetters() {
			assertEquals(g1, g1Copy);
			assertEquals(g1.getFullName(), g1Copy.getFullName());
			assertEquals(g1.getAccountNumber(), g1Copy.getAccountNumber());
			assertEquals(0,g1.getAccountBalance().compareTo(g1Copy.getAccountBalance()));
		}
		
		

		@Test
		public void testToString() {
			
			String s = "number: A1234, name: Ehsan Ekbatani\n" + 
					"starting Balance: $1500.00, current Balance: $1500.00\n" + 
					"type: GIC \n" + 
					"annual Interest Rate: 3.6%\n" + 
					"period of Investment: 2\n" + 
					"new Balance at Maturirty: 1609.94\n";
						
			assertEquals(s, g1.toString());
		}
		
		@Test
		public void testWithdraw() {		

			assertFalse(gw.withdraw(new BigDecimal(500)));
			
		}
		@Test
		public void testDeposit() {		
			assertFalse(gd.deposit(new BigDecimal(500)));
		}
	}




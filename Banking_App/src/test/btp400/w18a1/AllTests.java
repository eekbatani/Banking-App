/**
     * @author Ehsan Ekbatani
     */
package test.btp400.w18a1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BankTest.class, ChequingTest.class, GICTest.class, SavingsTest.class })
public class AllTests {

}

package cscie160.project;
import org.junit.runners.Suite;
import org.junit.runner.RunWith;

/**
 * Test suit for complete project package
 * @author Kalpan
 * @version 1.0
 */
	@RunWith(Suite.class)
	@Suite.SuiteClasses({ATMImplTest.class, ClientTest.class})
	public class TestsSuit{}

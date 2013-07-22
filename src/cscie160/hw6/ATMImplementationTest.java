package cscie160.hw6;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * This tests are to validate proper behavior of the ATMImplementation.
 * 
 * @author	Kalpan
 * @version	1.0
 */
public class ATMImplementationTest
{
	private ATMImplementation atmImply;

	/**
	 * Initialize the test by creating a setup.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		atmImply = new ATMImplementation();
	}

	/**
	 * Testing deposit and get balance method.
	 * @throws ATMException
	 */
	@Test
	public void testDeposit() throws ATMException
	{
		atmImply.deposit(275);
		assertEquals("deposited $275", 275, atmImply.getBalance(), 0);
		atmImply.deposit(225);
		assertEquals("deposited another $225", 500, atmImply.getBalance(), 0);
	}

	/**
	 * Testing withdraw and get balance method.
	 * @throws ATMException
	 */
	@Test
	public void testWithdraw() throws ATMException
	{
		atmImply.deposit(350);
		assertEquals("$350 deposited", 350, atmImply.getBalance(), 0);
		
		atmImply.withdraw(250);
		assertEquals("1st withdraw of $250", 100, atmImply.getBalance(), 0);
		
		atmImply.withdraw(50);
		assertEquals("another $225", 50, atmImply.getBalance(), 0);
	}

}

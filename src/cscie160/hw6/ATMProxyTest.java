package cscie160.hw6;

import static org.junit.Assert.assertEquals;

import org.junit.*;


/**
 * This test case is to validate the ATMProxy.  
 * The server should be up an running on localhost, port 7777.
 * 
 * @author	Kalpan
 * @version	1.0
 */
public class ATMProxyTest
{
	private static ATMProxy	Proxy;
	
	/**
	 * Initialize the test by creating a setup.
	 * Instantiate an instance to test.
	 * 
	 * @throws	Exception	
	 * 	 */
	@BeforeClass
	public static void setUp() throws Exception
	{
		Proxy = new ATMProxy("localhost", 7777);
		
	}
	
	/**
	 * testing deposit method
	 * 
	 * @throws ATMException
	 */
	@Test
	public void testDeposit() throws ATMException
	{
		Proxy.deposit(1200);
		assertEquals("Deposited $1200", 1200, Proxy.getBalance(), 0);
	}

	/**
	 * testing withdraw method
	 * 
	 * @throws ATMException
	 */
	@Test
	public void testWithdraw() throws ATMException
	{
		Proxy.withdraw(2200);
		assertEquals("Withdrawal of $2200", -1000, Proxy.getBalance(), 0);
	}

	/**
	 * testing the get balance method
	 * 
	 * @throws ATMException
	 */
	@Test
	public void testGetBalance() throws ATMException
	{
				
		Proxy.deposit(3500);
		assertEquals("Deposited $3500", 2500, Proxy.getBalance(), 0);
		
		Proxy.withdraw(501);
		assertEquals("withdrew $501", 1999, Proxy.getBalance(), 0);
	}

}

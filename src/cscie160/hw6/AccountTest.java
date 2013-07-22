package cscie160.hw6;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test Account class
 * 
 * @version 1.0
 * @author KALPAN
 *
 */
public class AccountTest
{
	private Account account;

/**
 * Creates a test set up with an account of 0 balance.
 * @throws Exception
 */
	@Before
	public void setUp() throws Exception
	{
		account = new Account();
	}

/**
 * Test getBalance method.
 */
	@Test
	public void testGetBalance()
	{
		assertEquals("Account created correctly", 0, account.getBalance(), 0);
		
	}

/**
 * Test setBalance method
 */
	@Test
	public void testSetBalance()
	{
		account.setBalance(10000);
		assertEquals("Correct balance updated", 10000, account.getBalance(), 0);
	}

}

package cscie160.hw5;

import java.rmi.RemoteException;

import org.junit.After;
import org.junit.Test;
import junit.framework.TestCase;

/**
* Main test class implementation
* @author Kalpan
* @version 1.0
*/

public abstract class ATMTestMain extends TestCase
{
    protected ATM atm;
    public abstract void setUp() throws Exception;
    
    @After
    public void tearDown()
    {
        atm = null;
    }

    @Test
    public void testDeposit() throws RemoteException
    {
        assertNotNull("atm is null", atm);

        float balance = atm.getBalance(1);
        float amount = 100;
        float expected = balance+amount; 
        atm.deposit(1, amount);
        float newBalance = atm.getBalance(1);
        assertEquals("[Error]: expected %f, but got %f", expected, newBalance);
    }

    @Test
    public void testWithdraw() throws RemoteException
    {
        assertNotNull("atm is null", atm);
        atm.deposit(1, 1000);
        float balance = atm.getBalance(1);
        float amount = 100;
        float expected = balance-amount; 
        atm.withdraw(1, amount);
        float newBalance = atm.getBalance(1);
        assertEquals(expected, newBalance, 0);
    }

    @Test 
    public void testGetBalance() throws RemoteException
    {
        assertNotNull("atm is null", atm);
        float expected = 0;
        float balance = atm.getBalance(1);
        assertEquals(expected, balance);
    }
}
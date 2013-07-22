package cscie160.project;

import static org.junit.Assert.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import org.junit.*;
/**
 * Base class for all unit tests
 * @author Kalpan
 * @version 1.0
 */
public abstract class ATMTestBase extends UnicastRemoteObject implements ATMListener
{
	private static final long serialVersionUID = 1L;
	protected ATM atm;
	
    public ATMTestBase() throws Exception
    {
        super();
    }

    public abstract void startATM() throws Exception;
    
    @Before
    public void setUp() throws Exception
    {
        startATM();
        atm.startSession(this);
        atm.verification(1, "1234");
        atm.verification(2, "2345");
        atm.verification(3, "3456");
    }
    
    @After
    public void tearDown() throws Exception
    {
        atm.endSession();
        atm = null;
    }
    
    @Test 
    public void testGetBalance() throws RemoteException
    {
        assertNotNull("atm is null", atm);
        float expected = 0;
        float balance = atm.getBalance(1);
        assertEquals(expected, balance,0);
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
        assertEquals("[Error]: expected %f, got %f", expected, newBalance, 0);
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
 
    @Override
    public void sendTransactionNotification(TransactionNotification notif) throws RemoteException
    {
        System.out.println(notif.getMsg());
    }

    @Override
    public void activeOrNot() throws RemoteException {}
}

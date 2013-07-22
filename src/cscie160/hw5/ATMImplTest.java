package cscie160.hw5;

import java.rmi.RemoteException;

import org.junit.Before;

/**
 * Test set up class
 * @author Kalpan
 * @version 1.0
 */
public class ATMImplTest extends ATMTestMain
{
    @Before
    public void setUp() throws RemoteException
    {
        atm = new ATMImpl();
    }
}
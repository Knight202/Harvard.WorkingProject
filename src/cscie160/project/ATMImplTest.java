package cscie160.project;

import java.rmi.RemoteException;

/**
 * This tests are to validate proper behavior of the ATMImplementation.
 * 
 * @author	Kalpan
 * @version	1.0
 */
public class ATMImplTest extends ATMTestBase
{
	private static final long serialVersionUID = 1L;
/**
 * Default constructor for ATMImplTest
 * @throws Exception
 */
	public ATMImplTest() throws Exception
    {
        super();
    }

    /**
     * This test method is to ensure proper allocation of ATM object before testing.
     */
    public void startATM() throws RemoteException
    {
        atm = new ATMImpl(BankImpl.getInstance());
    }
}

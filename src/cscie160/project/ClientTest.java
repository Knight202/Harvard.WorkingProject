package cscie160.project;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Class for testing remote object ATMImpl invoked by RMI
 * @author Kalpan
 * @version 1.0
 */
public class ClientTest extends ATMTestBase
{
	private static final long serialVersionUID = 1L;

	public ClientTest() throws Exception
    {
        super();
    }

    /**
     * @throws NotBoundException 
     * @throws RemoteException 
     * @throws MalformedURLException 
     */

    public void startATM() throws MalformedURLException, RemoteException, NotBoundException
    {
        ATMFactory factory = (ATMFactory) Naming.lookup("//localhost/atmfactory");
        atm = factory.getATM();     
    }

	@Override
	public void activeOrNot() throws RemoteException {
	}
}




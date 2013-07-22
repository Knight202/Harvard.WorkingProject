package cscie160.hw5;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementation of RMI factory for obtaining remote ATM object.
 * @author Kalpan
 * @version 1.0
 *
 */
public class ATMFactoryImpl extends UnicastRemoteObject implements ATMFactory 
{
	private static final long serialVersionUID = 1L;
	/**
     * Constructor for handling the exception for remote object
     * @throws RemoteException
     */
    public ATMFactoryImpl() throws RemoteException
    {
        super();
    }
    /**
     * This method will provide ATM object
     * @return atm
     * @throws RemoteException
     */
    @Override
    public ATM getATM() throws RemoteException
    {
        return new ATMImpl();
    }
}



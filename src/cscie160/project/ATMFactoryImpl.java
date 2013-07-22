package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * ATM Factory implementation
 * @author Kalpan
 * @version 1.0
 */
public class ATMFactoryImpl extends UnicastRemoteObject implements ATMFactory 
{
	private static final long serialVersionUID = 1L;
	/**Remote Bank object */
    private BankIntf bankObject = null;
    /**
     * Constructor for giving remote object for bank.
     * @param bank remote object
     * @throws RemoteException
     */
    public ATMFactoryImpl(BankIntf bank) throws RemoteException
    {
        this.bankObject = bank;
    }

    /**
     * This method will provide ATM instance
     * @return ATM object
     * @throws RemoteException
     */
    @Override
    public ATM getATM() throws RemoteException
    {
        return new ATMImpl(bankObject);
    }

}

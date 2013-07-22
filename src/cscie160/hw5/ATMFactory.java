package cscie160.hw5;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for RMI factory for obtaining all the remote objects.
 * 
 * @author Kalpan
 * @version 1.0
 *
 */
public interface ATMFactory extends Remote
{
    /**
     * This method will return remote object ATM for operations.
     * @return ATM
     * @throws RemoteException
     */
    public ATM getATM() throws RemoteException;
}
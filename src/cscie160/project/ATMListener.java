package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface for atm listeners that to receive notifications from ATM
 * @author Kalpan
 * @version 1.0
 */
public interface ATMListener extends Remote
{
    /**
     * This method is to verify the RMI connection is active or not.
	 *
     * @throws RemoteException
     */
    public void activeOrNot() throws RemoteException;

    /**
     * This method will send notification to the atm listner.
     * 
     * @param notify
     * @throws RemoteException
     */
    public void sendTransactionNotification(TransactionNotification notify) throws RemoteException;
}
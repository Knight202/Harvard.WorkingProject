package cscie160.project;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * Remote interface for Account object.
 * 
 * @author Kalpan
 * @version 1.0
 */
 
public interface AccountIntf extends Remote
{
    /**
     * This method will provide balance of the account.
     * 
     * @return Balance
     * @throws RemoteException
     */
    public float getBalance() throws RemoteException;

    /**
     * This method will set balance of the account to the given 
     * 
     * @param balance
     * @throws RemoteException
     */
    public void setBalance(float balance) throws RemoteException;
    /**
     * This method will provide customer's account no.
     * 
     * @return Account's numeric ID
     * @throws RemoteException
     */
    public int getAccountNumber() throws RemoteException;
}
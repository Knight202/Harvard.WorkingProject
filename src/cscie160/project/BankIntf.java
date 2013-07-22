package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface defining Bank remote object.
 * @author Kalpan
 * @version 1.0
 */
public interface BankIntf extends Remote
{
    /**
     * This method will provide a remote object account for the given account no.
     * @param accountNumber
     * @return Account remote object
     * @throws RemoteException
     */
    public AccountIntf getAccount(int accountNumber) throws RemoteException;

    /**
	 * This method will return security object to verify permissions and authorizations of perticular account.
     * @return Security object
     * @throws RemoteException
     */
    public SecurityIntf getSecurityDetails() throws RemoteException;
}
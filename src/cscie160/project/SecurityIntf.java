package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for Security object.
 * 
 * @author Kalpan
 * @version 1.0
 */
public interface SecurityIntf extends Remote
{
    /**
     * This method will verify the access of given account for executing certain Command
     * 
     * @param accountNumber
     * @param command
     * @return true / False based on command access
     * @throws RemoteException
     */
    public boolean commandAccessVerification(int accountNumber, Commands command) throws RemoteException;

    /**
     * This method will verify the PIN no with the account no.
     * @param accountNumber
     * @param PIN
     * @return true / false
     * @throws RemoteException
     */
    public boolean verifyPinNumber(int accountNumber, String PIN) throws RemoteException;
}
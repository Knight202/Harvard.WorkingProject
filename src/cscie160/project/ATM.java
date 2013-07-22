package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for ATM object.
 * 
 * @author Kalpan
 * @version 1.0
 */
public interface ATM extends Remote
{
    public static final int TIMEOUT_WARNING = 4000;
    public static final int TIMEOUT_EXIT = 1000;

    /**
     * This method will verify account with given PIN no.
     * @param accountNumber
     * @param PIN
     * @throws RemoteException
     */
    public void verification(int accountNumber, String PIN) throws RemoteException;

    /**
     * This method will provide balance of the account for the given account no.
     * @param accountNumber
     * @return account balance
	 * @throws RemoteException
     */
    public Float getBalance(int accountNumber) throws RemoteException;

    /**
     * This method will deposit the given amount to the account for given account no.
     * @param amount
     * @throws RemoteException
     * */
    public void deposit(int accountNumber, float amount) throws RemoteException;

    /**
     * This method will withdraw provided amount from given account no.
     * @param amount
     * @throws RemoteException 
     * */
    public void withdraw(int accountNumber, float amount) throws RemoteException;

    /**
     * This method will transfer the mentioned amount bet given two account numbers
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @throws RemoteException
     */
    public void transfer(int fromAccount, int toAccount, float amount) throws RemoteException;

    /**
     * This method starts client session
     * @param atmListener
     * @throws RemoteException
     */
    public void startSession(ATMListener atmListener) throws RemoteException;

    /**
     * This method will end current session.
     * @throws RemoteException
     */
    public void endSession() throws RemoteException;
}
package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * This class implements remote interface for account.
 * 
 * @author Kalpan
 * @version 1.0
 */
public class AccountImpl extends UnicastRemoteObject implements AccountIntf
{
	private static final long serialVersionUID = 1L;
	/** balance.*/
    private float balance = 0;
    /** Account number*/
    private final int accountNumber;
    /**
     * Constructor to create an account with given accountNumber
     * @param accountNumber
     */
    public AccountImpl(int accountNumber) throws RemoteException
    {
        this.accountNumber = accountNumber;
    }
//--------------------------------------------------Getter and Setter-------------------------------------------//
    @Override
    public float getBalance() throws RemoteException
    {
        return balance;
    }
    @Override
    public void setBalance(float balance) throws RemoteException
    {
        this.balance = balance;
    }
    @Override
    public int getAccountNumber() throws RemoteException
    {
        return accountNumber;
    }
}

package cscie160.hw5;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * RMI interface for ATM remote object.
 * 
 * @author Kalpan
 * @version 1.0
 * 
 */
public interface ATM extends Remote
{
    /**
     * Get balance of the account for the given customer's account number
     * 
     * @param accountNumber Customer's account No
     * @return Balance
     * @throws RemoteException
     */
    public Float getBalance(int accountNumber) throws RemoteException;
	/**
     * Deposit founds to the account for the given customer's account number
     * 
     * @param accountNumber Customer's account No
     * @param amount funds to deposit
     * @throws RemoteException
     */
	public void deposit(int accountNumber, float amount) throws RemoteException;
	/**
     * Withdraw founds from the given customer's account number
     * 
     * @param accountNumber Customer's account No
     * @param amount funds to withdraw
     * @throws RemoteException
     */
	public void withdraw(int accountNumber, float amount) throws RemoteException;
}


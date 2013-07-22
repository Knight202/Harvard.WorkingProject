package cscie160.hw5;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


/**
 * Implementation of the ATM
 * 
 * @author Kalpan
 * @version 1.0
 * 
 */
public class ATMImpl extends UnicastRemoteObject implements ATM
{
	private static final long serialVersionUID = 1L;
	private HashMap <Integer, Account> accounts;

    /**
     * Instantiates a new object
     * 
     * @throws RemoteException
     */
    public ATMImpl() throws RemoteException
    {
    	accounts = new HashMap<Integer, Account>();
        float[] balances = {0, 100, 500};
        for (int i = 0;i < 3;i++)
        {
            Account account = new Account(i);
            account.setBalance(balances[i]);
            accounts.put(i+1, account);
        }
    }
    
    /**
     * Get account.
     * @param accountNumber Customer's account No.
     * @return Account
     * @throws ATMException if Invalid account No.  
     */
    private Account getAccount(int accountNumber)  throws ATMException 
    {
        Account account = accounts.get(accountNumber);
        if (account == null)
            throw new ATMException (String.format("[Error]: Invalid Account No: %d", accountNumber));
        return account;
    }

    /**
     * Deposit a sum.
     * @param amount Funds to deposit
     * @param accountNumber Customer's account No.
     * @throws ATMException
     * 
     * */
    @Override
    public void deposit(int accountNumber, float amount) throws ATMException
    {
        if (amount <= 0)
            throw new ATMException("[Error]: Amount must be positive");
        
        Account account = getAccount(accountNumber); 
        if (account == null)
            throw new ATMException (String.format("Invalid Account No: %d", accountNumber));
        float balance = account.getBalance();
        account.setBalance(balance + amount);

    }

    /**
     * Withdraw a sum.
     * @param amount Funds to withdraw
     * @param accountNumber Customer account No
     * @throws ATMException
     * 
     * */
    @Override
    public void withdraw(int accountNumber, float amount) throws ATMException
    {
        if (amount <= 0)
            throw new ATMException("[Error]: Amount must be positive");
        
        Account account = getAccount(accountNumber); 
        if (account == null)
            throw new ATMException (String.format("[Error]: Invalid Account No: %d", accountNumber));

        float newBalance = account.getBalance() - amount; 
        if (newBalance < 0)
            throw new ATMException("[Error]: Not enough funds");
        
        account.setBalance(newBalance);
    }

    /**
     * Returns balance.
     * 
     * @param accountNumber Customer account No
     * @return Balance
     * @throws ATMException
     * */
    @Override
    public Float getBalance(int accountNumber) throws ATMException
    {
        Account account = getAccount(accountNumber); 
        return account.getBalance();
    }

}




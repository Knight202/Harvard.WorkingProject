package cscie160.project;
import java.util.HashMap;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementation of remote interface Bank to obtain remote object.
 * @author Kalpan
 * @version 1.0
 */
public class BankImpl extends UnicastRemoteObject implements BankIntf
{

	private static final long serialVersionUID = 1L;
	/** Customer accounts */
    private HashMap <Integer, AccountIntf> bankAccounts = null;
    /** Unique instance */
    private static BankImpl bankInstance = null; 
    
    /**
     * Default constructor to set initial access to bank accounts.
     * @throws RemoteException
     */
    public BankImpl() throws RemoteException
    {
    	bankAccounts = new HashMap<Integer, AccountIntf>();
        float[] balances = {0, 100, 500};		//Sets account's initial balance
        for (int i = 0; i < 3; i++)
        {
            AccountImpl account = new AccountImpl(i+1);
            account.setBalance(balances[i]);
            bankAccounts.put(i+1, account);		//Add account to the hashmap
        }
        SecurityImpl security = SecurityImpl.getInstance();	//set PIN and access info for each account
        security.accessProvider(1, "1234", true, true, true);
        security.accessProvider(2, "2345", true, false, true);
        security.accessProvider(3, "3456", false, true, true);
    }
    
    /**
     * @return Instance of bank class
     * @throws RemoteException
     */
    public static BankImpl getInstance() throws RemoteException
    {
        if (bankInstance == null) bankInstance = new BankImpl();
        return bankInstance;
    }
    /**
     * This method will provide a remote object account for the given account no.
     * @param accountNumber
     * @return Account remote object
     * @throws RemoteException
     */
    @Override
    public AccountIntf getAccount(int accountNumber) throws RemoteException
    {
    	AccountIntf account = bankAccounts.get(accountNumber);
        if (account == null)
        throw new ATMException (String.format("[Error]: Given account no cannot be found %d", accountNumber));
        return account;
    }

    /**
	 * This method will return security object to verify permissions and authorizations of perticular account.
     * @return Security object
     * @throws RemoteException
     */
    @Override
    public SecurityIntf getSecurityDetails() throws RemoteException
    {
        return SecurityImpl.getInstance();
    }
}

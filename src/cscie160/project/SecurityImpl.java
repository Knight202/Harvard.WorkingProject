package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
/**
 * This Class implements backend remote method for security verification.
 * 
 * @author Kalpan
 * @version 1.0
 * 
 */
public class SecurityImpl extends UnicastRemoteObject implements SecurityIntf
{

	private static final long serialVersionUID = 1L;
	/** HashMap for all accountInfo records */
    private HashMap<Integer, AccountInfo> accountInfo = null;
    /** Unique instance for Security*/
    private static SecurityImpl securityImpl = null;

    /**
     * Default constructor
     * @throws RemoteException
     */
    public SecurityImpl() throws RemoteException
    {
    	accountInfo = new HashMap<Integer, AccountInfo>();
    }

    /**
     * This method will provide instance of security class
     * @return securityInstance
     * @throws RemoteException
     */
    public static SecurityImpl getInstance() throws RemoteException
    {
        if (securityImpl == null) securityImpl = new SecurityImpl();
        return securityImpl;
    }
    @Override
    public boolean commandAccessVerification(int accountNumber, Commands command) throws ATMException
    {
    	AccountInfo accountRecord = getRecord(accountNumber);
        return accountRecord.getAccess(command);
    }
    @Override
    public boolean verifyPinNumber(int accountNumber, String PIN) throws ATMException
    {
    	AccountInfo accountRecord = getRecord(accountNumber);
        return accountRecord.getPIN().equals(PIN);
    }

    /**
     * This method will set PIN no and command access info for given account no.
     * 
     * @param accountNumber
     * @param PIN
     * @param deposit
     * @param withdraw
     * @param balance
     */
    public void accessProvider(int accountNumber, String PIN, boolean deposit, boolean withdraw,boolean balance)
    {
    	AccountInfo accountRecord = accountInfo.get(accountNumber);
        if (accountRecord == null)
        {
        	accountRecord = new AccountInfo();
            accountInfo.put(accountNumber, accountRecord);
        }
        accountRecord.setAccess(Commands.DEPOSIT, deposit);
        accountRecord.setAccess(Commands.WITHDRAWAL, withdraw);
        accountRecord.setAccess(Commands.BALANCE, balance);
        
        accountRecord.setPIN(PIN);   
    }

    /**
     * This method will provide account's security record info.
     * @param accountNumber
     * @return Security record for the given account
     * @throws ATMException
     */
    public AccountInfo getRecord(int accountNumber) throws ATMException
    {
    	AccountInfo record = accountInfo.get(accountNumber);
        if (record == null)
        throw new ATMException(String.format("[Error]: Permissions not found for accoumt: %06d",accountNumber));
        return record;
    }
}

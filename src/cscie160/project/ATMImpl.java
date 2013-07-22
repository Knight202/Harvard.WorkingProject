package cscie160.project;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.HashSet;

/**
 * ATM implementation class handles main business logic
 * 
 * @author Kalpan
 * @version 1.0
 */
public class ATMImpl extends UnicastRemoteObject implements ATM
{
	private static final long serialVersionUID = 1L;
	/** Bank interface remote object */
    private BankIntf bankIntf = null;
    /** Bank's security interface remote object */
    private SecurityIntf securityIntf = null;
    /** Available cash in the ATM */
    private float atmCash;
    /** List of authenticated accounts */
    private HashSet<Integer> accounts;
    /** Subscribed atm client listeners */
    private HashSet<ATMListener> atmListeners;
    /** session state flag */
    private boolean sessionState;
    /** date stamp of last time client processed any request */
    private Date previousActivityStamp;
    /** Time out warning Flag */
    private boolean towState;
    /** Next available numeric ID for the class' instance */
    private static int uniqueID = 0;

    /**
     * Constructor for creating collection of objects with 3 accounts and their initial balance.
     * @param bankIntf
     * @throws RemoteException
     */
    public ATMImpl(BankIntf bankIntf) throws RemoteException
    {
        this.bankIntf = bankIntf;
        securityIntf = bankIntf.getSecurityDetails();
        accounts = new HashSet<Integer>();
        atmListeners = new HashSet<ATMListener>();
        atmCash = 500;
        sessionState = false;
        previousActivityStamp = new Date();
        towState = false;
        uniqueID++;
    }
    
    public String toString()
    {
        return "ATM" + uniqueID;
    }

    /**
     * Get Account object
     * @param accountNumber
     * @return AccountIntf for given accountNumber
     * @throws RemoteException 
     */
    private AccountIntf getAccount(int accountNumber)  throws RemoteException 
    {
    	AccountIntf accountIntf = bankIntf.getAccount(accountNumber);
        if (accountIntf == null)
        throw new ATMException (String.format("[Error]: Account is not found: %d", accountNumber));
        return accountIntf;
    }
   
    /**
     * This method will provide balance of the account for the given account no.
     * @param accountNumber
     * @return account balance
	 * @throws RemoteException
     */
    @Override
    public Float getBalance(int accountNumber) throws RemoteException
    {
    	notifyATMListeners("[Info]: Checking balance for account No: " + accountNumber);
        sessionStateFlag();
        verifyCommandAccess (accountNumber, Commands.BALANCE);
        AccountIntf accountIntf = getAccount(accountNumber); 
        previousActivityStamp = new Date();
        
        return accountIntf.getBalance();
    }

    /**
     * This method will deposit the given amount to the account for given account no.
     * @param amount
     * @throws RemoteException
     * */
    @Override
    public void deposit(int accountNumber, float amount) throws RemoteException
    {
    	notifyATMListeners("[Info]: depositing " + amount + " to account " + accountNumber);
        sessionStateFlag();
        verifyCommandAccess (accountNumber, Commands.DEPOSIT);

        if (amount <= 0)
        throw new ATMException("[Error]: Amount should be positive");
        
        AccountIntf accountIntf = getAccount(accountNumber); 
        if (accountIntf == null)
        throw new ATMException (String.format("[Error]: given account no is not found %d", accountNumber));

        float balance = accountIntf.getBalance();
        accountIntf.setBalance(balance + amount);
        previousActivityStamp = new Date();

    }

    /**
     * This method will withdraw provided amount from given account no.
     * @param amount
     * @throws RemoteException 
     * */
    @Override
    public void withdraw(int accountNumber, float amount) throws RemoteException
    {
    	notifyATMListeners("[Info]: withdrawing " + amount + " from account " + accountNumber);
        sessionStateFlag();
        verifyCommandAccess (accountNumber, Commands.WITHDRAWAL);
        if (amount <= 0)
        throw new ATMException("[Error]: Amount should be positive");
        
        if (atmCash - amount < 0)
        throw new ATMException("ATM is out of Cash");
        
        AccountIntf account = getAccount(accountNumber); 
        if (account == null)
        throw new ATMException (String.format("[Error]: given account no is not found %d", accountNumber));

        float newBalance = account.getBalance() - amount; 
        if (newBalance < 0)
        throw new ATMException("Not enough amount in account");
        
        account.setBalance(newBalance);
        atmCash -= amount;
        previousActivityStamp = new Date();
    }

    /**
     * This method will transfer the mentioned amount bet given two account numbers
     * @param fromAccount
     * @param toAccount
     * @param amount
     * @throws RemoteException
     */
    @Override
    public void transfer(int fromAccount, int toAccount,float amount) throws RemoteException
    {
    	notifyATMListeners("[Info]: " + amount + " will be transferred from account " + fromAccount + " to account " + toAccount);
        sessionStateFlag();
        verifyCommandAccess (toAccount, Commands.DEPOSIT);
        verifyCommandAccess (fromAccount, Commands.WITHDRAWAL);
       
        if (amount <= 0)
        throw new ATMException("[Error]: Amount should be positive");
        
        AccountIntf toAccountIntf = getAccount(toAccount);
        AccountIntf fromAccountIntf = getAccount(fromAccount); 
        synchronized (fromAccountIntf)
        {
            synchronized (toAccountIntf)
            {
                float balanceOfFromAccount = fromAccountIntf.getBalance();
                float balanceOfToAccount = toAccountIntf.getBalance();

                if ( balanceOfFromAccount < amount )
                throw new ATMException("Not enough amount in account");
                
                fromAccountIntf.setBalance(balanceOfFromAccount - amount);
                toAccountIntf.setBalance(balanceOfToAccount + amount);
            }
        }
    }

    /**
     * This method will verify account with given PIN no.
     * @param accountNumber
     * @param PIN
     * @throws RemoteException
     */
    public void verification(int accountNumber, String PIN) throws RemoteException
    {
    	sessionStateFlag();
        if (!securityIntf.verifyPinNumber(accountNumber, PIN)){
            String msg = String.format("[Error]: Authentication failed for account %06d", accountNumber);
            notifyATMListeners (msg);
            throw new ATMException(msg);
        }
        accounts.add(accountNumber);
        notifyATMListeners (String.format("[Info]: Account is authenticated and added to the list %06d", accountNumber));
        previousActivityStamp = new Date();
    }

    @Override
    /**
     * This method starts client session
     * @param atmListener
     * @throws RemoteException
     */
    public void startSession(ATMListener atmListener) throws RemoteException
    {
        if (sessionState)
            throw new ATMException("Session is running");
        sessionState = true;
        previousActivityStamp = new Date();
        towState = false;
        addATMListener(atmListener);
     
        notifyATMListeners("ATM session started");
        Thread monitorTimeOut = new Thread(new Runnable(){
            @Override
            public void run(){
                while (ATMImpl.this.sessionState){
                    try{
                        checkSessionTimeout();
                        Thread.sleep(500);
                    } catch (Exception e){
                    	System.out.println(e);
                    }
                }
            }
        });
        monitorTimeOut.start();
    }
    
    @Override

    /**
     * This method will end current session.
     * @throws RemoteException
     */
    public void endSession()
    {
    	notifyATMListeners ("ATM session ended");
        accounts = new HashSet<Integer>();
        atmListeners = new HashSet<ATMListener>();
        sessionState = false;
        towState = false;
    }

    /**
     * Subscribe an ATM listener
     * @param atmListener
     * @throws RemoteException
     */
    public void addATMListener(ATMListener atmListener) throws RemoteException
    {
        if (atmListener != null) atmListeners.add(atmListener);
    }
  
    /**
     * Notification sender to atm listeners
     * @param msg
     */
    public void notifyATMListeners(String msg)
    {
            TransactionNotification notifMsg = new TransactionNotification (this.toString(), msg);
            for (ATMListener listener : atmListeners)
            {
                try
                {
                listener.sendTransactionNotification(notifMsg);
                } catch (RemoteException e)
                {
                    e.printStackTrace();
                }
            }
    }

    /**
     * Ping Method
     * @throws RemoteException
     */
    public void pingMethod() throws RemoteException
    {
        for (ATMListener listener : atmListeners)
        {
            listener.activeOrNot();
        }
    }

    /**
     * This method will check flag of sessionState
     * @throws RemoteException
     */
    public void sessionStateFlag() throws RemoteException
    {
        if (!sessionState)throw new RemoteException("Session is not active");
    }

    /**
     * This method will verify whether the account has execution permission for given command or not. 
     * @param accountNumber
     * @param name
     * @throws RemoteException
     */
    public void verifyCommandAccess (int accountNumber, Commands name) throws RemoteException
    {
        if (!accounts.contains(accountNumber))
            throw new ATMException(String.format("[Error]: Not authenticated account %06d", accountNumber));
        if (!securityIntf.commandAccessVerification(accountNumber, name))
            throw new ATMException(String.format("[]: Requested command execution is not permitted for account %06d: %s", accountNumber, name));
    }
  
    /**
     * Thismethod checks whether method is active or not.
     * @throws RemoteException
     */
    public void checkSessionTimeout()
    {
        try
        {
        	pingMethod();
        } catch (RemoteException e)
        {
        	notifyATMListeners("[Warning]: Session will be ended due to connection loss");
            endSession();
        }
        Date now = new Date();
        if (now.getTime() - previousActivityStamp.getTime() > TIMEOUT_WARNING)
        {
            if (towState)
            {
                if (now.getTime() - previousActivityStamp.getTime() > TIMEOUT_WARNING + TIMEOUT_EXIT)
                {
                	notifyATMListeners ("[Warning]: Session will be ended due to inactivity");
                    endSession();
                }
            }
            else
            {
            	notifyATMListeners ("[Warning]: Session will be ended due to inactivity");
                towState = true;
            }
        }
        else
            towState = false;
    }
}
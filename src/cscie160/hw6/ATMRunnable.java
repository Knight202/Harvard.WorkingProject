package cscie160.hw6;

import java.io.PrintStream;
import java.util.StringTokenizer;

/**
 * This class is responsible for executing client requests. 
 * @author Kalpan
 * @version 1.0
 * 
 */
public class ATMRunnable implements Runnable
{
	 /** for client request */
    private final String cmndLine;
   
    /** Instance of ATM object */
    private final ATM atm;
    
    /** To print results */
    private final PrintStream printStream;
    /** Primary thread invoking runnable */
    private final ThreadReader masterThread;
    /** Request ID */
    private final int reqID;
    /** Maximum ID */
    private static int maxID = 0; 
    
    /**
     * Default constructor.
     * @param atm
     * @param cmndLine
     * @param printStream
     */
    public ATMRunnable(ATM atm, String cmndLine, PrintStream printStream)
    {
        this.masterThread = (ThreadReader)Thread.currentThread();
        this.printStream = printStream;
        
        this.cmndLine = cmndLine;
        this.atm = atm;
        reqID = maxID++;
    }

    /**
     * Passes request string to ExecuteCommand method and prints the output to the client
     * 
     */
    @Override
    public void run()
    {
        try 
        {
            Float amount = parseCommand(cmndLine);
            if (amount != null) 
            { 
                printStream.println(amount);
            }
        } 
        catch (ATMException ex) 
        {
            System.out.println("[Error]: " + ex);
        }
    }
    /**
     * Creates a loggable representation of the current object's ID and request string
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return String.format("request in  Thread %d(%s): ", reqID, masterThread); 
    }
    /**
     * Parses the request string to determine which operation (deposit, withdraw, balance) is required.
     * @param commandLine
     * @return balance amount
     * @throws ATMException
     */
    private Float parseCommand(String commandLine) throws ATMException 
    {
        StringTokenizer tokenizer = new StringTokenizer(commandLine);
        String commandParams[] = new String[tokenizer.countTokens()];
        
        int index = 0;
        while (tokenizer.hasMoreTokens()) 
        {
            commandParams[index++] = tokenizer.nextToken();
        }
        String commandString = commandParams[0];
        if (commandString.equalsIgnoreCase(Commands.BALANCE.toString())) //Get Balance
        {
            return atm.getBalance();
        }
        
        if (commandParams.length < 2) 
        {
            throw new ATMException("Amount is not provided for: " + commandString);
        }
        
        try
        {
            float money = Float.parseFloat(commandParams[1]);
            
            if (commandString.equalsIgnoreCase(Commands.WITHDRAW.toString())) //Withdraw Money
            {
                atm.withdraw(money);
            }
            
            else   if (commandString.equalsIgnoreCase(Commands.DEPOSIT.toString()))  //Deposit Money
            {
                atm.deposit(money);        
            }
            
            else 
            {
                throw new ATMException("Command cannot be identified: " + commandString);
            }
        } 
        catch (Exception e) 
        {
            throw new ATMException("[Error]: " + e);
        }
        return null;
    }
    
    /**
     * Signals masterThread to prevent processing further requests.
     * 
     */
    public void lockThreadForClient()
    {
        masterThread.lockThreadForClient();
    }

    /**
     * Signals masterThread that further requests can be processed.
     */
    public void unlockThreadForClient()
    {
        masterThread.unlockThreadForClient();
    }
    
    /**
     * Get the command being processed
     * @return cmndLine
     */
    public String getCmndLine()
    {
        return cmndLine;
    }
}
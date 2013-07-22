package cscie160.project;

import java.rmi.Naming;

/**
 * ATM Server implemnataition
 * @author Kalpan
 * @version 1.0
 */
public class ATMServer
{
    public static void main(String[] args)
    {
    	// Create and install a security manager    	
    	// System.setSecurityManager(new RMISecurityManager());
    	try
    	{
    		BankIntf bank = (BankIntf)Naming.lookup("//localhost/bank");
    		if (bank==null)
    		throw new Exception ("Bank Server does not exist in registry");
    		System.out.println("Bank Server exists in registry");
                
    		ATMFactoryImpl obj = new ATMFactoryImpl(bank);
    		Naming.rebind("//localhost/atmfactory", obj);
    		System.out.println("ATM Server bound in registry");
    	}
    	catch (Exception e)
    	{
    		System.out.println("[ATM Server Error]: " + e.getMessage());
    		e.printStackTrace();
    	}
    }
}

package cscie160.project;

import java.rmi.Naming;

/**
 * Bank Server implementation
 * @author Kalpan
 * @version 1.0
 */
public class BankServer
{
    public static void main(String[] args)
    {
        // Create and install a security manager
        // System.setSecurityManager(new RMISecurityManager());
        try
        {
            BankImpl obj = new BankImpl();
            Naming.rebind("//localhost/bank", obj);
            System.out.println("Bank Server bound in registry");
        }
        catch (Exception e)
        {
            System.out.println("[Bank Server Error]: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
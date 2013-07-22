package cscie160.hw5;

import java.rmi.Naming;

/**
 * Server implementation
 * @author Kalpan
 * @version 1.0
 */
public class Server
{
    public static void main(String[] args)
    {
        {
            // Create and install a security manager
            //  System.setSecurityManager(new RMISecurityManager());
            try
            {
                ATMFactoryImpl obj = new ATMFactoryImpl();
                Naming.rebind("//localhost/atmfactory", obj);
                System.out.println("ATMFactoryImpl bound in registry");
            } 
            catch (Exception e) 
            {
            	System.out.println("ATMFactoryImpl error: " + e.getMessage());
            	e.printStackTrace();
            }
        }
    }

}
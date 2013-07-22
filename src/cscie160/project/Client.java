package cscie160.project;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

/**
 * Client class for ATM model.
 * @author Kalpan
 * @version 1.0
 * 
 */
public class Client extends UnicastRemoteObject implements ATMListener
{
	private static final long serialVersionUID = 1L;

	/**
  	 * Default constructor for Client.
	 * @throws RemoteException
	 */
		public Client() throws RemoteException
	    {
	        super();
	    }

    public static void main(String[] args)
    {
        try
        {
            Client client = new Client();
            client.run();
        } catch (Throwable e)
        {
            e.printStackTrace();
        } finally
        {
            System.exit(0);
        }
    }

    /**
     * Initialize ATM and invoke test
     * 
     * @throws Exception
     */
    public void run() throws Exception
    {
        ATM atm = null;
        try
        {
            ATMFactory factory = (ATMFactory) Naming.lookup("//localhost/atmfactory");
            atm = factory.getATM();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        if (atm != null)
        {
            try
            {	//This piece is already doing account verification so no need to duplicate in testATM method.
                atm.startSession(this);
                atm.verification(1, "1234");
                atm.verification(2, "2345");
                atm.verification(3, "3456");
                
                testATM(atm);		//This will start client piece mentioned in Homwwork.

            } catch (Exception e){
              System.out.println("[Error]: "+ e);
            } finally
            {
                atm.endSession();
                atm = null;
            }
        }
    }
    @Override
    public void sendTransactionNotification(TransactionNotification notify) throws RemoteException
    {
        System.out.println(notify.getMsg());
    }
    @Override
    public void activeOrNot() throws RemoteException{ }

    /**
     * Client definition as provided in homework.
     * 
     * @param atm
     */
    public static void testATM(ATM atm)
    {
        if (atm != null)
        {
            printBalances(atm);
            performTestOne(atm);
            performTestTwo(atm);
            performTestThree(atm);
            performTestFour(atm);
            performTestFive(atm);
            performTestSix(atm);
            performTestSeven(atm);
            performTestEight(atm);
            performTestNine(atm);
            printBalances(atm);
        }
    }

    public static void printBalances(ATM atm)
    {
        try
        {
            System.out.println("Balance(0000001): " + atm.getBalance(0000001));
            System.out.println("Balance(0000002): " + atm.getBalance(0000002));
            System.out.println("Balance(0000003): " + atm.getBalance(0000003));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void performTestOne(ATM atm){
        try{
            atm.verification(1, "1234123");
            atm.getBalance(0000001);
        } catch (Exception e){
            System.out.println("Failed as expected: " + e);
        }
    }
    public static void performTestTwo(ATM atm){
        try{
        	atm.withdraw(0000002, 500);
        } catch (Exception e){
            System.out.println("Failed as expected: " + e);
        }
    }
    public static void performTestThree(ATM atm){
        try{
            atm.withdraw(0000001, 50);
        } catch (Exception e){
            System.out.println("Failed as expected: " + e);
        }
    }
    public static void performTestFour(ATM atm){
        try{
            atm.deposit(0000001, 500);
        } catch (Exception e){
            System.out.println("Unexpected error: " + e);
        }
    }
    public static void performTestFive(ATM atm){
        try{
            atm.deposit(0000002, 100);
        } catch (Exception e){
            System.out.println("Unexpected error: " + e);
        }
    }
    public static void performTestSix(ATM atm){
        try{
            atm.withdraw(0000001, 100);
        } catch (Exception e){
            System.out.println("Unexpected error: " + e);
        }
    }
    public static void performTestSeven(ATM atm){
        try{
            atm.withdraw(0000003, 300);
        } catch (Exception e){
            System.out.println("Unexpected error: " + e);
        }
    }
    public static void performTestEight(ATM atm){
        try{
            atm.withdraw(0000001, 200);
        } catch (Exception e){
            System.out.println("Failed as expected: " + e);
        }
    }
    public static void performTestNine(ATM atm){
        try{
            atm.transfer(0000001, 0000002, 100);
        } catch (Exception e){
            System.out.println("Unexpected error: " + e);
        }
    }
}
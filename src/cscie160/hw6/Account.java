package cscie160.hw6;

/**
 * Representation of a client's account.
 * @author Kalpan
 * @version 1.0
 */
public class Account
{
    /** 
     * Account balance.
     */
    private float balance;

    public Account()
    {
        balance = 0;
        System.out.println("Account Created");
    }
	/**
	 * get account balance
	 * @return balance
	 */
    public float getBalance()
    {
    	synchronized (this)
    	{
            return this.balance;
        }
    }
	/**
	 * set account balance
	 * @param balance
	 */
    public void setBalance(float balance)
    {
        synchronized (this)
        {
            this.balance = balance;
        }
    }
}
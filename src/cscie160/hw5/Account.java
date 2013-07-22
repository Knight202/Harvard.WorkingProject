package cscie160.hw5;

/**
 * This class provides account information of the customer
 * 
 * @author Kalpan
 * @version 1.0
 */
public class Account
{
    /** Customer's account No.*/
    private final int accountNumber;
	/**Account balance*/
    private float balance;

    /**
     * Creates customer account with given Account Number with initial balance 0
     * 
     * @param accountNumber Customer's account No
     */
    public Account(int accountNumber)
    {
        super();
        this.accountNumber = accountNumber;
        balance = 0;
    }

    /**
     * Get account's number.
     * @return Customer's account No
     */
    public int getNumber()
    {
        return accountNumber;
    }

	/**
	 * get account balance
	 * @return balance
	 */
    public float getBalance()
    {
        return balance;
    }

	/**
	 * set account balance
	 * @param balance
	 */
    public void setBalance(float balance)
    {
        this.balance = balance;
    }
}


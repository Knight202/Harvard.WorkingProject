package cscie160.hw4;
/**
 * Account class for ATM project.
 * 
 * @version 1.0
 * @author KALPAN
 *
 */
public class Account {
	/**
	 * Account balance
	 */
	private float balance;
	/**
	 * default constructor. Initial balance is 0.
	 */
	public Account(){

		balance = 0;
	}
	/**
	 * set account balance
	 * @param balance
	 */
	public void setBalance(float balance){
		this.balance = balance;
	}
	/**
	 * get account balance
	 * @return balance
	 */
	public float getBalance(){
		return this.balance;
	}
}

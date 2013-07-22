package cscie160.hw4;
/**
 * ATMImplementation class runs on the server side. It implements interface ATM.
 * This class will update/return the account balance as requested by the client.
 * @version 1.0
 * @author KALPAN
 *
 */
public class ATMImplementation implements ATM {
	
	private Account checking;
	
	public ATMImplementation(){
		checking = new Account();
	}
	/**
	 * Deposits positive amount to the account balance.
	 * @param amount  Amount to deposit
	 * @throws ATMException
	 */
	@Override
	public void deposit(float amount) throws ATMException {
		if (amount <=0) throw new ATMException("Error: Deposit amount must be greater than 0");
		checking.setBalance(checking.getBalance() + amount);
	}
/**
 * Withdraws specified amount from the currentBalancce.
 * If not enough fund available, then throws error.
 * @param amount Amount to withdraw.
 * @throws ATMException  
 */
	@Override
	public void withdraw(float amount) throws ATMException {
		if (amount <=0) throw new ATMException("Error: Withdrawal amount must be greater than 0");
		float currentBalance = checking.getBalance();
		if ((currentBalance - amount)<=0) new ATMException ("Error: Not enough balance.");
		checking.setBalance(currentBalance - amount);
	}
/**
 * Gets currentBalance of the account.
 * @throws ATMException
 */
	@Override
	public Float getBalance() throws ATMException {
		return checking.getBalance();
	}
}

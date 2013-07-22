package cscie160.hw5;

import java.rmi.RemoteException;

/**
 * Prints any error message with ATM for Business errors or with remote objects for technical errors.
 * @author KALPAN
 * @version 1.0
 * 
 */
public class ATMException extends RemoteException
{
	private static final long serialVersionUID = 1L;

	/**
     * Exception message constructor
     * @param msg exception message
     */
    public ATMException(String msg) 
	{
		super(msg);
    }
}

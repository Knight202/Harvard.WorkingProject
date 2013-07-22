package cscie160.project;

import java.rmi.RemoteException;

/**
 * Throws exception any issue while operating ATM.
 * @author Kalpan
 * @version 1.0
 * 
 */
public class ATMException extends RemoteException
{
	private static final long serialVersionUID = 1L;

	public ATMException(String msg) 
	{
		super(msg);
    }
}
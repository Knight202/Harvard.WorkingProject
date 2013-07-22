package cscie160.project;

import java.rmi.Remote;
import java.rmi.RemoteException;
	/**
	* Interface for ATMFactory to obtain remote object ATM.
	* @author Kalpan
	* @version 1.0
	*/
	public interface ATMFactory extends Remote
	{
		/**
		* Get instance of ATM object
		* @return ATM remote object
		* @throws RemoteException
		*/
		public ATM getATM() throws RemoteException;
	}
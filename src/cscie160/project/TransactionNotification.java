package cscie160.project;

import java.io.Serializable;

/**
 * This class handles notifications sent by ATM.
 * @author Kalpan
 * @version 1.0
 * 
 */
public class TransactionNotification implements Serializable
{

	private static final long serialVersionUID = 1L;
	private final String msg;
    private final String notifier;
    
    public TransactionNotification(String notifier, String message)
    {
        this.notifier = notifier;
        this.msg = message;
    }
    /**
     * @return Notification sender
     */
    public String getnotifier()
    {
        return notifier;
    }    /**
     * @return msg
     */
    public String getMsg()
    {
        return msg;
    }
}
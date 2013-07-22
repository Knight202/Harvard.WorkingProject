package cscie160.hw6;

import java.util.LinkedList;

/**
 * This class takes requests from a queue. 
 * @author Kalpan
 * @version 1.0
 */
public class ATMThread extends Thread
{
    /** Queue of requests */
    private final LinkedList<ATMRunnable> queue;
    
    /**
     * Constructs an ATMThread object
     * @param queue
     */
    public ATMThread(LinkedList<ATMRunnable> queue)
    {
        this.queue = queue;
    }
    /**
     * Run request.
     */
    @Override
    public void run()
    {
    	while (true)
        {
            ATMRunnable atmRunnable = null;
            try
            {
            	synchronized (queue)
                {
                	while (queue.isEmpty())
                    {
                        try
                        {
                        	queue.wait();
                        }
                        catch (InterruptedException ex)
                        {
                        	System.out.println("[Interrupted]: " + ex);
                            return;
                        }
                    }
                	atmRunnable = queue.poll();
                	atmRunnable.lockThreadForClient();
                }
                atmRunnable.run();
            }
            finally
            {
            	atmRunnable.unlockThreadForClient();
            }
        }
    }
}
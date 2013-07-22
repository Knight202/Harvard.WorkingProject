package cscie160.hw6;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread reading a client connection and invoking requests' processing.
 * @author Kalpan
 * @version 1.0
 * 
 */
public class ThreadReader extends Thread
{
    protected final Socket clientSocket;
    private BufferedReader bufferedReader;
    private ATM atmImplementation;
    private ReentrantLock lock = new ReentrantLock();
    private final LinkedList<ATMRunnable> queue;

    /**
     * Parameterised constructor
     * @param queue
     * @param clientSocket
     */
    public ThreadReader(LinkedList<ATMRunnable> queue, Socket clientSocket)
    {
        this.queue = queue;
        this.clientSocket = clientSocket;
        atmImplementation = new ATMImplementation();
    }
    /**
     * Handles wait/notify functionality
     */
    public void run()
    {
        try
        {
        	InputStream inputStream = clientSocket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);

            String cmndLine;
            
            while ((cmndLine = bufferedReader.readLine()) != null)
            {
                ATMRunnable atmRunnable = new ATMRunnable(atmImplementation, cmndLine, printStream);
                
                System.out.println ("Running " + atmRunnable);
                
                synchronized (queue)
                {
                    queue.add(atmRunnable);
                    queue.notify();
                }
            }

        } catch (Exception Ex)
        {
            System.out.println("[Error]: " + Ex);
        }
    }
    /**
     * Signals Thread to prevent processing further requests.
     */
    public void lockThreadForClient()
    {lock.lock();}
    /**
     * Signals Thread that further requests can be processed.
     */
    public void unlockThreadForClient()
    {lock.unlock();}
}
package cscie160.hw6;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * A redesign of HW4's Server class handling multi-threaded environment
 * 
 * @author Kalpan
 * @version 1.0
 */
public class Server 
{

    private ServerSocket serverSocket;
    final LinkedList<ATMRunnable> queue;
    private final static int noOfThreads = 5; 
    private final ATMThread[] threadPool;
    
    /**
     * Default constructor
     * @param port
     * @throws java.io.IOException
     */
    public Server(int port) throws java.io.IOException 
	{
		serverSocket = new ServerSocket(port);

		queue = new LinkedList<ATMRunnable>();
		threadPool = new ATMThread[noOfThreads];
		for (int i=0; i<noOfThreads; i++)
			{
				threadPool[i] = new ATMThread(queue);
			}
    }

    /** serviceClient accepts a client connection and reads lines from the socket.
     */
    public void serviceClient() throws java.io.IOException 
	{
        for (int i = 0; i < threadPool.length; i++)
        {
            threadPool[i].start();
        }
        while (true)
        {
            System.out.println ("Accepting clients now");
            Socket clientConnection = serverSocket.accept();
            ThreadReader threadReader = new ThreadReader(queue, clientConnection);

            System.out.println("Client acquired on port #" + serverSocket.getLocalPort() + ", reading from socket");

            threadReader.start();
        }
    }
    
	public static void main(String argv[]) 
	{
		int port = 1099;
		if(argv.length > 0) 
		{
			try 
			{
				port = Integer.parseInt(argv[0]);
			} 
			catch (Exception e) 
			{ 
				e.printStackTrace(); 
			}
		}
		try 
		{
			Server server = new Server(port);
			server.serviceClient();
			System.out.println("Client serviced");
		} 
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
	}
}
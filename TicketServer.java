// Montrell Wiley, David Glenewinkel, Sarah Anderson
// CSCI 434, Project #2
// TicketServer.java
// This server is multithreaded.

import java.io.IOException;
import java.net.ServerSocket;

public class TicketServer implements TicketConstants
{
    public static void main(String[] args) 
    {
        ServerSocket welcomeSocket = null;
        int clientNumber = 1;

        try
        {
            welcomeSocket = new ServerSocket(PORT);
        }
        catch (IOException e)
        {
            System.err.println("Could not listen on port " + PORT);
            System.exit(1);
        }

        System.out.println("Server listening on port " + PORT + "...");

        // Listen for and process connections
        try
        {
            while (true)  
            {
                TicketTask task = new TicketTask(
                    clientNumber, welcomeSocket.accept());
                new Thread(task).start();
                clientNumber++;
                System.out.println("A client successfully connected...");
            }
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}


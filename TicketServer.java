// TicketServer.java

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.Random;

public class TicketServer implements TicketConstants
{
    private static Random generator = new Random();

    public static void main(String[] args) 
    {
        System.out.println("*** Ticket Server ***\n");

        try
        {
            ServerSocket welcomeSocket = new ServerSocket(PORT);

            System.out.printf("Server listening on port %d ...\n", PORT);

            // Always listen for and process connections
            while (true)  
            {

                Socket connectionSocket = welcomeSocket.accept();

                System.out.println("Client connected...");

                Scanner inFromClient = new Scanner(
                    connectionSocket.getInputStream());
                PrintWriter outToClient = new PrintWriter(
                    connectionSocket.getOutputStream());

                String clientRequest = inFromClient.nextLine();
                String result = "Transfer Succeeded";

                System.out.println(result); // status information

                outToClient.println(result);
                outToClient.flush();  // NEEDED to complete transmission
                connectionSocket.close();
            }
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}

// TicketClient.java

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TicketClient implements TicketConstants
{
    public static void main(String[] args) 
    {
        
        System.out.println("*** Ticket Client ***\n");
        System.out.println("This program lists events and allows" 
            + " you to reserve seats at the given event.\n");

        try
        {
            // Open connection to server
            Socket clientToServer = new Socket("tluprog", PORT);

            PrintWriter outToServer = new PrintWriter(
                clientToServer.getOutputStream());
            Scanner inFromServer = new Scanner(
                clientToServer.getInputStream());

            //Gets the list of venues from the server.
            String listOfVenues = inFromServer.next();
            System.out.print(listOfVenues); 

            // Send message to server and receive response
            //outToServer.println(event);
            //outToServer.flush();

            //String result = inFromServer.nextLine();
            //System.out.println("\nFrom Server: " + result);

            clientToServer.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}

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
            + " you to reserve seats at the given event.");

        Boolean Found = false;

        String event = " ";
        Scanner scan = new Scanner(System.in);

        while (!Found)
        {
            System.out.print("Choose the event you want: ");
            String input = scan.next();

            //if event is found in the list of event names
            //{
                Found = true;
            //}
            //else 
            //{
                // System.out.println("Could not understand your choice, "
                    //+ "please try again.");
            //}
        }

        try
        {
            // Open connection to server
            Socket clientToServer = new Socket("tluprog", PORT);

            PrintWriter outToServer = new PrintWriter(
                clientToServer.getOutputStream());
            Scanner inFromServer = new Scanner(
                clientToServer.getInputStream());

            // Send message to server and receive response
            outToServer.println(event);
            outToServer.flush();  // NEEDED to complete transmission

            String result = inFromServer.nextLine();
            System.out.println("\nFrom Server: " + result);

            clientToServer.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}

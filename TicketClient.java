// Montrell Wiley, David Glenewinkel, Sarah Anderson
// CSCI 434, Project #2
// 4/26/19
// TicketClient.java

/** Text based client-server for ticketing system.
 */

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
        
        Scanner scan = new Scanner(System.in);

        try
        {
            // Open connection to server
            Socket clientToServer = new Socket("tluprog", PORT);

            PrintWriter outToServer = new PrintWriter(
                clientToServer.getOutputStream());
            Scanner inFromServer = new Scanner(
                clientToServer.getInputStream());

            String line;
            do 
            {
                line = inFromServer.nextLine();
                System.out.println(line);
            } while (!line.equals(END_OF_FILE));


            //Sends the event to the server
            System.out.print("Choose an event: ");
            String chosenEvent = scan.nextLine();
            outToServer.println(chosenEvent);
            outToServer.flush();
            
            System.out.print("Choose a section: ");
            int chosenSection = scan.nextInt();
            outToServer.println((int) chosenSection);
            outToServer.flush();

            System.out.print("Choose how many tickets are being" 
                + " purchased: ");
            int chosenTickets = scan.nextInt();
            outToServer.println((int) chosenTickets);
            outToServer.flush();

            clientToServer.close();
            System.out.println("Tickets purchased!");
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}

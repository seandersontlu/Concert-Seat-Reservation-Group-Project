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
            String listOfVenues = inFromServer.nextLine();
            System.out.println(listOfVenues); 
            System.out.println();

            //TODO Sends a venue to server
            String chosenVenue = "";
            outToServer.println(chosenVenue);
            outToServer.flush();

            //TODO Gets the list of events at the venue
            String listOfEvents = inFromServer.nextLine();
            System.out.println(listOfEvents);
            System.out.println();

            //TODO Sends the event to the server
            String chosenEvent = "";
            outToServer.println(chosenEvent);
            outToServer.flush();

            //TODO Recieves sections from server
            String listOfSections = inFromServer.nextLine();
            System.out.println(listOfSections);
            System.out.println();

            //TODO Sends section and number to server
            String chosenSection = "";
            outToServer.println(chosenSection);
            outToServer.flush();
            int num = 0;
            outToServer.println(num);
            outToServer.flush();

            //TODO Recieves confirmation OR error
            String result = inFromServer.nextLine();
            System.out.println(result);
            System.out.println();


            clientToServer.close();
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}

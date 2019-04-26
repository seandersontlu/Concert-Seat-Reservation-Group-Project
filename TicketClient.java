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
        
        Scanner scan = new Scanner(System.in);

        try
        {
            // Open connection to server
            Socket clientToServer = new Socket("tluprog", PORT);

            PrintWriter outToServer = new PrintWriter(
                clientToServer.getOutputStream());
            Scanner inFromServer = new Scanner(
                clientToServer.getInputStream());

            //Gets the list of venues from the server.
            //byte venueList = inFromServer.nextByte();
            //String venueList = inFromServer.nextLine();


            String line;
            do 
            {
                line = inFromServer.nextLine();
                System.out.println(line);
            } while (!line.equals(END_OF_FILE));

            //Sends a venue to server
            System.out.println("Enter a venue: ");
            String chosenVenue = scan.nextLine();
            outToServer.println(chosenVenue);
            outToServer.flush();


            //Gets the list of events at the venue
            String listOfEvents = inFromServer.nextLine();
            System.out.println(listOfEvents);
            System.out.println();

            //Sends the event to the server
            System.out.print("Choose event: ");
            String chosenEvent = scan.nextLine();
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

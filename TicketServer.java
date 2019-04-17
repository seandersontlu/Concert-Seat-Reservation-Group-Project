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



                Venue venue;
                String listOfVenues = "Venue:";

                try (ObjectInputStream inFile
                    = new ObjectInputStream(new FileInputStream("Venue.ser")))
                {

                    while (true)
                    {
                        venue = (Venue) inFile.readObject();
                        listOfVenues += venue;
                    }


                }
                catch (FileNotFoundException e)
                {
                    System.err.println("Could not open file \"Venue.ser\"" +
                        "for reading");
                }
                catch (EOFException e)
                {
                    System.out.println("\nReached the end of Venues stores.");
                }
                catch (Exception e)
                {
                    System.err.println(e);

                }
            
            //Sends list of venues out to client
            
            outToClient.println(listOfVenues);
            outToClient.flush();

            //TODO Recieves a venue from the client
            
            String chosenVenue = inFromClient.nextLine();
            System.out.println("Venue recieved from client")


            //TODO Sends the list of events at the venue

            String listOfEvents = "Events: ";
            outToClient.print(listOfEvents);
            outToClient.flush();

            //TODO Recieves the event from the client
            
            String chosenEvent = inFromClient.nextLine();

            //TODO Sends the sections to the client

            String listOfSections = "Sections: ";
            outToClient.print(listOfSections);
            outToClient.flush();

            //TODO Recieves a section and number from the client

            String section = inFromClient.nextLine();
            int num = inFromClient.nextInt();

            //TODO Makes reservation and confirms reservation OR sends error message if the reservation is
            //impossible 
            
            Boolean reservationMade = true;

            String result;
            
            if (reservationMade)
            {
                result = "Your reservation has been made";
            }
            else
            {
                result = "Not enough remaining seats in section." 
                    + " Please try again.";
            }
            
            System.out.println(result);
            outToClient.println(result);
            outToClient.flush();
            connectionSocket.close();


            }
        }
        catch (IOException e)
        {
            System.err.println(e);
        }
    }
}

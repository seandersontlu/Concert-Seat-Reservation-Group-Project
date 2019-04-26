// Montrell Wiley, David Glenewinkel, Sarah Anderson
// CSCI 434, Project #2
// 4/26/19

import java.io.*;
import java.net.*;
import java.util.*;

public class TicketTask implements TicketConstants, Runnable
{
    private Socket socket;
    private int clientNumber;

    public TicketTask(int clientNumber, Socket socket)
    {
        this.clientNumber = clientNumber;
        this.socket = socket;
    }

    public void run()
    {
        try (ObjectInputStream inFile
            = new ObjectInputStream(new FileInputStream("Venue.ser")))
        {
            PrintWriter outToClient = new PrintWriter(
                socket.getOutputStream());
            Scanner inFromClient = new Scanner(
                socket.getInputStream());

            TreeSet venueSet;
            TreeSet eventSet;
            Venue tempVenue;
    
            venueSet = (TreeSet) inFile.readObject();
            Iterator<Venue> venueIter = venueSet.iterator();
            Iterator<Event> eventIter;
            while (venueIter.hasNext())
            {
                tempVenue = venueIter.next();
                outToClient.println();
                outToClient.println(tempVenue);
                outToClient.println("----------");
                outToClient.flush();
                eventSet = tempVenue.getEventSet();
                eventIter = eventSet.iterator();
                while (eventIter.hasNext())
                {
                    outToClient.println(eventIter.next());
                    outToClient.flush();
                }
            }
            outToClient.println("\nEnd of venues.");
            outToClient.flush();
            outToClient.println(END_OF_FILE);
            outToClient.flush();
            System.out.println(END_OF_FILE);

            /*
            byte[] bytes = listOfVenues.getBytes();
            outToClient.print(bytes);
            outToClient.flush();
            */


            System.out.println("Reading input from user " + clientNumber + "...");

            String chosenVenue = inFromClient.nextLine();

            System.out.println("Received chosen venue " + chosenVenue);
            try
            {
                String eventFileName = chosenVenue + "Events.ser";
                File eventFile = new File(eventFileName);
                if (!eventFile.exists())
                {
                    outToClient.print("There are no events available for this venue");
                }

                else
                {
                    try (ObjectInputStream tempInFile
                        = new ObjectInputStream(new FileInputStream(eventFileName)))
                    {
                        eventSet = (TreeSet) tempInFile.readObject();
                    }
                }
            }
            catch (Exception e)
            {
                System.err.print(e);
            }
        }
        catch (Exception e)
        {
            System.err.print(e);
        }
    }
}
                    





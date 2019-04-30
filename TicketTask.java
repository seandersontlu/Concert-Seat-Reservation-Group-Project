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

            System.out.println("Reading input from user " + clientNumber + "...");

            String chosenEvent = inFromClient.nextLine();
            System.out.println("Received chosen event " + chosenEvent
                + " from user " + clientNumber);
            int chosenSection = inFromClient.nextInt();
            System.out.println("Received chosen section " + chosenSection
                + " from user " + clientNumber);
            int chosenTickets = inFromClient.nextInt();
            System.out.println("Received chosen tickets " + chosenTickets
                + " from user " + clientNumber);

            // For testing purposes
            String currentEventFile = searchForEvent(chosenEvent);
            System.out.println("\nSearched for event: " + chosenEvent);
            System.out.println("Result event file: " + currentEventFile);

            try (ObjectInputStream tempInFile
                = new ObjectInputStream(new
                FileInputStream(currentEventFile)))
            {
                eventSet = (TreeSet<Event>) inFile.readObject();

                try (ObjectOutputStream outFile
                    = new ObjectOutputStream(new
                    FileOutputStream(currentEventFile)))
                {
                    Event currentEvent;
                    String currentTitle = "";

                    Iterator<Event> iter = eventSet.iterator();
                    while (iter.hasNext())
                    {
                        currentEvent = iter.next();
                        currentTitle = currentEvent.getEventTitle();
                        
                        //Testing
                        System.out.println(currentEvent);

                        if (currentTitle.equals(chosenEvent))
                        {
                            currentEvent.buyTickets(
                                chosenTickets, chosenSection);
                            System.out.println("\n\nAfter buying tickets\n");
                        }
                        
                    }
                    //Testing
                    System.out.println("\n\nSet after buying tickets\n");
                    iter = eventSet.iterator();
                    while (iter.hasNext())
                        System.out.println(iter.next());
                    outFile.writeObject(eventSet);
                }
                catch (Exception e)
                {
                    System.err.print("Barfed inside inner loop " + e);
                }
            }
            catch (Exception e)
            {
                System.err.print("Barfed inside outer loop " + e);
            }

            socket.close();


        }
        catch (EOFException e)
        {
            System.out.println("\nReached the end of venues stored.");
        }
        catch (Exception e)
        {
            System.err.print(e);
        }
    }


    private String searchForEvent(String search)
    {
        String result = "";

        try (ObjectInputStream inFile
            = new ObjectInputStream(new FileInputStream("Venue.ser")))
        {
            Event currentEvent;
            TreeSet venueSet;
            TreeSet eventSet;
            Venue tempVenue;
            String eventName = "";
    
            venueSet = (TreeSet) inFile.readObject();
            Iterator<Venue> venueIter = venueSet.iterator();
            Iterator<Event> eventIter;
            while (venueIter.hasNext())
            {
                tempVenue = venueIter.next();
                eventSet = tempVenue.getEventSet();
                eventIter = eventSet.iterator();
                while (eventIter.hasNext())
                {
                    currentEvent = eventIter.next();
                    eventName = currentEvent.getEventTitle();

                    if (eventName.equals(search))
                        result = tempVenue.getFileName();

                }
            }

        }
        catch (FileNotFoundException e)
        {
            System.err.print(e);
        }
        catch (EOFException e)
        {
            System.out.println("\nReached the end of events stored.");
        }
        catch (Exception e)
        {
            System.err.print(e);
        }
        
        return result;

    }

}
                    





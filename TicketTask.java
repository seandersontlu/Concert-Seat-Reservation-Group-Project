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
            // For testing purposes
            String currentEventFile = searchForEvent(chosenEvent);
            System.out.println("\nSearched for event: " + chosenEvent);
            System.out.println("Result event file: " + currentEventFile);
            //

            int chosenSection = inFromClient.nextInt();
            System.out.println("Received chosen section " + chosenSection
                + " from user " + clientNumber);
            int chosenTickets = inFromClient.nextInt();
            System.out.println("Received chosen tickets " + chosenTickets
                + " from user " + clientNumber);



            // Actual purchasing tickets process
            //
            try (ObjectInputStream tempInFile
                = new ObjectInputStream(new
                FileInputStream(currentEventFile)))
            {
                eventSet = (TreeSet<Event>) tempInFile.readObject();
                Event currentEvent;
                String currentTitle = "";

                Iterator<Event> iter = eventSet.iterator();
                while (iter.hasNext())
                {
                    currentEvent = iter.next();
                    currentTitle = currentEvent.toString();
                        
                    if (currentTitle.equals(chosenEvent))
                    {
                        try (ObjectOutputStream outFile
                            = new ObjectOutputStream(new
                            FileOutputStream(currentEventFile)))
                        {
                            //Testing
                            System.out.println("Found a match. "
                                + "Reserving seats...");
                            Thread.sleep(2000);
                            eventSet.remove(currentEvent);
                            currentEvent.buyTickets(
                                chosenTickets, chosenSection);
                            eventSet.add(currentEvent);     
                            outFile.writeObject(eventSet);
                            //Testing
                            System.out.println("\n\nAfter buying tickets INSIDE"
                                + " of loop\n");
                            Thread.sleep(2000);
                            iter = eventSet.iterator();
                            while (iter.hasNext())
                            {
                                currentEvent = iter.next();
                                System.out.println(currentEvent);
                                System.out.println("----\n"
                                    + currentEvent.getSeatChart());
                            }
                        }
                        catch (Exception e)
                        {
                            System.err.print("Error inside inner loop " + e);
                        }


                        //Testing
                        System.out.println("\n\nAfter buying tickets OUTSIDE"
                            + " of loop\n");
                        Thread.sleep(2000);
                        iter = eventSet.iterator();
                        while (iter.hasNext())
                        {
                            currentEvent = iter.next();
                            System.out.println(currentEvent);
                            System.out.println("----\n"
                                + currentEvent.getSeatChart());
                        }
                    }
                        
                }


            }
            catch (EOFException e)
            {
                System.out.println("\nReached the end of events stored"
                   + " in " + currentEventFile);
            }
            catch (Exception e)
            {
                System.err.print("Error inside outer loop " + e);
            }

            System.out.println("\nTickets purchased.");
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


     /** Searches each Venue's event set
     * for the given event and determines if
     * there is a match
     * @param search
     * @return result file name of where event is stored
     */
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
                    eventName = currentEvent.toString();

                    if (eventName.equals(search))
                    {
                        System.out.println(currentEvent.getSeatChart());
                        result = tempVenue.getFileName();
                    }

                }
            }

        }
        catch (FileNotFoundException e)
        {
            System.err.print(e);
        }
        catch (EOFException e)
        {
            System.out.println("\nReached the end of events stored"
                + " using searchForEvent method.");
        }
        catch (Exception e)
        {
            System.err.print(e);
        }
        
        return result;

    }

}
                    





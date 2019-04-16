// Montrell Wiley, David Glenewinkel, Sarah Anderson
// CSCI 434, Project #2
// 4/1/19

import java.util.*;
import java.io.*;

public class AdminController
{
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args)
    {
        final String venueFileName = "Venue.ser";
        Venue venue;

        System.out.println("Reading Venue objects from Venue.ser...\n");

        try (ObjectInputStream inFile
            = new ObjectInputStream(new FileInputStream("Venue.ser")))
        {
            while (true)
            {
                venue = (Venue) inFile.readObject();
                System.out.println("Venue:  " + venue);
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Could not open file \"Venue.ser\"" +
                "for reading");
        }
        catch (EOFException e)
        {
            System.out.println("\nReached the end of Venues stored.");
        }
        catch (Exception e)
        {
            System.err.println(e);
        }

        System.out.print("Please enter a Venue name: ");
        String venueName = scan.nextLine();

        try
        {
            String eventFileName = venueName + "Events.ser";
            File eventFile = new File(eventFileName);
            if (!eventFile.exists())
            {
                // This will create a new venue as well as
                // the event file that pairs with it.
                TreeSet<Event> eventSet = new TreeSet<Event>();
                boolean finished = false;

                System.out.println("\nCreating new venue...");
                System.out.print("Enter the address for the venue: ");
                String address = scan.nextLine().trim();
                System.out.print("Enter the amount of seats: ");
                int numOfSeats = scan.nextInt();
                int[][] seats = new int[numOfSeats / 2][numOfSeats / 2];
                createEvents(eventSet, eventFileName);


                try (ObjectOutputStream outFile
                    = new ObjectOutputStream(new FileOutputStream(venueFileName)))
                {
                    venue = new Venue(venueName, address, seats);
                    outFile.writeObject(venue);
                }
                catch (FileNotFoundException e)
                {
                    System.err.println("Cannot open file " + venueFileName + "for writing.");
                }
                catch (IOException e)
                {
                    System.err.println("Error in writing to file " + venueFileName);
                }

            }
            else
            {
                try (ObjectInputStream inFile
                    = new ObjectInputStream(new FileInputStream(eventFileName)))
                {
                    TreeSet<Event> eventSet 
                        = (TreeSet<Event>) inFile.readObject();

                    System.out.println("Here are the events for" +
                        " the selected venue:\n");

                    Iterator<Event> iter = eventSet.iterator();
                    while (iter.hasNext())
                        System.out.println(iter.next());

                    createEvents(eventSet, eventFileName);
                }

                catch (FileNotFoundException e)
                {
                    System.err.println("Could not open file" + eventFileName +
                        "for reading");
                }
                
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    private static void createEvents(TreeSet<Event> eventSet, String eventFileName)
    {
        try (ObjectOutputStream outFile
            = new ObjectOutputStream(new FileOutputStream(eventFileName)))
        {
            boolean finished = false;
            String choice = "";
            do
            {
                System.out.print("\nCreate a new event for" + 
                    " this venue? Y/N: ");
                choice = scan.next().toLowerCase();
                if (choice.equals("n"))
                    finished = true;
                else
                {
                    System.out.println("\nCreating new event...");
                    System.out.print("Enter the event title: ");
                    // Scanner was reading a random space so
                    // reinitializing the scanner fixes the issue
                    scan = new Scanner(System.in);
                    String eventTitle = scan.nextLine();
                    System.out.print("Enter the date of the event: ");
                    String date = scan.nextLine();
                    System.out.print("Enter the starting time: ");
                    String startTime = scan.nextLine();
                    System.out.print("Enter the ending time: ");
                    String endTime = scan.nextLine();
                        
                    eventSet.add(new Event(
                        eventTitle, date, startTime, endTime));
                }

            }while(!finished);
                        
            outFile.writeObject(eventSet);

            System.out.println("\nWrote event list to " + eventFileName +
                "\n");
        }
                    
        catch (FileNotFoundException e)
        {
            System.err.println("Cannot open file " + eventFileName + "for writing.");
        }
        catch (IOException e)
        {
            System.err.println("Error in writing to file " + eventFileName);
        }
    }

}

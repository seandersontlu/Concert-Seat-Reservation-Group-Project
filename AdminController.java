// Montrell Wiley, David Glenewinkel, Sarah Anderson
// CSCI 434, Project #2
// 4/1/19

import java.util.*;
import java.io.*;

public class AdminController
{
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

        Scanner scan = new Scanner(System.in);

        System.out.print("Please enter a Venue name: ");
        String venueName = scan.nextLine();

        try
        {
            String eventFileName = venueName + "Events.ser";
            File eventFile = new File(eventFileName);
            if (!eventFile.exists())
            {
                // TODO
                // This will create a new venue as well as
                // the event file that pairs with it.
                TreeSet<Event> eventSet = new TreeSet<Event>();
                boolean finished = false;

                System.out.println("\nCreating new venue...");
                System.out.print("Enter the address for the venue: ");
                String address = scan.nextLine();
                System.out.print("Enter the amount of seats: ");
                int numOfSeats = scan.nextInt();
                int[][] seats = new int[numOfSeats / 2][numOfSeats / 2];

                try (ObjectOutputStream outFile
                    = new ObjectOutputStream(new FileOutputStream(eventFileName)))
                {
                    do
                    {
                        System.out.println("\nCreating new event...");
                        System.out.print("Enter the event title: ");
                        String eventTitle = scan.nextLine();
                        System.out.print("Enter the date of the event: ");
                        String date = scan.nextLine();
                        System.out.print("Enter the starting time: ");
                        String startTime = scan.nextLine();
                        System.out.print("Enter the ending time: ");
                        String endTime = scan.nextLine();
                        
                        eventSet.add(new Event(
                            eventTitle, date, startTime, endTime));

                        System.out.print("\nCreate another event for" + 
                            " this venue? Y/N: ");
                        String choice = scan.next().toLowerCase();
                        if (choice.equals("n"));
                            finished = true;

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
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}

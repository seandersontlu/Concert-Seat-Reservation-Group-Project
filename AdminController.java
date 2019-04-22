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
        TreeSet venueSet;

        File venueFile = new File(venueFileName);
        if (!venueFile.exists())
        {
            try (ObjectOutputStream outFile
                = new ObjectOutputStream(new FileOutputStream(venueFileName)))
            {
                venueSet = new TreeSet<Venue>();
                outFile.writeObject(venueSet);
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

        System.out.println("Reading Venue objects from " + venueFileName +
            "...\n");

        try (ObjectInputStream inFile
            = new ObjectInputStream(new FileInputStream(venueFileName)))
        {
            venueSet = (TreeSet) inFile.readObject();
            Iterator<Venue> iter = venueSet.iterator();
            while (iter.hasNext())
                System.out.println(iter.next());
                
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Could not open file " + venueFileName +
                " for reading");
        }
        catch (EOFException e)
        {
            System.out.println("\nReached the end of Venues stored.");
        }
        catch (Exception e)
        {
            System.err.println(e);
        }

        System.out.print("\nPlease enter a Venue name: ");
        String venueName = scan.nextLine();

        try (ObjectInputStream tempInFile
            = new ObjectInputStream(new FileInputStream("Venue.ser")))
        {
            String eventFileName = venueName + "Events.ser";
            File eventFile = new File(eventFileName);
            if (!eventFile.exists())
            {
                // This will create a new venue as well as
                // the event file that pairs with it.
                TreeSet<Event> eventSet = new TreeSet<Event>();
                venueSet = (TreeSet) tempInFile.readObject();
                System.out.println("\nThat venue does not exist," +
                    " creating new venue...");
                
                // Events must be called first because
                // a venue must read from a set of events
                // to be created
                createEvents(eventSet, eventFileName);
                createVenues(venueName, venueSet, venueFileName);

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

    private static void createVenues(String venueName, TreeSet<Venue> venueSet,
                                        String venueFileName)
    {
        try (ObjectOutputStream outFile
            = new ObjectOutputStream(new FileOutputStream(venueFileName)))
        {
            System.out.println("\nCreating new venue...");
            System.out.print("Enter the address for the venue: ");
            // Scanner was reading a random space so
            // reinitializing the scanner fixes the issue
            scan = new Scanner(System.in);
            String address = scan.nextLine();
            System.out.print("Enter the amount of sections: ");
            int numOfSections = scan.nextInt();
            System.out.print("Enter the amount of rows of seats " + 
                "for each section: ");
            int rows = scan.nextInt();
            System.out.print("Enter the amount of columns of seats " + 
                "for each section: ");
            int cols = scan.nextInt();

            venueSet.add(new Venue(
                venueName, address, numOfSections, rows, cols));
            outFile.writeObject(venueSet);
            System.out.println("\nWrote new venue to " + venueFileName);
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

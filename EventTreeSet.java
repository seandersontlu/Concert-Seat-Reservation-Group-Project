// Montrell Wiley, David Glenewinkel, Sarah Anderson
// CSCI 434, Project #2
// 4/1/19

import java.util.*;
import java.io.*;

public class EventTreeSet
{
    public static void main(String[] args)
    {
        final String FILE_NAME = "events.csv";
        TreeSet<Event> eventSet = new TreeSet<Event>();

        /** Create the set of events read from the csv file
         */
        try (Scanner fileScan = new Scanner(new File(FILE_NAME)))
        {
            while (fileScan.hasNext())
            {
                String line = fileScan.nextLine();
                String[] items = line.split(",");

                String eventTitle = items[0];
                String date = items[1];
                String startTime = items[2];
                String endTime = items[3];
                eventSet.add(new Event(
                    eventTitle, date, startTime, endTime));
            }
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Cannot open file.");
        }

        /** Create serialized objects from the treeset
         *  and write to Event.ser
         */
        try (ObjectOutputStream outFile
            = new ObjectOutputStream(new FileOutputStream("Event.ser")))
        {
            outFile.writeObject(eventSet);

            System.out.println("\nWrote event list to Event.ser\n");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Cannot open file \"Event.ser\" for writing.");
        }
        catch (IOException e)
        {
            System.err.println("Error in writing to file \"Event.ser\".");
        }

        Iterator<Event> iter = eventSet.iterator();

        while (iter.hasNext())
            System.out.println(iter.next());
                     
    }
}

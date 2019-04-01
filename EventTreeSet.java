// Montrell Wiley
// CSCI 337 Homework #9, Problem #10
// PersonTreeSet.java
// 10/24/18
//
// This program creates a tree set of person objects

import java.util.*;
import java.io.*;

public class EventTreeSet
{
    public static void main(String[] args)
    {
        final String FILE_NAME = "events.csv";
        TreeSet<Event> eventSet = new TreeSet<Event>();

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

        Iterator<Event> iter = eventSet.iterator();

        while (iter.hasNext())
            System.out.println(iter.next());
                     
    }
}

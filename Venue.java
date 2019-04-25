// David, Sarah Anderson, and Montrell 
// CSCI 434  Project #2 Iteration #1
// Submitted: 4/5/2019
// Venue.java

/*
 * This class represents the Venue where the event will be held
 * @author  Sarah Anderson, Montrel Wiley, David Glenewinkel
 */

import java.util.*;
import java.io.*;

public class Venue implements Comparable<Venue>, Serializable
{
    private static final String SEAT_OPEN = "O";
    private static final String SEAT_TAKEN = "X";

    private String message;
    private String venueName;
    private String address;
    
    private TreeSet eventSet;
    private String fileName;
    
    /** Creates the Venue object
     * @param venueName   the name of the venue
     * @param address     the venue's address
     */
    public Venue(String venueName, String address) 
    {
        this.venueName = venueName;
        this.address = address;
        fileName = venueName + "Events.ser";
        createTreeSet();
    }

    /** Gets name of the venue
     * @return  the name of the venue
     */
    public String getVenueName()
    {
        return venueName;
    }
    
    /** Gets address of the venue
     * @return  the venue's address
     */
    public String getAddress()
    {
        return address;
    }
    
    /** Sets name of the venue
     */
    public void setVenueName(String name)
    {
        venueName = name;
    }
    
    /** Sets the address of the venue
     */
    public void setAddress(String venueAddress)
    {
        address = venueAddress;
    }

    /**Returns the TreeSet of events
     * @return TreeSet of events
     */
     public TreeSet getEventSet()
     {
         return eventSet;
     }

    /** Creates the TreeSet of Events associated with this Venue
     */
    private void createTreeSet()
    {
        try (ObjectInputStream inFile
            = new ObjectInputStream(new FileInputStream(fileName)))
        {
            eventSet = (TreeSet) inFile.readObject();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Could not open file " + fileName +
                "for reading");
        }
        catch (EOFException e)
        {
            System.out.println("\nReached the end of events stored.");
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    /**Compares two venue objects
     * @return The int result of comparing venue names
     */
     public int compareTo(Venue other)
     {
         int result = venueName.compareTo(other.venueName);

         return result;
     }

    /**Returns a string representation
     * @return  the string representation of the venue info
     */
    public String toString()
    {
        return venueName + " - " + address;
    }
}

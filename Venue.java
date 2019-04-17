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

public class Venue implements Serializable
{
    private static final int SEAT_OPEN = 0;
    private static final int SEAT_TAKEN = 1;

    private String message;
    private String venueName;
    private String address;
    
    private int numSections;
    private int rowsPerSect;
    private int totalRows;
    private int totalCols;
    private int seatsPerSect;
    private int totalSeats;

    private LinkedList[] seats;
    private TreeSet eventSet;
    private String fileName;
    
    /** Creates the Venue object
     * @param venueName   the name of the venue
     * @param address     the venue's address
     * @param numSections the number of sections of seats
     * @param rowsPerSect the number of rows in each section
     * @param totalCols   the total number of columns
     */
    public Venue(String venueName, String address, int numSections, 
        int rowsPerSect, int totalCols)
    {
        this.venueName = venueName;
        this.address = address;
        fileName = venueName + "Events.ser";

        this.numSections = numSections;
        this.rowsPerSect = rowsPerSect;
        this.totalCols = totalCols;
        int seatsPerSect = rowsPerSect * totalCols;
        totalSeats = seatsPerSect * numSections;
        totalRows = numSections * rowsPerSect;

        seats = new LinkedList[numSections];
        arrangeSeats();
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
    
    /** Gets the number of sections of seats
     * @ return  the number of seactions of seats
     */
     public int getNumSections()
     {
         return numSections;
     }

    /** Gets the number of rows per section
     * @return  the number of rows per Section
     */
    public int getRowsPerSection()
    {
        return rowsPerSect;
    }
    
    /** Gets the number of seats per section
     * @return  the number of seats per Section
     */
    public int getSeatsPerSection()
    {
        return seatsPerSect;
    }

    /** Gets the total number of rows
     * @return  the number of row of seats
     */
    public int getTotalRows()
    {
        return totalRows;
    }

    /** Gets the total number of columns
     * @return  the number of columns of seats
     */
    public int getTotalCols()
    {
        return totalCols;
    }

    /** Gets the number seats
     * @return  the total number of seats in the venue
     */
    public int getNumSeats()
    {
        return totalSeats;
    }

    /** Gets the array representing the seats
     * @return  the array of seats
     */
    public LinkedList[] getSeats()
    {
        return seats;
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

    /** Sets the number of the sections
     */
    public void setNumSections(int num)
    {
        numSections = num;
    }

    /** Sets the number of rows per section
     */
    public void setRowsPerSections(int num)
    {
        rowsPerSect = num;
    }
    
    /** Sets the number of columns
     */
    public void setTotalCols(int num)
    {
        totalCols = num;
    }

    
    /** Resets the seats to SEAT_OPEN
     */
    public void resetSeats()
    {
        arrangeSeats();
    }

    /** Returns the TreeSet of events
     * @return TreeSet of events
     public TreeSet getEventSet()
     {
         return eventSet;
     }

    /** Creates the TreeSet of Events
     *  associated with this Venue
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

    /** Creates the array of Linked Lists representing the seats 
     *  in the venue
     */
    private void arrangeSeats()
    {
        int rowCounter = 0;

        for (int i = 0; i < seats.length; i++)
        {
            seats[i] = new LinkedList();
            
            for (int j = 0; j < rowsPerSect; j++)
            {
                for (int k = 0; k < totalCols; k++)
                    seats[i].add(SEAT_OPEN);
                    //seats[i].add("R" + (rowCounter+1) + "-" + (k+1));
                rowCounter++;
            }
        }
    }

    /** Returns a string representation
     * @return  the string representation of the seats
     */
    public String toString()
    {
        return venueName + "\n" + address + "\n";
    }
}

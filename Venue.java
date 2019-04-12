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

public class Venue
{
    private static final int SEAT_OPEN = 0;
    private static final int SEAT_TAKEN = 1;

    private String message;
    private String venueName;
    private String address;
    private int numRows;
    private int numCols;
    private int numSeats;
    private int[][] seats;
    private TreeSet eventSet;
    private String fileName;
    
    /** Creates the Venue object
     * @param venueName   the name of the venue
     * @param address     the venue's address
     * @param seats       the seats in the venue
     */
    public Venue(String venueName, String address, int[][] seats)
    {
        this.venueName = venueName;
        this.address = address;
        this.seats = seats;
        fileName = venueName + "Events.ser";
        numRows = seats.length;
        numCols = seats[0].length;
        numSeats = numRows * numCols;
        initializeSeats();
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

    /** Gets the number of row
     * @return  the number of row of seats
     */
    public int getNumRows()
    {
        return numRows;
    }

    /** Gets the seat column 
     * @return  the number of columnss of seats
     */
    public int getNumCols()
    {
        return numCols;
    }

    /** Gets the number seats
     * @return  the number of seats in the venue
     */
    public int getNumSeats()
    {
        return numSeats;
    }
    
    /** Gets the 2d array representing the seats
     * @return  the 2d array of seats
     */
    public int[][] getSeats()
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

    /** Sets the number of the seats
     */
    public void setNumSeats(int num)
    {
        numSeats = num;
    }

    /** Sets the 2d array representing the seats
     */
    public void setSeats(int row, int col)
    {
        seats = new int[row][col];
        numRows = row;
        numCols = col;
        initializeSeats();
    }
     
    /** Sets the seat to SEAT_TAKEN (1)
     */
    public void reserveSeat(int row, int col) 
    {
        if (seats[row][col] == SEAT_OPEN)
            seats[row][col] = SEAT_TAKEN;
        else
            message = "This seat is taken.";
    }

    /**
     * Counts the number of open seats
     * @return  the number of open seats
     */
    public int numOpenSeats()
    {
        int num = 0;
        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numCols; j++)
                if (seats[numRows][numCols] == SEAT_OPEN)
                    num++;
        return num;
    }

    /**
     * Counts the number of taken seats
     * @return  the number of taken seats
     */
    public int numTakenSeats()
    {
        int num = 0;
        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numCols; j++)
                if (seats[numRows][numCols] == SEAT_TAKEN)
                    num++;
        return num;
    }

    /**
     * Checks if a seat is taken
     * @param row   The row of the desired seat
     * @param col   The column of the desired seat
     * @return  determines if seat is taken
     */
    public boolean isSeatTaken(int row, int col)
    {
        boolean result = false;
        if (seats[row][col] == SEAT_TAKEN)
            result = true;
        else
            result = false;
        return result;
    }
    
    /**
     * Checks if a seat is open
     * @param row   The row of the desired seat
     * @param col   The column of the desired seat
     * @return  determines if seat is open
     */
    public boolean isSeatOpen(int row, int col)
    {
        boolean result = false;
        if (seats[row][col] == SEAT_OPEN)
            result = true;
        else
            result = false;
        return result;
    }
   
    /** Resets the seats to SEAT_OPEN
     */
    public void resetSeats()
    {
        initializeSeats();
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

    /** Sets all the seats to SEAT_OPEN
     */
    private void initializeSeats()
    {
        for (int i = 0; i < numRows; i++)
            for (int j = 0; j < numCols; j++)
                seats[i][j] = SEAT_OPEN;
    }

    /** Returns a string representation
     * @return  the string representation of the seats
     */
    public String toString()
    {
        String result = "";
        for (int i = 0; i < numCols; i++)
            result += "\t[" + (i + 1) + "]";
        result += "\n";

        for (int j = 0; j < numRows; j++)
        {
            result += "[" + (j+1) + "]";
            for (int k = 0; k < numCols; k++)
                result += "\t " + seats[j][k];
            result += "\n";
        }
        return result;
    }
}

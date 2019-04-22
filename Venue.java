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
    //private static final String SEAT_OPEN = "O";
    private static final String SEAT_TAKEN = "X";

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
        seatsPerSect = rowsPerSect * totalCols;
        totalSeats = seatsPerSect * numSections;
        totalRows = numSections * rowsPerSect;

        seats = new LinkedList[totalSeats];
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
     * @return  the number of sections of seats
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

    /**Counts the number of open seats
     * @return  the number of open seats
    */
    public int numOpenSeats()
    {
        int num = 0;
        for (int i = 0; i < seats.length; i++)
            for (int j = 0; j < seats[i].size() / numSections; j++)
                if (!seats[i].contains(SEAT_TAKEN))
                    num++;
        return num;
    }
    
    /**Counts the number of taken seats
     * @return  the number of taken seats
    */
    public int numTakenSeats()
    {
        int num = 0;
        for (int i = 0; i < seats.length; i++)
            for (int j = 0; j < seats[i].size() / numSections ; j++)
                if (seats[i].contains(SEAT_TAKEN))
                    num++;
        return num;
    }

    /**Counts the number of rows needed to seat more than what one row can hold
     * @ param numSeats the number of seats to reserve
     * @ throws IllegalArgumentException the parameter must be larger than the
     *                                   total number of columns
     * @ return The number of rows needed to seat more than what a single row
     *          can hold
     */
    public int generateNumRowsNeeded (int numSeats)
    {
        if (numSeats <= totalCols)
            throw new IllegalArgumentException("Error: " +
                "Value entered must be greater than " +
                totalCols);

        double numRowsNeeded = 1.0;
        
        if (numSeats > totalCols)
        {
            numRowsNeeded = (double) numSeats / totalCols;
            numRowsNeeded = Math.ceil(numRowsNeeded);
        }

        int rowsNeeded = (int) numRowsNeeded;
        return rowsNeeded;
    }
    
    /**Counts the number of open seats in a given row
     * @ param rowNum   The row number you want to count the number of open
     *                  seats
     * @ throws IllegalArgumentException the parameter must be between 1 and
     *                                   the total number of rows, inclusively
     * @ return The number of open seats in a given row
     */
    public int countOpenSeatsInRow(int rowNum)
    {
        if (rowNum <= 0 || rowNum > totalRows)
            throw new IllegalArgumentException("Error: " +
                "Invalid input.\n The venue does not have " + 
                rowNum + " rows.\n" + "It has " + totalRows + 
                " rows.");
        
        int num = 0;
        for (int i = 0; i < seats[rowNum].size() - 1; i++)
            if (seats[rowNum].get(i) != SEAT_TAKEN)
                num++;
        return num;
    }

    /**Resets the seats to SEAT_OPEN
     */
    public void resetSeats()
    {
        arrangeSeats();
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
    
    /** Assigns values to the array of LinkedLists, seats[] 
     */
    public void arrangeSeats()
    {
        int rowCounter = 1;
        for (int i = 0; i < seats.length; i++)
        {
            seats[i] = new LinkedList();
            for (int j = 0; j < totalCols; j++)
                seats[i].add("R" + rowCounter + "-" + (j+1));
            rowCounter++;
        }
    }
    
    /**Reserves seat[s] if the number of seats desired to be reserve can fit in one row,
     * @param numSeats The number of seats to reserve
     * @param row The row you wish to be seated
     */
    public void reserveSeatsInOneRow (int numSeats, int row) 
    {
        for (int i = 0; i < numSeats; i++)
            if (seats[row].get(i) != SEAT_TAKEN)
                seats[row].set(i, SEAT_TAKEN);
    }

    /**Reserves seat[s] if the number of seats desired to be reserved fits in
     * multiple rows
     * @param numSeats The number of seats to reserve
     * @param row The starting row you wish to be seated
     * @param numRows The number of rows needed to allocate numSeats into
     */
    public void reserveSeatsInMultRows (int numSeats, int row,
        int numRows) 
    {
        int ct = 0;
        for (int i = row; i < row + numRows; i++)
        {
            for (int j = 0; j < totalCols && ct != numSeats; j++)
            {
                if (seats[i].get(j) != SEAT_TAKEN)
                    seats[i].set(j, SEAT_TAKEN);
                ct++;
            }
        }
    }
    
   /**Prints string representation of the venue seats
    * @return The string representation of the venue seats
    */
    public String displaySeats()
    {
        int sectCount = 1;
        String result = "";
        result += "\nSection 1: \n";
        
        for (int i = 0; i < seats.length / totalCols; i++)
        {
            if (i == rowsPerSect * (sectCount))
            {
                result += "\nSection " + (sectCount+1) + ": \n";
                sectCount++;
            }
            result += seats[i] + "\n";
        }

        return result;
    }

    /**Returns a string representation
     * @return  the string representation of the venue info
     */
    public String toString()
    {
        return venueName + "\n" + address + "\n";
    }
}

// SeatChart.java
/*
 * This class represents the seating chart
 * @author  Sarah Anderson, Montrel Wiley, David Glenewinkel
 */

import java.util.*;
import java.io.*;

public class SeatChart
{
    private static final String SEAT_OPEN = "O";
    private static final String SEAT_TAKEN = "X";

    private LinkedList[] seats;
    private int numSections;
    private int rowsPerSect;
    private int colsPerSect;
    private int openSeats;

     /* 
     * @param numSections the number of sections of seats
     * @param rowsPerSect the number of rows in each section
     * @param colsPerSect   the number of columns in each section
     */
    public SeatChart (int numSections, int rowsPerSect, int colsPerSect)
    {
        this.numSections = numSections;
        this.rowsPerSect = rowsPerSect;
        this.colsPerSect = colsPerSect;
        seats = new LinkedList[rowsPerSect * numSections];
        arrangeSeats();
    }
    
    /*
     * Gets the array representing the seats
     * @return  the array of seats
     */
    public LinkedList[] getSeats()
    {
        return seats;
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
    public int getRowsPerSect()
    {
        return rowsPerSect;
    }
    
    /** Gets the number of columns per section
     * @return  the number of columns per Section
     */
    public int getColsPerSect()
    {
        return colsPerSect;
    }
    
    /*
     * Calculates the number of seats per section
     * @return  the number of seats per Section
     */
    public int getSeatsPerSection()
    {
        return rowsPerSect * colsPerSect;
    }

    /*
     * Calculates the total number of rows
     * @return  the number of row of seats
     */
    public int getTotalRows()
    {
        return rowsPerSect * numSections;
    }

    /*
     * Calculates the total number of columns
     * @return  the number of columns of seats
     */
    public int getTotalCols()
    {
        return colsPerSect * numSections;
    }

    /*
     * Gets the number seats
     * @return  the total number of seats in the venue
     */
    public int getNumSeats()
    {
        return numSections * getSeatsPerSection();
    }
    
    /*
     * Gets the seats in a given section
     * @param section   the section number
     * @throw IllegalArgumentException  the specified section doesn't exist
     * @return  the seats in the specified section
     */
    public LinkedList[] getSectionSeats (int section)
    {
        if (section <= 0 || section > numSections)
            throw new IllegalArgumentException("Error: Section " +
                section + " doesn't exist.");

        LinkedList [] sectSeats = new LinkedList[rowsPerSect];
        int start = rowsPerSect * section - rowsPerSect;
        for (int i = 0; i < sectSeats.length; i++)
            sectSeats[i] = seats[start+i];
        return sectSeats;
        
        /* If you want to test this method for yourself, the following piece of
         * code will print the results.
         * 
         * System.out.println("Section " + section + ":");
         * for (int i = 0; i < sectSeats.length; i++)
         *   System.out.print(sectSeats[i] + "\n");
        */
    }
    
    public String displaySection()
    {
        String result = "";
        return result;
    }

    /**Sets the number of the sections
     * @param num  the new value
     * /
    public void setNumSections(int num)
    {
        numSections = num;
    }

    /** Sets the number of rows per section
     * @param num  the new value
     */
    public void setRowsPerSections(int num)
    {
        rowsPerSect = num;
    }
    
    /** Sets the number of columns
     * @param num  the new value
     */
    public void setColsPerSection (int num)
    {
        colsPerSect = num;
    }
        

    /**Resets the seats to SEAT_OPEN
     */
    public void resetSeats()
    {
        arrangeSeats();
    }
    
    /** Assigns values to the array of LinkedLists, seats[] 
     */
    public void arrangeSeats()
    {
        for (int i = 0; i < seats.length; i++)
        {
            seats[i] = new LinkedList();
            for (int j = 0; j < colsPerSect; j++)
                seats[i].add("R"+(i+1)+"-" +(j+1));
        }
    }
    
    /*
     * Counts the number of rows needed to seat more than what one row can hold
     * @ param numSeats the number of seats to reserve
     */
    private int generateNumRows (int numSeats)
    {
        double numRows = 1.0;
        
        if (numSeats > colsPerSect)
        {
            numRows = (double) numSeats / colsPerSect;
            numRows = Math.ceil(numRows);
        }

        int rows = (int) numRows;
        return rows;
    }

   /**Prints string representation of the venue seats
    * @return The string representation of the venue seats
    */
    public String toString()
    {
        int sectCount = 1;
        String result = "";
        result += "\nSection 1: \n";
        
        for (int i = 0; i < seats.length ; i++)
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
}

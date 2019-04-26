// SeatChart.java
/*
 * This class represents the seating chart
 * @author  Sarah Anderson, Montrel Wiley, David Glenewinkel
 */

import java.util.*;
import java.io.*;

public class SeatChart implements TicketConstants 
{
    private static final String SEAT_OPEN = "O";
    private static final String SEAT_TAKEN = "X";

    private LinkedList[] seats;
    private int numSections;
    private int rowsPerSect;
    private int colsPerSect;
    private int openSeats;
     /*
     * Creates the SeatChart object
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
        openSeats = getNumSeats();
        arrangeSeats();
    }

    /** Default constructor
     */
    public SeatChart()
    {
        this.numSections = NUM_SECTIONS;
        this.rowsPerSect = ROWS_PER_SECTION;
        this.colsPerSect = COLS_PER_SECTION;
        seats = new LinkedList[rowsPerSect * numSections];
        openSeats = getNumSeats();
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
     * Gets the number of open seats
     * @return  the total number of open seats
     */
    public int getOpenSeats()
    {
        return openSeats;
    }

    /*
     * Gets the number of taken seats
     * @return  the total number of taken seats
     */
    public int getTakenSeats()
    {
        return (getNumSeats() - openSeats);
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
    }
    
    /*
     * Sets the number of the sections
     * @param num  the new value
     * /
    public void setNumSections(int num)
    {
        numSections = num;
    }

    /*
     * Sets the number of rows per section
     * @param num  the new value
     */
    public void setRowsPerSections(int num)
    {
        rowsPerSect = num;
    }
    
    /*
     * Sets the number of columns
     * @param num  the new value
     */
    public void setColsPerSection (int num)
    {
        colsPerSect = num;
    }

    /*
     * sets the size of the seat array
     * @param num  the new size if seats[] 
     */
    public void setSeats (int num)
    {
        seats = new LinkedList[num];
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
                seats[i].add(SEAT_OPEN);
        }
    }
    
    /*
     * Reserves seat[s]
     * @param numSeats The number of seats to reserve
     * @param section The section you wish to be seated
     */
    public void reserveSeats (int numSeats, int section) 
    {
        int ct = 0;
        LinkedList[] sect = getSectionSeats (section);
        int numRows = generateNumRows (numSeats);

        //for (int i = row - 1; i < (row-1) + numRows; i++)
        for (int i = 0; i < sect.length; i++)
        {
            for (int j = 0; j < sect[i].size() && ct != numSeats; j++)
            {
                if (sect[i].get(j) == SEAT_OPEN)
                    sect[i].set(j, SEAT_TAKEN);
                ct++;
            }
        }
        openSeats -= numSeats;
    }
    
    /*
     * Counts the number of rows needed to seat more than what one row can hold
     * @ param  numSeats the number of seats to reserve
     * @ return  the number of rows needed to seat a given number 
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

   /*
    * Prints string representation of the venue seats
    * @return The string representation of the venue seats
    */
    public String toString()
    {
        int sectCount = numSections;
        String result = "";
        result += "\nSection " + sectCount + ": \n";
        sectCount -= 1;

        for (int i = seats.length; i > 0 ; i--)
        {
            if (i == rowsPerSect * (sectCount))
            {
                result += "\nSection " + sectCount + ": \n";
                sectCount--;
            }
            result += seats[i-1] + "\n";
        }
        result += "\n----------------";
        result += "\n|     Stage    |";
        result += "\n----------------\n";

        return result;
    }
}

// Montrell Wiley, David Glenewinkel, Sarah Anderson
// CSCI 434, Project #2
// 4/1/19

import java.util.*;
import java.io.*;

public class Event implements Comparable<Event>, Serializable
{
    private String eventTitle;
    private String date;
    private String startTime;
    private String endTime;
    
    /** Creates the Event object
     */
    public Event(String eventTitle, String date, 
        String startTime, String endTime)
    {
        this.eventTitle = eventTitle;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /*
     * gets the title of the event 
     * @return  the event title
     */
    public String getEventTitle()
    {
        return eventTitle;
    }

    /*
     * gets the date when the event will occur
     * @return  the date
     */
    public String getDate()
    {
        return date;
    }

    /*
     * gets the time when the event starts
     * @return  the start time
     */
    public String getStartTime()
    {
        return startTime;
    }

    /*
     * gets the time when the event is over
     * @return  the end time
     */
    public String getEndTime()
    {
        return endTime;
    }

    /*
     * sets the title of the event
     * @param title  the new title
     */
    public void setEventTitle(String title)
    {
        eventTitle = title;
    }

    /*
     * sets the date when the event will occue
     * @param newDate  the new date
     */
    public void setDate(String newDate)
    {
        date = newDate;
    }

    /*
     * sets the time when the event starts
     * @param time  the new start time
     */
    public void setStartTime(String time)
    {
        startTime = time;
    }
    
    /*
     * sets the time when the event is over
     * @param time  the new end time
     */
    public void setEndTime(String time)
    {
        endTime = time;
    }

    /*
     * Reserves seat[s]
     * @param numSeats The number of seats to reserve
     * @param row The row you wish to be seated
    public void reserveSeats (int numSeats, int row) 
    {
        int numRows = generateNumRows (numSeats);
        int ct = 0;
        for (int i = row - 1; i < (row-1) + numRows; i++)
        {
            for (int j = 0; j < totalCols && ct != numSeats; j++)
            {
                if (seats[i].get(j) != SEAT_TAKEN)
                    seats[i].set(j, SEAT_TAKEN);
                ct++;
            }
        }
        openSeats -= numSeats;
    }
     */

    public boolean equals(Object obj)
    {
        Event other;
        boolean result = false;

        if (obj != null && getClass() == obj.getClass())
        {
            other = (Event) obj;
            result = eventTitle.equals(other.eventTitle)
                && date.equals(other.date)
                && startTime.equals(other.startTime)
                && endTime.equals(other.endTime);
        }

        return result;
    }

    public int compareTo(Event other)
    {
        int result = eventTitle.compareTo(other.eventTitle);

        // In the chance that an artist has multiple events
        // on different dates, this will help compare and order
        // by date instead.
        if (result == 0)
            result = date.compareTo(other.date);

        return result;
    }

    public String toString()
    {
        return (eventTitle + " - " + date +
            " - " + startTime + "-" + endTime);
    }       
}

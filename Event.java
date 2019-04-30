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
    private double facePrice;
    private double sectionFee;
    private SeatChart seating;
    
    /** Creates the Event object
     */
    public Event(String eventTitle, String date, 
        String startTime, String endTime, double facePrice)
    {
        this.eventTitle = eventTitle;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.facePrice = facePrice;
        seating = new SeatChart();
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
     * gets the face value of the ticket 
     * @return  face price of the ticket
     */
    public double getFacePrice()
    {
        return facePrice;
    }

    public double getSectionFee(int section)
    {
        return (section * 0.10);
    }
    

    /*
     * gets the seating chart
     * @return  the seating chart
     */
    public SeatChart getSeatChart()
    {
        return seating;
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
     * sets the face price of the ticket
     * @param newPrice  the new price of the ticket
     */
    public void setFacePrice (double newPrice)
    {
        facePrice = newPrice;
    }

    /*
     * buys the tickets
     * @param numTickets 
     * @param section   section number
     * @return  totalPrice of a ticket
     */
    public void buyTickets(int numTickets, int section)
    {
        if (seating.getOpenSeats() < numTickets)
            throw new IllegalArgumentException("Error: We do not have " + 
                numTickets + " seats available");

        seating.reserveSeats(numTickets, section);
    }

    /*
     * Calculates the total price of the tickets
     * @param section   section number
     * @return  totalPrice of a ticket
     */
    public double caclulateTicketPrice(int section)
    {
        return facePrice + (facePrice * getSectionFee(section));
    }

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

    /*
     * Creates a string representation for the event
     * @return  string representation 
     */
    public String toString()
    {
        return ("($" + facePrice + ") " + eventTitle + " - " + date +
            " - " + startTime + "-" + endTime);
    }       
}

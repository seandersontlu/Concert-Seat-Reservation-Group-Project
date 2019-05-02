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
     * @param eventTitle The inputted title of the event
     * @param date The inputted date of the event
     * @param startTime The inputted starting time of the event
     * @param endTime The inputted ending time of the event
     * @param facePrice The inputted face value of one ticket for the event
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

     /** Gets the title of the event 
     * @return  the event title
     */
    public String getEventTitle()
    {
        return eventTitle;
    }

     /** Gets the date when the event will occur
     * @return  the date
     */
    public String getDate()
    {
        return date;
    }

     /** Gets the time when the event starts
     * @return  the start time
     */
    public String getStartTime()
    {
        return startTime;
    }

     /** Gets the time when the event is over
     * @return  the end time
     */
    public String getEndTime()
    {
        return endTime;
    }
    
     /** Gets the face value of the ticket 
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
    
     /** Gets the seating chart
     * @return  the seating chart
     */
    public SeatChart getSeatChart()
    {
        return seating;
    }

     /** Sets the title of the event
     * @param title  the new title
     */
    public void setEventTitle(String title)
    {
        eventTitle = title;
    }

     /** Sets the date when the event will occue
     * @param newDate  the new date
     */
    public void setDate(String newDate)
    {
        date = newDate;
    }

     /** Sets the time when the event starts
     * @param time  the new start time
     */
    public void setStartTime(String time)
    {
        startTime = time;
    }
    
     /** Sets the time when the event is over
     * @param time  the new end time
     */
    public void setEndTime(String time)
    {
        endTime = time;
    }

     /** Sets the face price of the ticket
     * @param newPrice  the new price of the ticket
     */
    public void setFacePrice (double newPrice)
    {
        facePrice = newPrice;
    }

     /** Buys the tickets
     * @param numTickets Inputted number of tickets to be purchaced
     * @param section   Inputted section number
     * @throws IllegalArgumentException Thrown if the inputed number of tickets
     * exceeds the number of tickets available
     */
    public void buyTickets(int numTickets, int section)
    {
        if (seating.getOpenSeats() < numTickets)
            throw new IllegalArgumentException("Error: We do not have " + 
                numTickets + " seats available");

        seating.reserveSeats(numTickets, section);
    }

     /** Calculates the total price of the tickets
     * @param section   section number
     * @return  totalPrice of a ticket
     */
    public double caclulateTicketPrice(int section)
    {
        return facePrice + (facePrice * getSectionFee(section));
    }

    /** Determines if two Event objects are equal.
     * @param obj The other Event object
     * @return result Returns the boolean value that determines if both events
     * are equal to each other or not.
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

    /** Compares two Event objects
     * @param other The other Event object that is being compared to
     * @return result Returns the int value that determines if the first event
     * is greater than, less than, or equal to the other Event object.
     *
     */
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

     /** Creates a string representation for the event
     * @return  string representation 
     */
    public String toString()
    {
        return ("($" + facePrice + ") " + eventTitle + " - " + date +
            " - " + startTime + "-" + endTime);
    }       
}

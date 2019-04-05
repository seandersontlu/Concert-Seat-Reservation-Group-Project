// Montrell Wiley, David Glenewinkel, Sarah Anderson
// CSCI 434, Project #2
// 4/1/19

import java.io.Serializable;

public class Event implements Comparable<Event>, Serializable
{
    private String eventTitle;
    private String date;
    private String startTime;
    private String endTime;

    /**Creates an event object.
     * @param eventTitle
     * @param date
     * @param startTime
     * @param endTime
     */
    public Event(String eventTitle, String date,
                 String startTime, String endTime)
    {
        this.eventTitle = eventTitle;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    
    /** Returns the title of the event
     * @return eventTitle
     */
    public String getEventTitle()
    {
        return eventTitle;
    }
    
    /** Returns the date of the event
     * @return date
     */
    public String getDate()
    {
        return date;
    }

    /** Returns the start time of the event
     * @return startTime
     */
    public String getStartTime()
    {
        return startTime;
    }

    /** Returns the end time of the event. 
     * @return endTime
     */
    public String getEndTime()
    {
        return endTime;
    }

    /** Updates the title of an event
     * @param title
     */
    public void setEventTitle(String title)
    {
        eventTitle = title;
    }

    /** Updates the date of the event
     * @param newDate
     */
    public void setDate(String newDate)
    {
        date = newDate;
    }
    
    /** Updates the start time of the event
     * @param time
     */
    public void setStartTime(String time)
    {
        startTime = time;
    }

    /** Updates the end time of the event.
     * @param time
     */
    public void setEndTime(String time)
    {
        endTime = time;
    }

    /** Determines if two event objects are equal
     * @param obj
     * @return result
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

    /** Compares two event objects
     * @param other
     * @return result
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

    /** Converts event object into string
     * @return String
     */
    public String toString()
    {
        return (eventTitle + " - " + date +
            " - " + startTime + "-" + endTime);
    }       



}




















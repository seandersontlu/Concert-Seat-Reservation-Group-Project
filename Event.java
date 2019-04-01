

public class Event implements Comparable<Event>
{
    private String eventTitle;
    private String date;
    private String startTime;
    private String endTime;

    public Event(String eventTitle, String date,
                 String startTime, String endTime)
    {
        this.eventTitle = eventTitle;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getEventTitle()
    {
        return eventTitle;
    }

    public String getDate()
    {
        return date;
    }

    public String getStartTime()
    {
        return startTime;
    }

    public String getEndTime()
    {
        return endTime;
    }

    public void setEventTitle(String title)
    {
        eventTitle = title;
    }

    public void setDate(String newDate)
    {
        date = newDate;
    }

    public void setStartTime(String time)
    {
        startTime = time;
    }

    public void setEndTime(String time)
    {
        endTime = time;
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
        int result =


}




















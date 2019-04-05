// David, Sarah Anderson, and Montrell 
// CSCI 434  Project #2 Iteration #0
// Submitted: 4/5/2019
// READ_ME.txt

Event Ticket and Seating Application
-------------------------------------

This set of classes form the basic structure of the Event Ticket and Seating
Application, creating the necessary opbjects and methods for basic
functionality.

Event class
    This class creates an Event object and the associated methods.
    Methods within the Event class include:

    public Event(String eventTitle,String date,String startTime, String endTime)
            -- Creates an event object.
    public String getEventTitle()   
            -- Returns the eventTitle.
    public String getDate()         
            -- Returns the date.
    public String getStartTime()    
            -- Returns the startTime.
    public String getEndTime()  
            -- Returns the endTime.
    public void setEventTitle(String title) 
            -- Updates title.
    public void setDate(String newDate) 
            -- Updates date.
    public void setStartTime(String time) 
            -- Updates startTime.
    public void setEndTime(String time) 
            -- Updates endTime.
    public boolean equals(Object obj) 
            -- determines if two event objects are equal.
    public int compareTo(Event other)
            -- compares two event objects.
    public String toString()
            -- returns event object as a string.

EventTreeSet class

    public static void main()
            -- Reads all events from a CSV file and creates serializable
            objects from the treeset and writes to Event.ser.



Venue class
    
    This class creates the Venue object and associated methods.
    Methods within the Venue class include:

    public Venue(String venueName, String address, int numSeats, int[][] seats)
            -- Creates the venue object.
    public String getVenueName()
            -- Returns the venueName.
    public String getAddress()
            -- Returns the address.
    public int getRow()
            -- Returns the row.
    public int getCol()
            -- Returns the col.
    public in getNumSeats()
            -- Returns numSeats.
    public int[][] getSeats()
            -- Returns array of seats
    public void setVenueName(String name)
            -- Updates the venueName.
    public void setAddress(String venueAddress)
            -- Updates the address.
    public void setNumSeats(int num)
            -- Updates numSeats.
    public void setSeats(int row, int col)
            -- Arranges seats in a 2D array.
    public int numOpenSeats()
            -- Returns number of open seats.
    public int numTakenSeats()
            -- Returns number of taken seats.
    public boolean isSeatTaken(int row, int col)
            -- Determines if a seat is taken.
    public boolean isSeatOpen(int row, int col)
            -- Determines if a seat is open.

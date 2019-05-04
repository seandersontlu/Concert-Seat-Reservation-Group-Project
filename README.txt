// David, Sarah Anderson, and Montrell 
// CSCI 434  Project #2 Iteration #4
// Submitted: 4/28/2019
// READ_ME.txt

Event Ticket and Seating Application
-------------------------------------

This set of classes form the basic structure of the Event Ticket and Seating
Application, creating the necessary opbjects and methods for basic
functionality.

AdminController.java
    This program allows the user to create events and venues, putting them into
    a serializable file for use by other programs. Note that serializable files
    must be made before programs like the server, client, and GUI can work.



Event.java
    This class creates an Event object and the associated methods.
    Methods within the Event class include:

    public Event(String eventTitle,String date,String startTime, 
        String endTime, double facePrice)
            -- Creates an event object.
    public String getEventTitle()   
            -- Returns the eventTitle.
    public String getDate()         
            -- Returns the date.
    public String getStartTime()    
            -- Returns the startTime.
    public String getEndTime()  
            -- Returns the endTime.
    public double getFacePrice()
            -- Returns the facePrice.
    public double getSectionFee(int section)
            -- Returns the price for the section
    public seatChart getSetChart()
            -- Returns a SeatChart object for the event.
    public void setEventTitle(String title) 
            -- Updates title.
    public void setDate(String newDate) 
            -- Updates date.
    public void setStartTime(String time) 
            -- Updates startTime.
    public void setEndTime(String time) 
            -- Updates endTime.
    public void setFacePrice(double newPrice)
            -- Updates facePrice
    public void buyTickets(int numTickets, int section)
            -- Buys the number of tickets in a given section, returns
            IllegalArgumentException if not enough seats are available.
    public doubel calculateTicketPrice(int section)
            -- Calculates the price of a ticket in a given section.
    public boolean equals(Object obj) 
            -- determines if two event objects are equal.
    public int compareTo(Event other)
            -- compares two event objects.
    public String toString()
            -- returns event object as a string.

EventTest.java
    This tests the Event class to make sure everything is working properly.

README.txt
    What you are reading right now. This lists all of the information about
    this project and the classes and programs that are used in it.

SeatChart.java
    This class creates a seating chart.

    public SeatChart(int numSections, int rowsPerSect, int colsPerSect)
            -- Constructs the SeatChart object.
    public SeatChart()
            -- Default constructor with no arguments.
    public LinkedList[] getSeats()
            -- Returns an array representing seats.
    public int getNumSections()
            -- Returns the number of sections.
    public int getRowsPerSect()
            -- Returns the number of rows per section.
    public int getColsPerSect()
            -- Returns the number of columns per section.
    public int getTotalRows()
            -- Returns the total number of rows.
    public int getTotalCols()
            -- Returns the total number of columns.
    public int getNumSeats()
            -- Returns the total number of seats.
    public int getOpenSeats()
            -- Returns the total number of open seats.
    public int getTakenSeats()
            -- Returns the total number of reserved seats.
    public LinkedList[] getSectionSeats(int section)
            -- Returns the seats in a given section and throws an
            IllegalArgumentException if the section does not exist.
    public void setNumSection(int num)
            -- Updates the number of sections.
    public void setRowsPerSection(int num)
            -- Updates the number of rows in a section.
    public void setColsPerSection(int num)
            -- Updates the number of columns in a section.
    public void setSeats(int num)
            -- Updtes the size of the seat array.
    public void resetSeats()
            -- Sets all seats to open
    public void arrangeSeats()
            -- Assignes values to the array of LinkedLists.
    public void reserveSeats(int numSeats, int section)
            -- Reserves seats.
    private int generateNumRows(int numSeats)
            -- Counts the number of rows needed to seat more than what one row
            can hold.
    public String toSting()
            -- Converts the venue seats into a string for printing.

TicketClient.java
    This program is a text based client that allows the user to reserve seats
    at a given event to reserve seats and purchase tickets. Note this code does
    not currrently work due to code the no longer exist (consider scrapping).

TicketConstants.java
    Contains usable constants for the program. 
    
    public static final int PORT = 35225
            -- Port number for client and server applications.
    public final String END_OF_FILE = "--END_OF_FILE--";
            -- Text that denotes the end of a file.
    public final int NUM_SECTIONS = 6;
            -- Constant number of sections.
    public final int ROWS_PER_SECTION = 5;
            -- Constant number of rows 
    public final int COLS_PER_SECTION = 5;
            -- Constant number of columns

TicketGUI.java
    This program is an old version of the rough GUI created with NetBeans, 
    consider scrapping.

TicketGui2.java
    Tis program is a better version of the GUI, cleaned up and altered. 
    USE THIS VERSION OF THE GUI.

TicketServer.java
    This program in the server for both the GUI and text based client. It
    establishes a connection between client and server and creates a task for
    the client.

TicketTask.java
    This program is the task associeted with the server, it does the actual
    processing of reservations and sending of information to the client.

UML Diagram.pptx
    This is a UML diagram of the classes in this program. It is an
    organizational tool and does not effect the overall functionality.

Venue class
    
    This class creates the Venue object and associated methods.
    Methods within the Venue class include:

    public Venue(String venueName, String address)
            -- Creates the venue object.
    public String getVenueName()
            -- Returns the venueName.
    public String getAddress()
            -- Returns the address.
    public void setVenueName(String name)
            -- Updates the venueName.
    public void setAddress(String venueAddress)
            -- Updates the address.
    public TreeSet getEventSet()
            -- Returns the TreeSet of events
    public void createTreeSet()
            -- Creates the tree set of events associated with a venue.
    public int compareTo(Venue other)
            -- compares two venue objects
    public String toString()
            -- Returns the venue as a string.



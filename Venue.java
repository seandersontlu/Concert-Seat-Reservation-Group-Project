// Venue.java

public class Venue
{
    private static final int SEAT_OPEN = 0;
    private static final int SEAT_TAKEN = 1;

    private String venueName;
    private String address;
    private int row;
    private int col;
    private int numSeats;
    private int[][] seats;

    public Venue(String venueName, String address, int numSeats, int[][] seats)
    {
        this.venueName = venueName;
        this.address = address;
        this.numSeats = numSeats;
        this.seats = seats;
    }

    /** Gets name of the venue
     */
    public String getVenueName()
    {
        return venueName;
    }
    
    /** Gets address of the venue
     */
    public String getAddress()
    {
        return address;
    }

    /** Gets the seat row
     */
    public int getRow()
    {
        return row;
    }

    /** Gets the seat column 
     */
    public int getCol()
    {
        return col;
    }

    /** Gets the number seats
     */
    public int getNumSeats()
    {
        return numSeats;
    }
    
    /** Gets the 2d array representing the seats
     */
    public int[][] getSeats()
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

    /** Sets the number of the seats
     */
    public void setNumSeats(int num)
    {
        numSeats = num;
    }

    /** Sets the 2d array representing the seats
     */
    public void setSeats(int row, int col)
    {
        seats = new int[row][col];
    }
    
    /**
     * Counts the number of open seats
     * @return number of open seats
     */
    public int numOpenSeats()
    {
        int num = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                if (seats[row][col] == SEAT_OPEN)
                    num++;
        return num;
    }

    /**
     * Counts the number of taken seats
     * @return number of taken seats
     */
    public int numTakenSeats()
    {
        int num = 0;
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                if (seats[row][col] == SEAT_TAKEN)
                    num++;
        return num;
    }

    /**
     * Checks if a seat is taken
     * @return determines if seat is taken
     */
    public boolean isSeatTaken(int row, int col)
    {
        boolean result = false;
        if (seats[row][col] == SEAT_TAKEN)
            result = true;
        else
            result = false;
        return result;
    }
    
    /**
     * Checks if a seat is open
     * @return determines if seat is open
     */
    public boolean isSeatOpen(int row, int col)
    {
        boolean result = false;
        if (seats[row][col] == SEAT_OPEN)
            result = true;
        else
            result = false;
        return result;
    }
}

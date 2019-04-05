

import static org.junit.Assert.*;
import org.junit.*;

public class VenueTest
{
    private Venue venue;
    private int[][] seats = new int[20][25];

    @BeforeClass
    public static void testVenue()
    {
        System.out.println("This program performs unit testing.");
        System.out.println("for the Venue class.");
    }
/**
    @Test
    public void testConstructor()
    {
        venue = new Venue("Paper Tiger", "800 W Street", seats);
    }
*/
   @Test
   public void testAccessors()
   {
        venue = new Venue("Paper Tiger", "800 W Street", seats);
        assertEquals("Venue Name = Paper Tiger", "Paper Tiger",
            venue.getVenueName());
        assertEquals("Address = 800 W Street", "800 W Street",
            venue.getAddress());
        assertEquals("Num Rows = 20", 20,
            venue.getNumRows());
        assertEquals("Num Cols = 25", 25,
            venue.getNumCols());
        assertEquals("Num Seats = 25*20", 25*20,
            venue.getNumSeats());
        int[][] testSeats = seats;
        assertEquals("Array of seats", testSeats,
            venue.getSeats());
   }

   @Test
   public void testMutators()
   {
        venue = new Venue("Paper Tiger", "800 W Street", seats);
        venue.setVenueName("Gold Eagle");
        assertEquals("setVenueName(Gold Eagle)", "Gold Eagle",
            venue.getVenueName());
        venue.setAddress("210 N Ave");
        assertEquals("setAddress(210 N Ave)", "210 N Ave",
            venue.getAddress());
        venue.setNumSeats(30);
        assertEquals("setNumSeats(30)", 30,
            venue.getNumSeats());
        int[][] testSeats = new int[10][15];
        venue.setSeats(10,15);
        assertEquals("setSeats(10,15)", testSeats,
            venue.getSeats());
   }

   @Test
   public void testReserveSeat()
   {
        venue = new Venue("Paper Tiger", "800 W Street", seats);
        venue.reserveSeat(5,3);
        assertTrue("Seat 5,3 is taken", venue.isSeatTaken(5,3));
        assertFalse("Seat 5,3 is not open", venue.isSeatOpen(5,3));
        venue.resetSeats();
        assertFalse("Seat 5,3 is not taken", venue.isSeatTaken(5,3));
        assertTrue("Seat 5,3 is open", venue.isSeatOpen(5,3));
    }

       

}



// David, Sarah Anderson, and Montrell 
// CSCI 434  Project #2 Iteration #1
// Submitted: 4/5/2019
// EventTest.java


import static org.junit.Assert.*;
import org.junit.*;

public class EventTest
{
    private Event event;

    @BeforeClass
    public static void testEvent()
    {
        System.out.println("This program performs unit testing");
        System.out.println("for the Event class.");
    }

    @Test
    public void testConstructor()
    {
        event = new Event("Beyonce", "6/2/19", "9:00pm", "11:00pm");
        assertEquals("Event = Beyonce - 6/2/19 - 9:00pm-11:00pm",
                    "Beyonce - 6/2/19 - 9:00pm-11:00pm", event.toString());
        event = new Event("Kanye West", "12/2/29", "3:00pm", "4:30pm");
        assertEquals("Event = Kanye West - 12/2/29 - 3:00pm-4:30pm",
                    "Kanye West - 12/2/29 - 3:00pm-4:30pm", event.toString());
        event = new Event("Lil Uzi Vert", "1/23/22", "10:00am", "12:00pm");
        assertEquals("Event = Lil Uzi Vert - 1/23/22 - 10:00am-12:00pm",
                    "Lil Uzi Vert - 1/23/22 - 10:00am-12:00pm", event.toString());
    }

    @Test
    public void testAccessors()
    {
        event = new Event("Beyonce", "6/2/19", "9:00pm", "11:00pm");
        assertEquals("Event Title = Beyonce", "Beyonce",
            event.getEventTitle());
        assertEquals("Date = 6/2/19", "6/2/19",
            event.getDate());
        assertEquals("Start Time = 9:00pm", "9:00pm",
            event.getStartTime());
        assertEquals("End Time = 11:00pm", "11:00pm",
            event.getEndTime());
    }

    @Test
    public void testMutators()
    {
        event = new Event("Beyonce", "6/2/19", "9:00pm", "11:00pm");
        event.setEventTitle("Conway Twitty");
        assertEquals("setEventTitle(Conway Twitty)",
                    "Conway Twitty - 6/2/19 - 9:00pm-11:00pm",
                    event.toString());
        event.setDate("9/4/19");
        assertEquals("setDate(9/4/19)",
                    "Conway Twitty - 9/4/19 - 9:00pm-11:00pm",
                    event.toString());
        event.setStartTime("5:00pm");
        assertEquals("setStartTime(5:00pm)",
                    "Conway Twitty - 9/4/19 - 5:00pm-11:00pm",
                    event.toString());
        event.setEndTime("8:30pm");
        assertEquals("setEndTime(8:30pm)",
                    "Conway Twitty - 9/4/19 - 5:00pm-8:30pm",
                    event.toString());
    }

    @Test
    public void testEquals()
    {
        Event event1 = new Event("Beyonce", "6/2/19", "9:00pm", "11:00pm");
        Event event2 = new Event("Beyonce", "6/2/19", "9:00pm", "11:00pm");
        assertNotSame("event1 and event2 are separate objects",
            event1, event2);
        assertEquals("Beyonce - 6/2/19 - 9:00pm-11:00pm ==" + 
             "Beyonce - 6/2/19 - 9:00pm-11:00pm", event1, event2);
        event2.setEventTitle("JID");
        assertNotEquals("Beyonce - 6/2/19 - 9:00pm-11:00pm !=" +
             "JID - 6/2/19 - 9:00pm-11:00pm", event1, event2);
    }

    @Test
    public void testCompareTo()
    {
        Event event1 = new Event("Beyonce", "6/2/19", "9:00pm", "11:00pm");
        Event event2 = new Event("Queen", "2/23/19", "4:00pm", "7:00pm");
        assertEquals("event1 compareTo event2", -15, event1.compareTo(event2));

        event2.setEventTitle("Beyonce");
        assertEquals("event1 compareTo event2", 4, event1.compareTo(event2));
    }


}


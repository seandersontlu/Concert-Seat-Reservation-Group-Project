

import java.util.*;
import java.io.*;

public class SerialRead
{
    public static void main(String[] args)
    {

        System.out.println("Reading event objects from Event.ser...\n");

        try (ObjectInputStream inFile
            = new ObjectInputStream(new FileInputStream("Event.ser")))
        {
            TreeSet events = (TreeSet) inFile.readObject();
            System.out.println("Events:  " + events);
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Could not open file \"Event.ser\"" +
                "for reading");
        }
        catch (EOFException e)
        {
            System.out.println("\nReached the end of events stored.");
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }
}

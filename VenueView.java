// VenueView.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VenueView extends JPanel
{
    private SeatChart seating;
    
    public VenueView()
    {
        seating = new SeatChart();
        
        setLayout(new GridLayout(seating.getRowsPerSect(),
            seating.getColsPerSect()));
        
        JButton[] buttonArr = new JButton[seating.getSeatsPerSection()];
        for (int i = 0; i < buttonArr.length; i++)
        {
            buttonArr[i] = new JButton();
            buttonArr[i].setBackground(Color.WHITE);
            add(buttonArr[i]);
        }
    }   
}

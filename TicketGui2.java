// ticketGui.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicketGui2 extends JFrame implements TicketConstants
{
    // Initialize values
    SeatChart seating;

    public static void main (String[] args)
    {
        TicketGui2 window = new TicketGui2();
        window.setVisible(true);
    }
    
    @SuppressWarnings("unChecked")
    public TicketGui2()
    {
        super ("Shop Concert Tickets");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
       
        seating = new SeatChart();

        JLabel titleLabel = new JLabel("Welecome to the ticket reservation system");
        JLabel instruction1 = new JLabel("Please select the event and the venue");
        JLabel instruction2 = new JLabel("Please select a section and enter " +
            "the number of tickets");
        JLabel resultLabel = new JLabel();
        JButton submitButton = new JButton("Submit");
        
        JPanel step1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel step2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttonPanel = new JPanel();
        
        JComboBox venueComboBox = new JComboBox();
        venueComboBox.addItem("Select Venue");
        for (int i = 0; i < 4; i++)
            venueComboBox.addItem("Item " + (i+1));

        JComboBox eventComboBox = new JComboBox();
        eventComboBox.addItem("Select Event");
        for (int i = 0; i < 4; i++)
            eventComboBox.addItem("Item " + (i+1));
        
        JComboBox sectionComboBox = new JComboBox();
        sectionComboBox.addItem("Select Section");
        for (int i = 0; i < 4; i++)
            sectionComboBox.addItem("Item " + (i+1));
        
        JTextField numSeatsTextField = new JTextField("Enter number of seats");
        
        JPanel[] venuePanel = new JPanel[seating.getNumSections()];
        for (int i = 0; i < venuePanel.length; i++)
            venuePanel[i] = new VenueView();
        
        setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(submitButton);
        step1Panel.add(venueComboBox);
        step1Panel.add(eventComboBox);
        step2Panel.add(sectionComboBox);
        step2Panel.add(numSeatsTextField);

        JPanel topPanel = new JPanel(new GridLayout(6, 1));
        topPanel.add(titleLabel);
        topPanel.add(instruction1);
        topPanel.add(step1Panel);
        topPanel.add(instruction2);
        topPanel.add(step2Panel);
        topPanel.add(buttonPanel);
        

        JPanel venueGrid = new JPanel(new GridLayout(seating.getRowsPerSect(),
            seating.getColsPerSect()));
        for (int i = 0; i < venuePanel.length; i++)
            venueGrid.add(venuePanel[i]);

        add(topPanel, BorderLayout.NORTH);
        add(venueGrid, BorderLayout.SOUTH);
    }
    
    /* VenueComboBox listener 
    private class VenueComboBoxListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt) 
        {
            VenueComboBoxActionPerformed(evt);
        }
    }
    */

    /* EventComboBox listener
    private class SectionComboBoxListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt) 
        {
            SectionsComboBoxActionPerformed(evt);
        }
    }
    */

    /* NumberOfSeats Listener
    private class NumSeatsTextFieldListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt) 
        {
            NumSeatsTextFieldActionPerformed(evt);
        }
    }
    */

    /*
        resultLabel.setText("No data to process");

        SubmitButton.setText("Submit");
        SubmitButton.addActionListener(new java.awt.event.ActionListener() 
        {
            public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                SubmitButtonActionPerformed(evt);
            }
        }
    */
}

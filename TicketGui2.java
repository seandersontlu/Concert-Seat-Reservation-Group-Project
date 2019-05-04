// ticketGui.java

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicketGui2 extends JFrame implements TicketConstants
{
    // Initialize values
    SeatChart seating;

    JLabel eventLabel; 
    JLabel venueLabel; 
    JLabel sectionLabel; 
    JLabel numTicketsLabel; 
    JLabel resultLabel;

    public static void main (String[] args)
    {
        TicketGui2 window = new TicketGui2();
        window.setVisible(true);
    }
    
    @SuppressWarnings("unChecked")
    public TicketGui2()
    {
        super ("Shop Concert Tickets");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
       
        seating = new SeatChart();
        
        // Labels
        JLabel titleLabel = new JLabel("Welecome to the ticket reservation system");
        JLabel instruction1 = new JLabel("Please select the event and the venue");
        JLabel instruction2 = new JLabel("Please select a section and enter " +
            "the number of tickets");
        resultLabel = new JLabel();
        
        // Buttons
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitListener());
        
        // Panels
        JPanel step1Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel step2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel buttonPanel = new JPanel();
        JPanel venuePanel = new JPanel(new
            GridLayout(seating.getRowsPerSect(),seating.getColsPerSect()));
        
        JPanel[] sectionPanel = new JPanel[seating.getNumSections()];
        for (int i = 0; i < sectionPanel.length; i++)
        {
            sectionPanel[i] = sectionSeatView();
            sectionPanel[i].setBorder(BorderFactory.createLineBorder(Color.BLACK,7));
        }

        JPanel topPanel = new JPanel(new GridLayout(6, 1));
        
        // ComboBoxes
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
        
        // TextField
        JTextField numSeatsTextField = new JTextField("Enter number of seats");
        
        // add to panels
        setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(submitButton);
        step1Panel.add(venueComboBox);
        step1Panel.add(eventComboBox);
        step2Panel.add(sectionComboBox);
        step2Panel.add(numSeatsTextField);

        setLayout(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(titleLabel);
        topPanel.add(instruction1);
        topPanel.add(step1Panel);
        topPanel.add(instruction2);
        topPanel.add(step2Panel);
        topPanel.add(buttonPanel);
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // add to frame
        add(topPanel, BorderLayout.NORTH);
        for (int i = 0; i < sectionPanel.length; i++)
            venuePanel.add(sectionPanel[i]);
        add(venuePanel, BorderLayout.SOUTH); 
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

    private class SubmitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt) 
        {
            //SubmitButtonActionPerformed(evt);
            //resultLabel.setText("No data to process");
        }
    }

    private JPanel sectionSeatView()
    {
        JPanel venueGrid = new JPanel(new GridLayout(seating.getRowsPerSect(),
            seating.getColsPerSect()));
        
        JButton[] buttonArr = new JButton[seating.getSeatsPerSection()];
        for (int i = 0; i < buttonArr.length; i++)
        {
            buttonArr[i] = new JButton();
            buttonArr[i].setBackground(Color.WHITE);
            venueGrid.add(buttonArr[i]);
        }
        return venueGrid;
    }
}

// Montrell Wiley, David Glenewinkel, Sarah Anderson
// CSCI 434, Project #2
// TicketClientGUI.java

/** GUI based client-server for ticketing system.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TicketClientGUI extends JFrame implements TicketConstants
{
    // Initialize values
    private final int TEXT_BOX_WIDTH = 100;
    private final int TEXT_BOX_HEIGHT = 20;

    private SeatChart seating;
    private PrintWriter outToServer;
    private Scanner inFromServer;
    private Socket clientToServer;

    private JPanel[] sectionPanel;
    private JLabel resultLabel;
    private JFormattedTextField numSeatsTextField;

    private String chosenEvent = "";
    private int chosenSection;
    private int chosenTickets;

    public static void main (String[] args)
    {
        TicketClientGUI window = new TicketClientGUI();
        window.setVisible(true);
    }
    
    @SuppressWarnings("unChecked")
    public TicketClientGUI()
    {
        super ("Shop Concert Tickets");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
       
        // Setup connection to server
        try
        {
            clientToServer = new Socket("tluprog", PORT);
            outToServer = new PrintWriter(clientToServer.getOutputStream());
            inFromServer = new Scanner(clientToServer.getInputStream());
        }
        catch (IOException e)
        {
            System.err.println(e);
        }

        // Labels
        JLabel titleLabel = new JLabel("Welcome to the ticket reservation system");
        JLabel instruction1 = new JLabel("Please select an event");
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
        
        sectionPanel = new JPanel[seating.getNumSections()];
        for (int i = 0; i < sectionPanel.length; i++)
        {
            sectionPanel[i] = sectionSeatView();
            sectionPanel[i].setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                    Color.BLACK, 2), "Section: " + (i+1)));
        }

        JPanel topPanel = new JPanel(new GridLayout(6, 1));
        
        // ComboBoxes
        JComboBox eventComboBox = new JComboBox();
        eventComboBox.addItem("Select Event");
        String line = "";
        do
        {
            line = inFromServer.nextLine();
            eventComboBox.addItem(line);
        } while (!line.equals(END_OF_FILE));
        eventComboBox.addActionListener(new EventComboBoxListener());

        JComboBox sectionComboBox = new JComboBox();
        sectionComboBox.addItem("Select Section");
        for (int i = 0; i < seating.getNumSections(); i++)
            sectionComboBox.addItem(i+1);
        sectionComboBox.addActionListener(new SectionComboBoxListener());
        
        // TextField
        NumberFormat integerFormat = NumberFormat.getIntegerInstance();
        NumberFormatter numberFormatter = new NumberFormatter(integerFormat);
        numberFormatter.setAllowsInvalid(false);
        numberFormatter.setMinimum(1);
        numberFormatter.setMaximum(seating.getSeatsPerSection());

        numSeatsTextField = new JFormattedTextField(numberFormatter);
        numSeatsTextField.setPreferredSize(new Dimension(
                                    TEXT_BOX_WIDTH,TEXT_BOX_HEIGHT));
        
        // add to panels
        setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(submitButton);
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
        add(resultLabel);
    }
    
    /** EventComboBox listener 
     */
    private class EventComboBoxListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) 
        {
            JComboBox cb = (JComboBox) event.getSource();
            chosenEvent = (String) cb.getSelectedItem();
        }
    }

    /** SectionComboBox listener
     */
    private class SectionComboBoxListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) 
        {
            JComboBox cb = (JComboBox) event.getSource();
            chosenSection = (int) cb.getSelectedItem();

            sectionPanel[chosenSection - 1].setBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(
                    Color.RED, 2), "Section: " + chosenSection));
        }
    }

    /** SubmitButton listener
     */
    private class SubmitListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event) 
        {
            String result = numSeatsTextField.getText();
            chosenTickets = Integer.parseInt(result);
            resultLabel.setText("Chose " + chosenTickets + " tickets");
            outToServer.println(chosenEvent);
            outToServer.flush();
            outToServer.println(chosenSection);
            outToServer.flush();
            outToServer.println(chosenTickets);
            outToServer.flush();
        }
    }

    /*
     * Creates a grid for one section
     * @return  grid panel for one section
     */
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

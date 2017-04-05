package com.Richard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

/**
 * Created by su7163if on 3/29/2017.
 */
public class guiTicket extends JFrame{
    private JTextField txtName;
    private JTextField txtDescription;
    private JComboBox cbxPriority;
    private JList JlstTicket;
    private JButton btnSubmit;
    private JPanel rootPanel;
    private JButton btnResolve;
    private JList JlstResolved;
    private JButton btnSaveQuit;

    DefaultListModel<String> ticketListModel;
    DefaultListModel<String> resolvedTicketModel;
    ticketWriterFile ticketsWrite;
    ticketWriterFile resolvedTicketsWrite;
    ticketReaderFile activeReader;
    ticketReaderFile resolvedReader;

    protected guiTicket() {

        setContentPane(rootPanel);
        rootPanel.setSize(1000, 1000);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        //Creating two file writers for active and resolved tickets.
        ticketsWrite = new ticketWriterFile("activeTickets.txt");
        resolvedTicketsWrite = new ticketWriterFile("resolvedTickets.txt");



        //configureButtons and configureComboBox methods.
        configureButtons();
        configureComboBox();

        //new default list model for JListTicket.
        ticketListModel = new DefaultListModel<String>();
        JlstTicket.setModel(ticketListModel);
        JlstTicket.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        resolvedTicketModel = new DefaultListModel<String>();
        JlstResolved.setModel(resolvedTicketModel);

        //Creates 2 buffered readers for the active and resolved ticket lists.
        activeReader = new ticketReaderFile("activeTickets.txt");
        resolvedReader = new ticketReaderFile("resolvedTickets.txt");
        activeReader.createReaderFileTicket();
        activeReader.readAllLines(ticketListModel);

        resolvedReader.createReaderFileTicket();
        //Currently takes the top ticket in the resolved list box and sets ticket ID based off of that.
        //TODO adjust newTicketID method to compare all available ticket Id's
        resolvedReader.newTicketID();
        //reads all of the lines in the appropriate file and puts them into the list box.
        resolvedReader.readAllLines(resolvedTicketModel);









    }

    private void configureComboBox() {
        //Makes the numbers 1-5 available for selection in combobox.
        for(int x = 1 ; x <= 5 ; x++){
            if(x == 1){
                cbxPriority.addItem(x);
            }else {
                cbxPriority.addItem(x);
            }
        }
    }

    protected void configureButtons(){
        //configures buttons.
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Creates new ticket object based on info entered in text boxes.
                String description = txtDescription.getText();
                String name = txtName.getText();
                Date date = new Date();
                int priority = (Integer) cbxPriority.getSelectedItem();
                Ticket newTicket = new Ticket(description, priority, name, date);

                //adds ticket string to list box.
                ticketListModel.addElement(newTicket.toString());

            }
        });
        btnResolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //if nothing is selected in the list box window pops informing user to select a ticket.
                if(JlstTicket.getSelectedIndex() == -1){
                    JOptionPane.showMessageDialog(JlstTicket, "Please select from the list of tickets.");
                    return;
                }else {
                    //else they can enter a resolution. Which is amended at the end of the ticket string
                    String resolution = JOptionPane.showInputDialog("Please enter resolution");
                    if(resolution == null){
                        return;
                    }else {
                        String resTicket = JlstTicket.getSelectedValue().toString();

                        resolvedTicketModel.add(0, resTicket + " \bResolution: " + resolution);
                        ticketListModel.removeElement(resTicket);
                    }

                }
            }
        });

        btnSaveQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //saves everything in both list boxes to a file.
            resolvedTicketsWrite.createWriterFileTicket();
            for(int x = 0; x < resolvedTicketModel.size() ; x++) {
                resolvedTicketsWrite.writeFileTicket(resolvedTicketModel.elementAt(x)+"\n");
            }

            ticketsWrite.createWriterFileTicket();
            for(int x = 0 ; x < ticketListModel.size() ; x++){
                ticketsWrite.writeFileTicket(ticketListModel.elementAt(x)+"\n");
            }

            resolvedTicketsWrite.closeFile();
            ticketsWrite.closeFile();
            System.exit(0);
            }
        });
    }
}

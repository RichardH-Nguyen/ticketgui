package com.Richard;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * Created by NinjaHunter on 4/1/17.
 */
public class ticketReaderFile {
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName; //Ex: "file.txt"
    private BufferedReader newReadFile;

    protected ticketReaderFile(String filename){
        this.fileName = filename;

    }

    protected void createReaderFileTicket(){
        //creates bufferedReader
        try{
            newReadFile = new BufferedReader(new FileReader(fileName));
        }catch (IOException ioe){
            System.out.println("File not found");
            return;
        }
    }

    protected void readAllLines(DefaultListModel ticketListModel){
        //Reads all lines in a file and adds them to the list model provided.
        try {
            String line = newReadFile.readLine();
            while(line != null){
                ticketListModel.addElement(line);
                line = newReadFile.readLine();
            }
        }catch (IOException ioe){
            System.out.println("File not found");
        }
    }

    protected void newTicketID(){
        //Currently sets ticket ID based on the file name provided for reader object.
        try {

            //TODO create another variable to store a new number to compare to the old and get the highest Ticket id.
            String line = newReadFile.readLine();

            String[] splitLine = line.split(" ");
            int id = Integer.parseInt(splitLine[1]) + 1;


            Ticket newTicket = new Ticket("d", 1, "r", new Date());
            newTicket.setStartTicket(id);
        }catch (IOException ioe){
            System.out.println("File not found.");
        }
    }
}

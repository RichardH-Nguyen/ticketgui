package com.Richard;

import javax.swing.*;
import java.io.*;

/**
 * Created by NinjaHunter on 4/1/17.
 */
public class ticketWriterFile {
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName; //Ex: "file.txt"
    private BufferedWriter newFile;


    protected ticketWriterFile(String fileName){
            this.fileName = fileName;

    }

    protected void createWriterFileTicket(){
        //creates a bufferedWriter.
        try {
            newFile = new BufferedWriter(new FileWriter(fileName));
        }catch (IOException ioe){
            System.out.println("File not found");
            return;
        }
    }

    protected void writeFileTicket(String x){
        //writes to file.
        try {
            newFile.write(x);
        }catch (IOException ioe){
            System.out.println("File not found");
            return;
        }
    }


    protected void closeFile(){
        //closes file.
        try{
            newFile.close();
        }catch (IOException ioe){
            System.out.println("File not found");
            return;
        }
    }

}

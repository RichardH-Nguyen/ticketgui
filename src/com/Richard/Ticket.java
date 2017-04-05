package com.Richard;

import java.util.Date;

/**
 * Created by su7163if on 3/29/2017.
 */
public class Ticket {
    private int priority;
    private String reporter; //Person or department that reported issue.
    private String description;
    private Date dateReported;
    private static int staticTicketIDCounter;
    protected int ticketID;

    public Ticket(String desc, int p, String rep, Date date){
        this.description = desc;
        this.priority = p;
        this.reporter = rep;
        this.dateReported = date;
        this.ticketID = staticTicketIDCounter;
        staticTicketIDCounter++;
    }

    public  String toString(){
        return ("ID: " + this.ticketID + " Issue: " + this.description + ", Priority: " + this.priority + ", Reported by: " + this.reporter
                + ", Reported on: " + this.dateReported);
    }

    public int getTicketID(){
        return ticketID;
    }

    public int getPriority(){
        return  priority;
    }

    protected void setStartTicket( int ticketStartID){
        staticTicketIDCounter = ticketStartID;

    }
}

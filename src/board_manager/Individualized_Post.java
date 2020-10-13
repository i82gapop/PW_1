package board_manager;

import java.util.ArrayList;
import java.util.Date;

import contact_manager_package.Contact;

public class Individualized_Post extends Post{
    
    protected ArrayList <Contact> recipients = new ArrayList <Contact>();

    public Individualized_Post(){};

    public Individualized_Post(int identifier, String title, String body, Contact owner, ArrayList<Contact> destinators, Date publication, ArrayList <Contact> recipients){

        super(identifier, title, body, owner, destinators, publication);

        this.recipients = recipients;
    }

    public ArrayList <Contact> getRecipients() {return recipients;}

    public void setRecipients(ArrayList <Contact> recipients){this.recipients = recipients;}
}

package board_manager;

import java.util.ArrayList;
import java.util.Date;

import contact_manager_package.Contact;

public class Individualized_Post extends Post{
    
    protected ArrayList <Contact> recipients = new ArrayList <Contact>();

    public Individualized_Post(int identifier, String title, String body, Contact owner, Date publication){

        super(identifier, title, body, owner, publication);
    }

    public ArrayList <Contact> getRecipients() {return recipients;}

    public void setRecipients(ArrayList <Contact> recipients){this.recipients = recipients;}

    public String toString(){

        if(recipients!=null){

            return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner + "; Publication: " + publication + "; Recipients: " + recipients + "}";
        }

        else{

            return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner + "; Publication: " + publication + "}";
        }
    }
}

package board_manager;

import java.util.ArrayList;

import contact_manager_package.Contact;

public class Individualized_Post extends Post{
    
    protected ArrayList <String> recipients = new ArrayList <String>();

    public Individualized_Post(int identifier, String title, String body, Contact owner, ArrayList <String> recipients){

        super(identifier, title, body, owner);

        this.recipients = recipients;
    }

    public ArrayList <String> getRecipients() {return recipients;}

    public void setRecipients(ArrayList <String> recipients){this.recipients = recipients;}

    public String toString(){

        if(recipients!=null){

            return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner.getFullname() + "; Publication: " + publication + "; Recipients: " + recipients + "}";
        }

        else{

            return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner.getFullname() + "; Publication: " + publication + "}";
        }
    }

    public String toStringFile(){

        return  getType() + "|" + identifier + "|" + title + "|" + body + "|" + owner.getEmail() + "|" + getPublicationString() + "|" + getStatus() + "|" + recipients;
    }
}

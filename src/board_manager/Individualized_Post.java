package board_manager;

import java.util.ArrayList;

import contact_manager_package.Contact;




/**
 * A concrete post in the factory
 * @author Pedro Pablo Garcia Pozo
 * @author Ruben Borrego Canovaca
 * */

public class Individualized_Post extends Post{

    //Attributes
    protected ArrayList <String> recipients = new ArrayList <String>();



    /**
     * Parameterized constructor
     * @param identifier The identifier of the post
     * @param title The title of the post
     * @param body The body of the post
     * @param owner The owner of the post
     * @param recipients an ArrayList with the recipients of an Individualized post
     * */

    public Individualized_Post(int identifier, String title, String body, Contact owner, ArrayList <String> recipients){

        super(identifier, title, body, owner);
        this.recipients = recipients;
    }

    /**
  	 * Returns the recipients of an Individualized post
  	 *
  	 * @return Recipients of the Individualized post
  	 * */

    public ArrayList <String> getRecipients() {return recipients;}


    /**
     * Sets the recipients of an Individualized post
     *
     * @param recipients recipients of the Individualized post
     * */

    public void setRecipients(ArrayList <String> recipients){this.recipients = recipients;}




    /**
  	 * Auxiliar functions to see the info of a post
  	 *
  	 * */

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

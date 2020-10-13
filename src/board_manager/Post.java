package board_manager;

import java.util.Date;
import java.util.ArrayList;
import contact_manager_package.Contact;

public abstract class Post {
    
    protected int identifier;
    protected String title;
    protected String body;
    protected Contact owner;
    protected ArrayList<Contact> destinators;
    protected Date publication;
    //protected status;

    public Post(){};

    public Post(int identifier, String title, String body, Contact owner, ArrayList<Contact> destinators, Date publication){

        this.identifier = identifier;
        this.title = title;
        this.body = body;
        this.owner = owner;
        this.destinators = destinators;
        this.publication = publication;
        
    }

    public int getIdentifier() {return identifier;}
    public String getTitle() {return title;}
    public String getBody() {return body;}
    public Contact getOwner() {return owner;}

    public void setIdentifier(int identifier){this.identifier = identifier;}
    public void setTitle(String title){this.title = title;}
    public void setBody(String body){this.body = body;}
    public void setOwner(Contact owner){this.owner = owner;}

    public String toString(){

        return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner + "}";
    }
}

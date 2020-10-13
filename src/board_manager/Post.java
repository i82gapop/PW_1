package board_manager;

import java.util.Date;
import contact_manager_package.Contact;

public abstract class Post {
    
    protected int identifier;
    protected String title;
    protected String body;
    protected Contact owner;
    protected Date publication;
    protected Status status;

    public Post(){};

    public Post(int identifier, String title, String body, Contact owner, Date publication){

        this.identifier = identifier;
        this.title = title;
        this.body = body;
        this.owner = owner;
        this.publication = new Date();
    }

    public int getIdentifier() {return identifier;}
    public String getTitle() {return title;}
    public String getBody() {return body;}
    public Contact getOwner() {return owner;}
    public Date getPublication() {return publication;}
    public Status getStatus() {return status;}

    public void setIdentifier(int identifier){this.identifier = identifier;}
    public void setTitle(String title){this.title = title;}
    public void setBody(String body){this.body = body;}
    public void setOwner(Contact owner){this.owner = owner;}
    public void setPublication(Date publication){this.publication = publication;}
    public Status setStatus(Status status) {return status;}

    public String toString(){

        return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner + "; Publication: " + publication + "}";
    }
}

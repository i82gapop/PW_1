package board_manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import contact_manager_package.Contact;

public abstract class Post {
    
    protected int identifier;
    protected String title;
    protected String body;
    protected Contact owner;
    protected Date publication;
    protected Status status;
    protected Type type;

    public Post(){};

    public Post(int identifier, String title, String body, Contact owner){

        this.identifier = identifier;
        this.title = title;
        this.body = body;
        this.owner = owner;
        this.publication = new Date();
    }

    public Type getType(){return type;}
    public int getIdentifier() {return identifier;}
    public String getTitle() {return title;}
    public String getBody() {return body;}
    public Contact getOwner() {return owner;}
    public Date getPublication() {return publication;}
    public Status getStatus() {return status;}

    public String getStatusString(){

        return status.name();
    }

    public String getTypeString(){

        return type.name();
    }

    public String getPublicationString(){

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		return format.format(publication);
    }

    public void setIdentifier(int identifier){this.identifier = identifier;}
    public void setTitle(String title){this.title = title;}
    public void setBody(String body){this.body = body;}
    public void setOwner(Contact owner){this.owner = owner;}
    public void setPublication(Date date){this.publication = date;}
    public void setStatus(Status status) {this.status = status;}
    public void setType(Type type){this.type = type;}

    public String toString(){

        return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner.getFullname() + "; Publication: " + publication + "}";
    }

    public String toStringFile(){

        return getType() + "|" + identifier + "|" + title + "|" + body + "|" + owner.getEmail() + "|" + getPublicationString() + "|" + getStatus();
    }
}

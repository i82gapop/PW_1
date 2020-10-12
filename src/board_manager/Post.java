package board_manager;

import contact_manager_package.Contact;

public abstract class Post {
    
    protected int identifier;
    protected String title;
    protected String body;
    protected Contact owner;

    public Post(){};

    public Post(int identifier, String title, String body, Contact owner){

        this.identifier = identifier;
        this.title = title;
        this.body = body;
        this.owner = owner;
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

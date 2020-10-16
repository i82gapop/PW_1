package board_manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import contact_manager_package.Contact;



/**
 * The abstract post in the factory
 * @author Ruben Borrego Canovaca
 * @author Pedro Pablo Garcia Pozo
 * @since 26-09-2020
 * @version 2.0
 * */



public abstract class Post {

  //Attributes

    protected int identifier;
    protected String title;
    protected String body;
    protected Contact owner;
    protected Date publication;
    protected Status status;
    protected Type type;


    /**
  	 * Empty (default) constructor
  	 * */
    public Post(){};


    /**
  	 * Parameterized constructor
  	 * @param identifier The identifier of the post
  	 * @param title The title of the post
  	 * @param body The body of the post
  	 * @param owner The owner of the post
  	 * */

    public Post(int identifier, String title, String body, Contact owner){

        this.identifier = identifier;
        this.title = title;
        this.body = body;
        this.owner = owner;
    }


    /**
  	 * Returns the type of a post
  	 *
  	 * @return Type of the post
  	 * */
    public Type getType(){return type;}

    /**
     * Returns the id of a post
     *
     * @return ID of the post
     * */
    public int getIdentifier() {return identifier;}

    /**
     * Returns the title of a post
     *
     * @return Title of the post
     * */


    public String getTitle() {return title;}

    /**
     * Returns the body of a post
     *
     * @return Body of the post
     * */


    public String getBody() {return body;}


    /**
     * Returns the owner of a post
     *
     * @return Owner of the post
     * */


    public Contact getOwner() {return owner;}


    /**
     * Returns the date of publication of a post
     *
     * @return Date of publication of the post
     * */



    public Date getPublication() {return publication;}

    /**
     * Returns the status of a post
     *
     * @return Status of the post
     * */


    public Status getStatus() {return status;}

    /**
     * Auxiliar function to see the status as a string
     *
     * */

    public String getStatusString(){

        return status.name();
    }


    /**
     * Auxiliar function to see the type as a string
     *
     * */
    public String getTypeString(){

        return type.name();
    }


    /**
     * Auxiliar function to see the date of publication as a string
     *
     * */
    public String getPublicationString(){

        SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy");

        if(publication!=null){

        	return date_format.format(publication);
        }

        else{

        	return null;
        }
    }


    /**
  	 * Sets the id of a post
  	 *
  	 * @param identifier id of the post
  	 * */
    public void setIdentifier(int identifier){this.identifier = identifier;}

    /**
     * Sets the title of a post
     *
     * @param title Title of the post
     * */


    public void setTitle(String title){this.title = title;}

    /**
     * Sets the body of a post
     *
     * @param body body of the post
     * */

    public void setBody(String body){this.body = body;}

    /**
     * Sets the owner of a post
     *
     * @param owner owner of the post
     * */


    public void setOwner(Contact owner){this.owner = owner;}

    /**
     * Sets the date of publication of a post
     *
     * @param publication date of publication of the post
     * */


    public void setPublication(Date date){this.publication = date;}

    /**
     * Sets the status of a post
     *
     * @param status status of the post
     * */


    public void setStatus(Status status) {this.status = status;}

    /**
     * Sets the type of a post
     *
     * @param type Type of the post
     * */



    public void setType(Type type){this.type = type;}


    /**
  	 * Auxiliar functions to see the info of a post
  	 *
  	 * */
    public String toString(){

        return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner.getFullname() + "; Publication: " + publication + "}";
    }

    public String toStringFile(){

        return getType() + "|" + identifier + "|" + title + "|" + body + "|" + owner.getEmail() + "|" + getPublicationString() + "|" + getStatus();
    }
}

package board_manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import contact_manager_package.Contact;


/**
 * A concrete post in the factory
 * @author Pedro Pablo Garcia Pozo
 * @author Ruben Borrego Canovaca
 * */

public class Flash_Post extends Post{


//Attributes
    protected Date date_start;
    protected Date date_end;


/**
* Parameterized constructor
* @param identifier The identifier of the post
* @param title The title of the post
* @param body The body of the post
* @param owner The owner of the post
* @param date_start starting date that a flash post is allowed to be posted
* @param date_end ending date that a flash post is allowed to be posted
* */


    public Flash_Post(int identifier, String title, String body, Contact owner, Date date_start, Date date_end){

        super(identifier, title, body, owner);

        this.date_start = date_start;
        this.date_end = date_end;
    }

    /**
  	 * Returns the starting date of an flash post
  	 *
  	 * @return starting date of the flash post
  	 * */


    public Date getDateStart() {return date_start;}


    /**
     * Returns the ending date of an flash post
     *
     * @return ending date of the flash post
     * */



    public Date getDateEnd() {return date_end;}


    /**
     * Auxiliar function to see the date interval as a string
     *
     * */

    public String getDateString(){

        SimpleDateFormat format = new SimpleDateFormat("HH:mm/dd-MM-yyyy");
		return format.format(date_start) + "|" + format.format(date_end);
    }



    /**
     * Sets the starting date of a flash post
     *
     * @param date_start starting date of the flash post
     * */


    public void setDateStart(Date date_start) {this.date_start = date_start;}

    /**
     * Sets the ending date of a flash post
     *
     * @param date_end ending date of the flash post
     * */


    public void setDateEnd(Date date_end) {this.date_end = date_end;}




    /**
  	 * Auxiliar functions to see the info of a post
  	 *
  	 * */

    public String toString(){

        if(date_end!=null | date_start!=null){

            return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner.getEmail() + "; Publication: " + publication + "; Starting Date: " + date_start + "; Expiration Date: " + date_end + "}";
        }

        else{

            return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner.getEmail() + "; Publication: " + publication + "}";
        }
    }

    public String toStringFile(){

        return  getType() + "|" + identifier + "|" + title + "|" + body + "|" + owner.getEmail() + "|" + getPublicationString() + "|" + getStatus() + "|" + getDateString();
    }
}

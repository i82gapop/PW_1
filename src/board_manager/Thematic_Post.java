package board_manager;

import java.util.ArrayList;

import contact_manager_package.Contact;


/**
 * A concrete post in the factory
 * @author Pedro Pablo Garcia Pozo
 * @author Ruben Borrego Canovaca
 * */


public class Thematic_Post extends Post{


//Attributes
    protected ArrayList <String> interests = new ArrayList <String>();

    /**
     * Parameterized constructor
     * @param identifier The identifier of the post
     * @param title The title of the post
     * @param body The body of the post
     * @param owner The owner of the post
     * @param interests an ArrayList with the interests of a Thematic post
     * */

    public Thematic_Post(int identifier, String title, String body, Contact owner, ArrayList <String> interests){

        super(identifier, title, body, owner);

        this.interests = interests;
    }


    /**
  	 * Returns the interests of a Thematic post
  	 *
  	 * @return interests of the Thematic post
  	 * */
    public ArrayList <String> getInterests() {return interests;}

    /**
     * Sets the interests of an Thematic post
     *
     * @param interests interests of the Thematic post
     * */



    public void setInterests(ArrayList <String> interests){this.interests = interests;}


    /**
  	 * Auxiliar functions to see the info of a post
  	 *
  	 * */

     
    public String toString(){

        if(interests!=null){

            return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner.getFullname() + "; Publication: " + publication + "; Interests: " + interests + "}";
        }

        else{

            return "Post {ID: " + identifier + "; Title: " + title + "; Body: " + body + "; Owner: " + owner.getFullname() + "; Publication: " + publication + "}";
        }
    }

    public String toStringFile(){

        return  getType() + "|" + identifier + "|" + title + "|" + body + "|" + owner.getEmail() + "|" + getPublicationString() + "|" + getStatus() + "|" + interests;
    }
}

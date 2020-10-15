package board_manager;

import java.util.ArrayList;

import contact_manager_package.Contact;

public class Thematic_Post extends Post{
    
    protected ArrayList <String> interests = new ArrayList <String>();

    public Thematic_Post(int identifier, String title, String body, Contact owner, ArrayList <String> interests){

        super(identifier, title, body, owner);

        this.interests = interests;
    }

    public ArrayList <String> getInterests() {return interests;}

    public void setInterests(ArrayList <String> interests){this.interests = interests;}

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

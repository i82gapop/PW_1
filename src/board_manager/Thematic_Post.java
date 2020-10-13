package board_manager;

import java.util.ArrayList;
import java.util.Date;

import contact_manager_package.Contact;

public class Thematic_Post extends Post{
    
    protected ArrayList <String> interests = new ArrayList <String>();

    public Thematic_Post(){};

    public Thematic_Post(int identifier, String title, String body, Contact owner, ArrayList<Contact> destinators, Date publication, ArrayList <String> interests){

        super(identifier, title, body, owner, destinators, publication);

        this.interests = interests;
    }

    public ArrayList <String> getInterests() {return interests;}

    public void setInterests(ArrayList <String> interests){this.interests = interests;}
}

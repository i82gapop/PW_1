package board_manager;

import contact_manager_package.Contact;

import java.util.ArrayList;
import java.util.Date;

public class General_Post extends Post{
    
    public General_Post(){};

    public General_Post(int identifier, String title, String body, Contact owner, ArrayList<Contact> destinators, Date publication){

        super(identifier, title, body, owner, destinators, publication);
    }
}
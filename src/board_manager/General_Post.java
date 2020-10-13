package board_manager;

import contact_manager_package.Contact;

import java.util.Date;

public class General_Post extends Post{

    public General_Post(int identifier, String title, String body, Contact owner, Date publication){

        super(identifier, title, body, owner, publication);
    }
}
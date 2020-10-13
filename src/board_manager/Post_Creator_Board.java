package board_manager;

import contact_manager_package.Contact;
import java.util.Date;

public class Post_Creator_Board implements Post_Creator{
    
    public Post getPost(String type, int identifier, String title, String body, Contact owner, Date publication){

        if(type.equals("GENERAL")){

            return new General_Post(identifier, title, body, owner, publication);
        }
    
        else if(type.equals("INDIVIDUALIZED")){
    
            return new Individualized_Post(identifier, title, body, owner, publication);
        }
    
        else if(type.equals("THEMATIC")){
    
            return new Thematic_Post(identifier, title, body, owner, publication);
        }
    
        else if(type.equals("FLASH")){
    
            return new Flash_Post(identifier, title, body, owner, publication);
        }
    
        else{
    
            return null;
        }
    }
}

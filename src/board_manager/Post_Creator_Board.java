package board_manager;

import contact_manager_package.Contact;
import java.util.Date;
import java.util.ArrayList;

public class Post_Creator_Board implements Post_Creator{
    
    public Post getPost(Type type, int identifier, String title, String body, Contact owner, ArrayList <String> recipients, ArrayList <String> interests, Date date_start, Date date_end){

        if(type == Type.GENERAL){

            return new General_Post(identifier, title, body, owner);
        }
    
        else if(type == Type.INDIVIDUALIZED){

            return new Individualized_Post(identifier, title, body, owner, recipients);
        }
    
        else if(type == Type.THEMATIC){
    
            return new Thematic_Post(identifier, title, body, owner, interests);
        }
    
        else if(type == Type.FLASH){
    
            return new Flash_Post(identifier, title, body, owner, date_start, date_end);
        }
    
        else{
    
            return null;
        }
    }
}

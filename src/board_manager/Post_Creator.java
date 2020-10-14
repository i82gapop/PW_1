package board_manager;

import contact_manager_package.Contact;
import java.util.Date;
import java.util.ArrayList;

/**
 * The abstract factory to create posts
 * @author Pedro Pablo Garcia Pozo
 * @author Ruben Borrego Canovaca
 * @version 2.1
 * @since 248274129
 */


public interface Post_Creator {
    
    public Post getPost(Type type, int identifier, String title, String body, Contact owner, ArrayList <String> recipients, ArrayList <String> interests, Date date_start, Date date_end);
}

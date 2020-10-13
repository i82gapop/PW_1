package board_manager;

import contact_manager_package.Contact;
import java.util.Date;

/**
 * The abstract factory to create posts
 * @author Pedro Pablo Garcia Pozo
 * @author Ruben Borrego Canovaca
 * @version 2.1
 * @since 248274129
 */


public interface Post_Creator {
    
    public Post getPost(String type, int identifier, String title, String body, Contact owner, Date publication);
}

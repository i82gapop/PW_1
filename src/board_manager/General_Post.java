package board_manager;

import contact_manager_package.Contact;


/**
 * A concrete post in the factory
 * @author Pedro Pablo Garcia Pozo
 * @author Ruben Borrego Canovaca
 * */


public class General_Post extends Post{


  /**
   * Parameterized constructor
   * @param identifier The identifier of the post
   * @param title The title of the post
   * @param body The body of the post
   * @param owner The owner of the post
   * */

    public General_Post(int identifier, String title, String body, Contact owner){

        super(identifier, title, body, owner);
    }
}

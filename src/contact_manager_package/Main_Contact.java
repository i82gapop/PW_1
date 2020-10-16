package contact_manager_package;

import java.io.IOException;
import java.text.ParseException;


/**
 * A class to represent the main of the contact manager
 * @author Pedro Pablo Garcia Pozo
 * @author Ruben Borrego Canovaca
 * @since 26-09-2020
 * @version 2.0
 *
 * */

public class Main_Contact {

    public static void main(String[] args) throws ParseException, IOException{

        Manager system = Manager.getInstance();

        system.LoadFile();

        system.Menu();

        system.SaveFile();
    }
}

package contact_manager_package;

import java.io.IOException;
import java.text.ParseException;

public class Principal {
    
    public static void main(String[] args) throws ParseException, IOException{
        
        Manager system = Manager.getInstance();

        system.LoadFile();

        system.Menu();

        system.SaveFile();
    }
}
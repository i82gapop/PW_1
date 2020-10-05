package contact_manager_package;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.text.ParseException;

public class Principal {
    
    public static void main(String[] args) throws ParseException, IOException{
        
        Manager system = Manager.getInstance();
        
        system.LoadFile();

        //system.Menu();
        
        system.mostrarContactos();

        system.SaveFile();

    }
}
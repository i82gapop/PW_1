package board_manager;

import java.io.IOException;
import java.text.ParseException;

public class Principal {

    public static void main(String[] args) throws ParseException, IOException {
        
        PostManager system = PostManager.getInstance();

        system.LoadFile();

        system.Menu();

        system.SaveFile();
    }
}

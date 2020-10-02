package contact_manager_package;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Principal {
    
    public static void main(String[] args) throws ParseException{
        
        Manager m1 = Manager.getInstance();

        Date aux = new Date();
        Date aux2 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        aux = format.parse("2000-06-03");
        aux2 = format.parse("1960-06-03");

        m1.AddContact("pedro", "pablo", aux, "pedropgarciap@gmail.com");
        m1.AddContact("Javier", "García Ibáñez", aux2, "jgar@");
        
        Contact aux1 = m1.SearchContactByEmail("pedropgarciap@gmail.com");
        aux1.addInterest("FAMILIA"); 
        aux1.addInterest("TRABAJO");
        System.out.println("EDAD: " + aux1.getAge());
        
        aux1 = m1.SearchContactByEmail("jgar@");
        aux1.addInterest("TRABAJO");

        m1.mostrarContactos();
        m1.ConsultContact();
    
    }
}

package contact_manager_package;

import java.util.Date;

public class Principal {
    
    public static void main(String[] args) {
        
        Manager m1 = Manager.getInstance();

        Date aux = new Date();

        m1.AddContact("Pedro Pablo", "García Pozo", aux, "pedropgarciap@gmail.com");
        m1.AddContact("Javier", "García Ibáñez", aux, "jgar@");
        m1.AddContact("Ruben", "Borrego noseque", aux, "ruben@");
        
        m1.ConsultContact();
    }
}

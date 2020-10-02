package contact_manager_package;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.text.*;
import java.io.*;


public class Manager {

    private static Manager instance = null;

    private ArrayList <Contact> contacts;
    private Scanner in = new Scanner (System.in);
    
    private Manager(){

        this.contacts = new ArrayList <Contact>();
    }

    public static Manager getInstance(){

        if(instance == null){
            
            instance = new Manager();
        }

        return instance;
    }

    public void AddContact (Contact contact){

        contacts.add(contact);
    }

    public boolean AddContact(String name, String surname, Date birthday, String email){

        if(!checkExistence(email)){

            AddContact(new Contact(name, surname, birthday, email));

            System.out.println("Contacto añadido con éxito.");
            return true;
        }

        System.out.println("No se ha podido añadir el contacto por que el email ya existe en la Base de Datos");
        return false;
    }

    public boolean RemoveContact (String email){

        if(checkExistence(email)){
            
            for(int i = 0; i < contacts.size(); i++){
            
                if(contacts.get(i).getEmail().equals(email)){
                
                    contacts.remove(i);
                }
            }
            
            System.out.println("Contacto eliminado con éxito.");
            return true;
        }

        System.out.println("No se ha podido eliminar el contacto por que el email no existe en la Base de Datos");
        return false;       
    }

    public void UpdateContact (Contact new_contact, Contact old_contact){

        int ind = contacts.indexOf(old_contact);
        contacts.set(ind, new_contact);

    }

    public void ConsultContact(){

        int option;

        Contact single = new Contact();
        ArrayList <Contact> compound = new ArrayList <Contact>();
        String search_term;
        int search_term_int;

        System.out.println("=====================================");
        System.out.println("Contact's Consult");
        System.out.println("=====================================");
        System.out.println("How do you wish to search for your contact?: ");
        System.out.println("1. By email");
        System.out.println("2. By name (name and surname)");
        System.out.println("3. By interests)");
        System.out.println("4. By age");
        System.out.println("=====================================");
        System.out.println("Your option: ");
        option = in.nextInt();

        if(option == 1){

            System.out.println("Type the email of the contact to search: ");
            search_term = in.next();

            if((single = SearchContactByEmail(search_term)) != null){

                System.out.println("Showing the result of the search: ");
                System.out.println(single.toString());
            }

            else{

                System.out.println("No results.");
            }
        } 

        else if(option == 2){
            
            System.out.println("Type the full name of the contact to search: ");

            in = new Scanner (System.in);

            search_term = in.nextLine();

            if((compound = SearchContactByFullname(search_term)) != null){

                System.out.println("Showing the result of the search: ");
                
                for(int i = 0; i < compound.size(); i++){

                    System.out.println(compound.get(i).toString());
                }
            }

            else{

                System.out.println("No results.");
            }
        }

        else if(option == 3){
            
            System.out.println("Type the interest of the contact to search: ");
            search_term = in.next();

            if((compound = SearchContactByInterest(search_term)) != null){

                System.out.println("Showing the result of the search: ");
                
                for(int i = 0; i < compound.size(); i++){

                    System.out.println(compound.get(i).toString());
                }
            }

            else{

                System.out.println("No results.");
            }
        }

        else if (option == 4){

            System.out.println("Type the age of the contact to search: ");
            search_term_int = in.nextInt();


            if((compound = SearchContactByAge(search_term_int)) != null){

                System.out.println("Showing the result of the search: ");
                
                for(int i = 0; i < compound.size(); i++){

                    System.out.println(compound.get(i).toString());
                }
            }

            else{

                System.out.println("No results.");
            }          
        }

        else{
            
            System.out.println("Ha elegido una opción incorrecta. Pruebe de nuevo.");
        }
    }

    public boolean checkExistence(String email){
        
        Iterator <Contact> it = contacts.iterator();
        
        while(it.hasNext()){

            if(it.next().getEmail() == email){
                
                return true;
            }
        }

        return false;
    }

    public Contact SearchContactByEmail(String email){
        
        Contact single;

        for(int i = 0; i < contacts.size(); i++){
            
            if(contacts.get(i).getEmail().equals(email)){
            
                single = contacts.get(i);
                return single;
            }
        }

        return null;
    }
    
    public ArrayList <Contact> SearchContactByFullname(String fullname){

        ArrayList <Contact> compound = new ArrayList <Contact>();
        Contact single;

        for(int i = 0; i < contacts.size(); i++){

            if(contacts.get(i).getFullname().equals(fullname)){

                single = contacts.get(i);
                compound.add(single);
            }
        }

        if(compound.size() != 0){

            return compound;
        }

        else{

            return null;
        }
    }

    public ArrayList <Contact> SearchContactByInterest(String interest){
        
        ArrayList <Contact> aux = new ArrayList <Contact>();

        for(int i = 0; i < contacts.size(); i++){

            if(contacts.get(i).getInterests().contains(Interest.valueOf(interest))){

                aux.add(contacts.get(i));
            }
        }

        if(aux.size() != 0){

            return aux;
        }

        else{

            return null;
        }
    }

    public ArrayList <Contact> SearchContactByAge(int age){

        ArrayList <Contact> compound = new ArrayList <Contact>();
        Contact single;

        for(int i = 0; i < contacts.size(); i++){

            if(contacts.get(i).getAge() == age){

                single = contacts.get(i);
                compound.add(single);
            }
        }

        if(compound.size() != 0){

            return compound;
        }

        else{

            return null;
        }
    }

    public void mostrarContactos(){

        Iterator <Contact> it = contacts.iterator();
        
        while(it.hasNext()){

            System.out.println(it.next().toString());
        }
    }

    public void SaveFile(String message){

        






    }

    public void LoadFile() throws ParseException{
        

        try{
            BufferedReader br = new BufferedReader(new FileReader(new File("contact.txt")));
            String line;
            String[] arrayline, aux_interest;
            SimpleDateFormat aux_date = new SimpleDateFormat("dd/MM/yyyy");

            while((line = br.readLine()) != null){

                arrayline = line.split("|");
                ArrayList<Interest> aux_array = new ArrayList<Interest>();
                    
                arrayline[4].substring(1, arrayline[4].length() - 1);

                aux_interest = arrayline[4].split(", ");

                for(int i = 0; i < arrayline[4].length(); i++){
                    
                    Interest inter = Interest.valueOf(aux_interest[i]);
		            aux_array.add(inter);
                }
                            
                Contact aux_contact = new Contact(arrayline[0],arrayline[1],aux_date.parse(arrayline[2]),arrayline[3],aux_array);

                contacts.add(aux_contact);
            }

        br.close();
        }catch(IOException e){
            System.out.println("File not found.");
        }
    }
}
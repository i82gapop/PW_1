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
    
    private Manager(){

        contacts = new ArrayList <Contact>();
    }

    private static Manager getInstance(String email){

        if(instance == null){
            
            instance = new Manager();
        }

        return instance;
    }

    public void AddContact (Contact contact){

        contacts.add(contact);
    }

    public boolean AddContact(String name, String surname, Date birthday, String email){

        if(SearchContactBy(email)){

            AddContact(new Contact(name, surname, birthday, email));
            
            return true;
        }

        return false;
    }

    public boolean RemoveContact (Contact contact){

        return RemoveContact(contact.getEmail());
    }

    public boolean RemoveContact (String email){
        
       for(int i = 0; i < contacts.size(); i++){
           
            if(contacts.get(i).getEmail().equals(email)){
               
               contacts.remove(i);
               return true;
            }
       }
       return false;
    }

    public void UpdateContact (Contact new_contact, Contact old_contact){

        int ind = contacts.indexOf(old_contact);
        contacts.set(ind, new_contact);

    }



    public void ConsultContact(){

        Scanner in = new Scanner (System.in);
        int option;

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
            
            Contact aux1 = new Contact();
            String aux_email;
            System.out.println("Type the email of the contact: ");
            aux_email = in.next();

            if(SearchContactBy(aux1, aux_email)){

                System.out.println("Showing the result of the search: ");
                System.out.println(aux1.toString());
            }

            else{

                System.out.println("No results.");
            }
        } 

        else if(option == 2){
            
            ArrayList <Contact> aux2 = new ArrayList <Contact>();
            String aux_fullname;
            System.out.println("Type the email of the contact: ");
            aux_fullname = in.next();

            if(SearchContactBy(aux2, aux_fullname)){

                System.out.println("Showing the result of the search: ");
                
                Iterator <Contact> it = aux2.iterator();

                while(it.hasNext()){

                    it.next().toString();
                }
            }

            else{

                System.out.println("No results.");
            }
        }

        else if(option == 3){
            
            ArrayList <Contact> aux3 = new ArrayList <Contact>();
            String aux_interest;
            System.out.println("Type the email of the contact: ");
            aux_interest = in.next();

            if(SearchContactBy(aux3, aux_fullname)){

                System.out.println("Showing the result of the search: ");
                
                Iterator <Contact> it = aux3.iterator();

                while(it.hasNext()){

                    it.next().toString();
                }
            }

            else{

                System.out.println("No results.");
            }
        }

        else if (option == 4){
            /*string aux_name;
            System.out.println("Type the full name of the contact: ");
            aux_name = in.nextLine();
            System.out.println("Showing results of the search: ")
            System.out.println(SearchContactBy(aux_name).toString());*/

        }

        else{
            /*sdsds*/
        }
    }



    public boolean SearchContactBy(Contact contacto, String email){
        
        Iterator <Contact> it = contacts.iterator();
        
        while(it.hasNext()){

            if(it.next().getEmail() == email){
                
                contacto = it.next();
                
                return true;
            }
        }

        return false;
    }

    public boolean SearchContactBy(String email){
        
        Iterator <Contact> it = contacts.iterator();
        
        while(it.hasNext()){

            if(it.next().getEmail() == email){
                
                return true;
            }
        }

        return false;
    }
    
    public boolean SearchContactBy(ArrayList <Contact> contactos, String fullname){
        
        Iterator <Contact> it = contacts.iterator();
        Contact aux;

        while(it.hasNext()){

            if(it.next().getFullname() == fullname){
                
                aux = it.next();
                contacts.add(aux);
            }
        }

        if(contactos.size() != 0){

            return true;
        }

        else{
            
            return false;
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
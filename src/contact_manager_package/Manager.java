package contact_manager_package;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;


public class Manager {

    private static Manager instance = null;
    private ArrayList <Contact> contacts;
    private static Manager getInstance(String email){

        if(instance == null){
            
            instance = new Manager (email);
        }

        return instance;
    }

    private Manager(String email){
        contacts = new ArrayList <Contact>();
    }

    public boolean AddContact (Contact contact){
        return contacts.add(contact);

    }
    public void AddContact(String email, String name, String surname, Date birthday, ArrayList<Interest> interests){
        AddContact(new Contact (email, name, surname, birthday, interests));
    }

    public boolean RemoveContact (Contact contact){
        return contacts.remove(contact);
    }

    public void RemoveContact (String email){
        RemoveContact(SearchContactBy(email));
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
        System.out.println("Your option: "));
        option = in.nextInt();

        switch(option){

            if(option == 1){

                String aux_email;
                System.out.println("Type the email of the contact: ");
                aux_email = in.next();
                System.out.println("Showing results of the search: ")
                System.out.println(SearchContactBy(aux_email).toString());


           } 

            else if(option == 2){
                String aux_name;
                System.out.println("Type the full name of the contact: ");
                aux_name = in.nextLine();
                System.out.println("Showing results of the search: ")
                System.out.println(SearchContactBy(aux_name).toString());

           }
            else if(option == 3){
                /*string aux_interests;
                System.out.println("Type the full name of the contact: ");
                aux_name = in.nextLine();
                System.out.println("Showing results of the search: ")
                System.out.println(SearchContactBy(aux_name).toString());*/

           }
            else if (option == 4){
                /*string aux_name;
                System.out.println("Type the full name of the contact: ");
                aux_name = in.nextLine();
                System.out.println("Showing results of the search: ")
                System.out.println(SearchContactBy(aux_name).toString());*/

            }
            else{

            }








        }


    }


    public Contact SearchContactBy (String email){
        Iterator <Contact> it = contacts.iterator();
        Contact aux;
        while(it.hasNext()){
            aux = it.next();
            if(aux.getEmail() == email){
                return aux; 
            }
        }
        return null;
    }

        public Contact SearchContactBy(){
        }

}
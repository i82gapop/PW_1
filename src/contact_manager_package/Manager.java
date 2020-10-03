package contact_manager_package;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
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

    public void Menu(){

        int option;

        System.out.println("=====================================");
        System.out.println("Contact's Consult");
        System.out.println("=====================================");
        System.out.println("Options: ");
        System.out.println("1. Add contact");
        System.out.println("2. Eliminate contact");
        System.out.println("3. Update contact");
        System.out.println("4. Consult contacts");
        System.out.println("0. Exit the menu");
        System.out.println("=====================================");
        System.out.println("Your option: ");
        
        option = in.nextInt();

        while(option != 0){

            
        }

    }

    public void AddContact (Contact contact){

        contacts.add(contact);
    }

    public boolean AddContact(String name, String surname, Date birthday, String email){

        if(!checkExistence(email)){

            AddContact(new Contact(name, surname, birthday, email));

            return true;
        }

        return false;
    }

    public boolean RemoveContact (String email){

        if(checkExistence(email)){
            
            for(int i = 0; i < contacts.size(); i++){
            
                if(contacts.get(i).getEmail().equals(email)){
                
                    contacts.remove(i);
                    return true;
                }
            }
        }

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
        System.out.println("3. By interests");
        System.out.println("4. By age");
        System.out.println("0. Exit the menu");
        System.out.println("=====================================");
        System.out.println("Your option: ");
        option = in.nextInt();

        while(option != 0){

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
                //in.flush();

                search_term = in.nextLine();

                if((compound = SearchContactByFullname(search_term)) != null){

                    System.out.println("Showing the results of the search: ");
                    
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

                    System.out.println("Showing the results of the search: ");
                    
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

                    System.out.println("Showing the results of the search: ");
                    
                    for(int i = 0; i < compound.size(); i++){

                        System.out.println(compound.get(i).toString());
                    }
                }

                else{

                    System.out.println("No results.");
                }          
            }

            else{
                
                System.out.println("Wrong option. Try using a valid option (between 0-4)");
                option = in.nextInt();
            }
        }
    }

    public boolean checkExistence(String email){
        
        Iterator <Contact> it = contacts.iterator();
        
        while(it.hasNext()){

            if(it.next().getEmail().equals(email)){
                
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

    public void SaveFile() throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("contact.txt")));

        for (Contact contact : contacts) {
            
            bw.write(contact.toStringFile());
            bw.write("\n");
        }

        bw.close();
    }

    public void LoadFile() throws ParseException{
        

        try{
            
            BufferedReader br = new BufferedReader(new FileReader(new File("contact.txt")));
            String line;
            String [] array = new String[5];

            SimpleDateFormat aux_date = new SimpleDateFormat("dd-MM-yyyy");
            StringTokenizer token;
            
            while((line = br.readLine()) != null){

                token = new StringTokenizer(line, "|");            

                for(int i = 0; token.hasMoreTokens(); i++){

                    array[i] = token.nextToken();
                }

                Contact aux_contact = new Contact(array[0], array[1], aux_date.parse(array[2]), array[3]);

                String without_spaces = array[4].replace(" ", "");
                String without_brackets = without_spaces.substring(1, without_spaces.length()-1);
                StringTokenizer token_interest = new StringTokenizer(without_brackets, ",");

                while(token_interest.hasMoreTokens()){

                    aux_contact.addInterest(token_interest.nextToken());
                }

                contacts.add(aux_contact);
            }

        br.close();
        }catch(IOException e){
            System.out.println("File not found.");
        }
    }
}
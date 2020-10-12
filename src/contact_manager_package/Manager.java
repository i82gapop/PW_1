package contact_manager_package;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.text.*;
import java.io.*;
import java.util.Properties;


/**
 * A program to manage contacts of different people in Java.
 * @author Ruben Borrego Canovaca
 * @author Pedro Pablo Garcia Pozo
 * @since 26-09-2020
 * @version 2.0
 **/


public class Manager {

    // Singleton declaration
    private static Manager instance = null;

    private ArrayList <Contact> contacts;
    private ArrayList <String> interests;
    private Scanner in = new Scanner (System.in);

    // private constructor
    
    private Manager(){

        this.contacts = new ArrayList <Contact>();
        this.interests = new ArrayList <String>();
    }


    // Access point to the instance

    public static Manager getInstance(){

        if(instance == null){
            
            instance = new Manager();
        }

        return instance;
    }


/**
 * An interface of the menu of the sytem
 * 
 * */

    public void Menu() throws ParseException{

        int option = 1;
        String buff_name, buff_surname, buff_birth, buff_email;
        Contact aux_contact;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        while(option != 0){
        System.out.println("CONTACT MANAGEMENT SYSTEM");
        System.out.println("=====================================");
        System.out.println("OPTIONS");
        System.out.println("=====================================");
        System.out.println("1. Add contact");
        System.out.println("2. Eliminate contact");
        System.out.println("3. Update contact");
        System.out.println("4. Consult contacts");
        System.out.println("0. Exit the menu");
        System.out.println("=====================================");
        System.out.println("Your option: ");
        
        option = in.nextInt();

            switch(option){

                case 0:

                        System.out.println("Saving all changes in the file.............");
                        System.out.println("All under control.");
                        System.out.println("Have a nice day, thank you for trusting on us");
                    
                        break;

                case 1: 

                    try{

                            in = new Scanner (System.in);

                            System.out.println("Type the name of the contact below:");
                        buff_name = in.nextLine();

                            System.out.println("Type the surname of the contact below:");
                        buff_surname = in.nextLine();

                            System.out.println("Type the Birthday of the contact below: (format: dd-MM-yyyy)");
                        buff_birth = in.next();

                            System.out.println("Type the email of the contact below:");
                        buff_email = in.next();

                        if(SearchContactByEmail(buff_email) == null){

                            aux_contact = new Contact(buff_name, buff_surname, format.parse(buff_birth), buff_email);
                        
                            System.out.println("What interests does your contact have from the following: " + interests);

                            in = new Scanner (System.in);

                            String buff_interest = in.nextLine();
                            String without_spaces = buff_interest.replace(" ", "");
                            StringTokenizer token_interest = new StringTokenizer(without_spaces, ",");
                            ArrayList <String> token_interests = new ArrayList <String>();

                            while(token_interest.hasMoreTokens()){

                                token_interests.add(token_interest.nextToken().toUpperCase());
                            }

                            for(int i = 0; i < token_interests.size(); i++){

                                if(interests.contains(token_interests.get(i))){
                
                                    aux_contact.addInterest(token_interests.get(i));
                                }
                            }

                            AddContact(aux_contact);
                        }

                    else {
                        System.out.println("Email already on the System.");
                    }

                        }catch(ParseException e){
                            System.out.println("Not a valid format for the date, try with this format: dd/MM/yyyy.");
                            }

                    break;

                case 2:

                    in = new Scanner (System.in);
                        System.out.println("Type the email of the contact to remove below: ");
                    buff_email = in.next();
                    RemoveContact(buff_email);

                    break;

                case 3:
                try{

                    in = new Scanner (System.in);
                        System.out.println("Type the email of the contact to update below: ");
                    buff_email = in.next();

                    in = new Scanner (System.in);

                        System.out.println("Lets add the new information of the contact.");
                        System.out.println("Type the new name of the contact below:");
                    buff_name = in.nextLine();

                        System.out.println("Type the new surname of the contact below:");
                    buff_surname = in.nextLine();

                        System.out.println("Type the new Birthday of the contact below: (format: dd-MM-yyyy)");
                    buff_birth = in.next();

                    aux_contact = new Contact(buff_name, buff_surname, format.parse(buff_birth), buff_email);

                    System.out.println("What new interests does your contact have from the following: " + interests);

                    in = new Scanner (System.in);

                    String buff_interest_ = in.nextLine();
                    String without_spaces_ = buff_interest_.replace(" ", "");
                    StringTokenizer token_interest_ = new StringTokenizer(without_spaces_, ",");
                    ArrayList <String> token_interests_ = new ArrayList <String>();


                    while(token_interest_.hasMoreTokens()){

                        token_interests_.add(token_interest_.nextToken().toUpperCase());
                    }

                    for(int i = 0; i < token_interests_.size(); i++){

                        if(interests.contains(token_interests_.get(i))){
        
                            aux_contact.addInterest(token_interests_.get(i));
                        }
                    }

                    UpdateContact(aux_contact, SearchContactByEmail(buff_email));

                    }catch(ParseException e){
                            System.out.println("Not a valid format for the date, try with this format: dd/MM/yyyy.");
                            }

                    break;


                case 4:

                    ConsultContact();
                    break;

                
                default:
                    System.out.println("Not a valid option. Try again.");
            }
            
        }

    }




/**
 * Function that adds a given contact to the contacts list.
 * 
 * @param contact Contact to add
 * @return boolean value, false if its a problem adding the contact; true if its added without problems
 *
 **/

    public boolean AddContact(Contact contact){

        if(!checkExistence(contact.getEmail())){

            contacts.add(contact);

            return true;
        }

        return false;
    }

/**
 * Function that removes a given contact from the list.
 * 
 * @param email Email of the contact to remove
 * @return boolean value, false if its a problem removing the contact; true if its removed without problems
 *
 **/

    public boolean RemoveContact (String email){

        if(checkExistence(email)){
            
            for(int i = 0; i < contacts.size(); i++){
            
                if(contacts.get(i).getEmail().equals(email)){
                    
                    
                    contacts.remove(i);
                    System.out.println("Contact removed succesfully");
                    return true;
                }
            }
        }


            System.out.println("The contact doesn't exist.");
            return false;   
        

    }


/**
 * Function that updates a contact of the contacts list.
 * 
 * @param new_contact Contact with the new information to add
 * @param old_contact Contact in the list to update
 *
 **/

    public void UpdateContact (Contact new_contact, Contact old_contact){

        int ind = contacts.indexOf(old_contact);
        contacts.set(ind, new_contact);
    }



/**
 * An interface to see Search options
 * 
 **/

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

                if((compound = SearchContactByInterest(search_term.toUpperCase())) != null){

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

    /**
 * Function that check the existence of a contact with a given email
 * 
 * @param email Enail for searching of
 * @return boolean value, true if it exists; false if it doesn't
 *
 **/

    public boolean checkExistence(String email){
        
        Iterator <Contact> it = contacts.iterator();
        
        while(it.hasNext()){

            if(it.next().getEmail().equals(email)){
                
                return true;
            }
        }

        return false;
    }


/**
 * A function that searches for the contact with the email given
 *
 * @param email email of the contact to search for
 * @return A contact with the email given
 * */

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


/**
 * A function that searches for the contacts with the name given
 *
 * @param fullname Full name of the contact to search for
 * @return A list with all the contacts with the same name as given
 * */
    
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

/**
 * A function that searches for the contacts with the interest given
 *
 * @param interest Interest of the contact to search for
 * @return A list with all the contacts with the same interest as given
 * */

    public ArrayList <Contact> SearchContactByInterest(String interest){
        
        ArrayList <Contact> aux = new ArrayList <Contact>();

        for(int i = 0; i < contacts.size(); i++){

            if(contacts.get(i).getInterests().contains(interest)){

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


    /**
 * A function that searches for the contacts with the age given
 *
 * @param age Age of the contacts to search for
 * @return A list with all the contacts with the same age as given
 * */

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


/**
 * An interface that shows the list of contacts
 *
 * */

    public void ShowContacts(){

        Iterator <Contact> it = contacts.iterator();
        
        while(it.hasNext()){

            System.out.println(it.next().toString());
        }
    }



/**
 * A function that saves all the info of the system in a file
 *
 * */

    public void SaveFile() throws IOException {

        Properties properties = new Properties();

        InputStream file_ = null;

        file_ = new FileInputStream("properties.properties");

        properties.load(file_);

        String path = properties.getProperty("Directory");

        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));

        for (Contact contact : contacts) {
            
            bw.write(contact.toStringFile());
            bw.write("\n");
        }

        bw.close();
    }



/**
 * A function that reads all the info of a file and load it all in the system
 *
 * */
    public void LoadFile() throws ParseException{
        

        try{
            Properties properties = new Properties();

            InputStream file_ = null;

            file_ = new FileInputStream("properties.properties");

            properties.load(file_);

            String path = properties.getProperty("Directory");
            StringTokenizer interests_token = new StringTokenizer(properties.getProperty("Interests"), ","); 
            ArrayList <String> interests_tokens = new ArrayList <String>();

            while(interests_token.hasMoreTokens()){

                interests_tokens.add(interests_token.nextToken());
            }

            for(int i = 0; i < interests_tokens.size(); i++){//eliminates duplicates

                if(!interests.contains(interests_tokens.get(i))){

                    interests.add(interests_tokens.get(i));
                }
            }

            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
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
                ArrayList <String> token_interests = new ArrayList <String>();

                while(token_interest.hasMoreTokens()){

                    token_interests.add(token_interest.nextToken());
                }

                for(int i = 0; i < token_interests.size(); i++){//eliminates duplicates

                    if(interests.contains(token_interests.get(i))){
                
                        aux_contact.addInterest(token_interests.get(i));
                    }
                }

                contacts.add(aux_contact);
            }

        br.close();
        }catch(IOException e){
            System.out.println("File not found.");
        }
    }
}
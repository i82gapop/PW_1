package board_manager;


import contact_manager_package.Contact;
import contact_manager_package.Manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
import java.util.Properties;
import java.util.StringTokenizer;




/**
 * A program to manage posts a board with different contacts in Java.
 * @author Ruben Borrego Canovaca
 * @author Pedro Pablo Garcia Pozo
 * @since 26-09-2020
 * @version 2.0
 **/

public class PostManager {

    private static PostManager instance = null;

	SimpleDateFormat format = new SimpleDateFormat("HH:mm/dd-MM-yyyy");
	private Scanner in = new Scanner (System.in);
    private ArrayList <Post> posts;
    private ArrayList <String> interests;
    private Manager manager = Manager.getInstance();



    /**
    * private constructor
    * @throws ParseException if the format of the dates aren't allowed
    *
    * */
    private PostManager() throws ParseException {

        this.posts = new ArrayList <Post>();
        this.interests = new ArrayList <String>();

        manager.LoadFile();
    }

        /**
        * Access point to the instance
        * @throws ParseException if the format of the dates aren't allowed
        *
        * */

    public static PostManager getInstance() throws ParseException {

        if(instance == null){

            instance = new PostManager();
        }

        return instance;
    }


    /**
     * A visual menu of the sytem
     * @throws ParseException if the format of the dates aren't allowed
     * */
	public void Menu() throws ParseException{

        Contact user;
        String buff_title, buff_body, log_username, buff_interest, buff_recipients, without_spaces, buff_date_pub, buff_date_end;

        System.out.println("ADVERTISEMENTS MANAGEMENT SYSTEM");
        System.out.println("Type your email to login in: ");
        log_username = in.next();

        if((user = manager.SearchContactByEmail(log_username)) != null){

            int option = 1, id;

            while(option != 0){
            in = new Scanner (System.in);
            System.out.println("ADVERTISEMENTS MANAGEMENT SYSTEM");
            System.out.println("OPTIONS");
            System.out.println("=====================================");
            System.out.println("=====================================");
            System.out.println("1. Create a post");
            System.out.println("2. Post a post");
            System.out.println("3. Edit a post");
            System.out.println("4. Archive a post");
            System.out.println("5. Search a post");
            System.out.println("6. Show board");
            System.out.println("0. Exit the menu");
            System.out.println("=====================================");
            System.out.println("Your option: ");

            option = in.nextInt();

                switch(option){

                    case 0:

                        System.out.println("Saving all changes in the file.............");
                        System.out.println("All under control.");
                        System.out.println("Have a nice day, thank you for trusting on us.");

                        break;

                    case 1:
                        
                        try{

                        Post_Creator_Board aux_post_creator = new Post_Creator_Board();
                        Post aux_post;
                        

                    	int opt;
                    	in = new Scanner (System.in);
                        System.out.println("What type of post do you want to create?: ");
                        System.out.println("1. General Post.");
                        System.out.println("2. Thematic Post.");
                        System.out.println("3. Individualized Post.");
                        System.out.println("4. Flash Post");
                        System.out.println("0. Cancel");
                        System.out.println("=====================================");
                        System.out.println("Your option: ");
                        opt = in.nextInt();

                        switch(opt) {
                            
                            case 0:
                                
                                System.out.println("Cancelled");
                                break;

                            case 1:
                            
                                in = new Scanner (System.in);
                                System.out.println("Type the title of the post: ");
                                buff_title = in.nextLine();
                                System.out.println("Type the body of the post: ");
                                buff_body = in.nextLine();
                                System.out.println("Post created successfully.");

                                aux_post = aux_post_creator.getPost(Type.GENERAL, posts.size(), buff_title, buff_body, user, null, null, null, null);
                                aux_post.setType(Type.GENERAL);

                                AddPost(aux_post);

                                break;


                            case 2:

                                in = new Scanner (System.in);
                                System.out.println("Type the title of the post: ");
                                buff_title = in.nextLine();
                                System.out.println("Type the body of the post: ");
                                buff_body = in.nextLine();
                                System.out.println("What interests does your contact have from the following: " + interests);
                                in = new Scanner (System.in);
                                buff_interest = in.nextLine();

                                without_spaces = buff_interest.replace(" ", "");
                                StringTokenizer token_interest = new StringTokenizer(without_spaces, ",");
                                ArrayList <String> token_interests = new ArrayList <String>();
                                ArrayList <String> aux_interests = new ArrayList <String>();

                                while(token_interest.hasMoreTokens()){

                                    token_interests.add(token_interest.nextToken().toUpperCase());
                                }

                                for(int i = 0; i < token_interests.size(); i++){

                                    if(interests.contains(token_interests.get(i))){

                                       aux_interests.add(token_interests.get(i));
                                    }
                                }

                                aux_post = aux_post_creator.getPost(Type.THEMATIC, posts.size(), buff_title, buff_body, user, null, aux_interests, null, null);
                                aux_post.setType(Type.THEMATIC);

                                AddPost(aux_post);

                                break;

                            case 3:

                                in = new Scanner (System.in);
                                System.out.println("Type the title of the post: ");
                                buff_title = in.nextLine();
                                System.out.println("Type the body of the post: ");
                                buff_body = in.nextLine();
                                System.out.println("What recipients do you want the post to have: ");
                                buff_recipients = in.nextLine();

                                without_spaces = buff_recipients.replace(" ", "");
                                StringTokenizer token_recipient = new StringTokenizer(without_spaces, ",");
                                ArrayList <String> token_recipients = new ArrayList <String>();
                                ArrayList<String> aux_recipients = new ArrayList<String>();

                                while(token_recipient.hasMoreTokens()){

                                    token_recipients.add(token_recipient.nextToken());
                                }

                                for(int i = 0; i < token_recipients.size(); i++){

                                    if(manager.checkExistence(token_recipients.get(i))){

                                       aux_recipients.add(token_recipients.get(i));
                                    }
                                }

                                aux_post = aux_post_creator.getPost(Type.INDIVIDUALIZED, posts.size(), buff_title, buff_body, user, aux_recipients, null, null, null);
                                aux_post.setType(Type.INDIVIDUALIZED);

                                AddPost(aux_post);

                                break;

                            case 4:

                                in = new Scanner (System.in);
                                System.out.println("Type the title of the post: ");
                                buff_title = in.nextLine();
                                System.out.println("Type the body of the post: ");
                                buff_body = in.nextLine();
                                System.out.println("When do you want to post it?: (format: HH:mm/dd-MM-yyyy)");
                                buff_date_pub = in.next();
                                System.out.println("When do you want to remove it?: (format: HH:mm/dd-MM-yyyy)");
                                buff_date_end = in.next();


                                aux_post = aux_post_creator.getPost(Type.FLASH, posts.size(), buff_title, buff_body, user, null, null, format.parse(buff_date_pub), format.parse(buff_date_end));
                                aux_post.setType(Type.FLASH);

                                AddPost(aux_post);

                                break;

                            default:

                                System.out.println("Not a valid option. ");
                                break;
                        
                        }
                        }catch(ParseException e){
                            System.out.println("Not a valid format for the date, try with this format: HH:mm/dd-MM-yyyy.");
                        }
                                      
                        break;

                    case 2:
                        
                        in = new Scanner (System.in);
                        System.out.println("Type the id of the post to post: ");
                        id = in.nextInt();

                        if(checkExistence(id)) {

                            if(posts.get(id).getOwner() == user){
                                ToPost(id);
                            }

                            else{
                                System.out.println("You are not the owner of this post. ");
                            }
                        }

                        else {
                        	System.out.println("That ID post doesn't exist. ");
                        }

                        break;

                    case 3:
                    	try {

                        in = new Scanner (System.in);
                        System.out.println("What post do you want to edit?: (type the id)");
                        id = in.nextInt();

                        if(checkExistence(id)){
                            if(posts.get(id).getOwner() == user){

                            int option_1;

                            System.out.println("What info of the post do you want to edit?: ");
                            System.out.println("1. Title");
                            System.out.println("2. Body");

                            if(posts.get(id).getType() == Type.THEMATIC){
                                    System.out.println("3. Interests");
                            }

                            else if(posts.get(id).getType() == Type.INDIVIDUALIZED){
                                System.out.println("3. Recipients");
                            }

                            else if(posts.get(id).getType() == Type.FLASH){
                                System.out.println("3. Publication date");
                                System.out.println("4. End date");

                            }


                            System.out.println("=====================================");
                            System.out.println("Your option: ");

                            option_1 = in.nextInt();


                            if(option_1 == 1){
                                System.out.println("Type the new title of the post: ");
                                buff_title = in.next();
                                posts.get(id).setTitle(buff_title);

                                }

                            else if(option_1 == 2){
                                System.out.println("Type the new body of the post: ");
                                buff_body = in.next();
                                posts.get(id).setBody(buff_body);
                                }

                            else if(option_1 == 3){
                              if(posts.get(id).getType() == Type.THEMATIC){
                            	  if(posts.get(id) instanceof Thematic_Post) {

                                System.out.println("Type the new interests of the post: ");
                                in = new Scanner (System.in);
                                buff_interest = in.nextLine();

                                without_spaces = buff_interest.replace(" ", "");
                                StringTokenizer token_interest_ = new StringTokenizer(without_spaces, ",");
                                ArrayList <String> token_interests_ = new ArrayList <String>();
                                ArrayList <String> aux_interests_ = new ArrayList <String>();

                                while(token_interest_.hasMoreTokens()){

                                    token_interests_.add(token_interest_.nextToken().toUpperCase());
                                }

                                for(int i = 0; i < token_interests_.size(); i++){

                                    if(interests.contains(token_interests_.get(i))){

                                       aux_interests_.add(token_interests_.get(i));
                                    }
                                }

                                ((Thematic_Post)posts.get(id)).setInterests(aux_interests_);

                            	  }
                              }

                              else if(posts.get(id).getType() == Type.INDIVIDUALIZED){
                            	  if(posts.get(id) instanceof Individualized_Post) {
                                System.out.println("Type the new recipients of the post: ");
                                
                                in = new Scanner (System.in);
                                buff_recipients = in.nextLine();

                                without_spaces = buff_recipients.replace(" ", "");
                                StringTokenizer token_recipient_ = new StringTokenizer(without_spaces, ",");
                                ArrayList <String> token_recipients_ = new ArrayList <String>();
                                ArrayList<String> aux_recipients_ = new ArrayList<String>();

                                while(token_recipient_.hasMoreTokens()){

                                    token_recipients_.add(token_recipient_.nextToken());
                                }

                                for(int i = 0; i < token_recipients_.size(); i++){

                                    if(manager.checkExistence(token_recipients_.get(i))){

                                       aux_recipients_.add(token_recipients_.get(i));
                                    }
                                }

                                ((Individualized_Post)posts.get(id)).setRecipients(aux_recipients_);


                            	  }
                              }

                              else if(posts.get(id).getType() == Type.FLASH){
                            	  if(posts.get(id) instanceof Flash_Post) {
                                System.out.println("Type the new start date of the post: ");
                                buff_date_pub = in.next();
                                ((Flash_Post)posts.get(id)).setDateStart(format.parse(buff_date_pub));

                            	  }
                              }



                            }

                            else if(option_1 == 4){
                              if(posts.get(id).getType() == Type.FLASH){
                            	if(posts.get(id) instanceof Flash_Post) {
                                System.out.println("Type the new end date of the post: ");
                                buff_date_end = in.next();
                                ((Flash_Post)posts.get(id)).setDateEnd(format.parse(buff_date_end));
                            	}
                              }

                              else{
                                System.out.println("Not a valid option. ");
                              }
                            }

                              else{
                              System.out.println("Not a valid option. ");

                              }


                            }
                            else{
                                System.out.println("You are not the owner of this post.");
                            }

                        }

                        else{
                            System.out.println("This ID Post doesn't exist.");
                        }

                        }catch(ParseException e){
                            System.out.println("Not a valid format for the date, try with this format: HH:mm/dd-MM-yyyy.");
                        }

                        break;

                    case 4:

                        in = new Scanner (System.in);
                        System.out.println("What post do you want to remove?: (type the id)");
                        id = in.nextInt();

                        if(posts.size() != 0){

                            if(posts.get(id).getOwner() == user){
                                RemovePost(id);
                            }
                            else{
                                System.out.println("You are not the owner of this post. ");
                            }
                        }

                        else{

                            System.out.println("There are no posts to remove.");
                        }

                        break;

                    case 5:

                        ConsultPost();
                        break;

                    case 6:

                    	in = new Scanner (System.in);
                    	System.out.println("How do you want to order the posts?: ");
                    	System.out.println("1. By Owner. ");
                    	System.out.println("2. By Date. ");
                        System.out.println("=====================================");
                        System.out.println("Your option: ");

                    	option = in.nextInt();

                    	if(option == 1) {



                        System.out.println("==========================================================================");
                        System.out.println("Showing the board for " + log_username);
                        System.out.println("==========================================================================");
                        System.out.println("General Posts: ");

                        		int trig = 1;
                        		ArrayList<Contact> contacts = manager.getContacts();

                        		for(int k = 0; k < contacts.size(); k++) {

                        			for(int j = 0; j < posts.size(); j++) {

                        				if(posts.get(j).getOwner() == contacts.get(k)) {
                        					if(posts.get(j).getStatus() == Status.POSTED && (posts.get(j).getType() == Type.GENERAL)) {
                        						if(trig == 1) {
                        	                        System.out.println("=====================================");
                        	                        System.out.println(posts.get(j).getOwner().getEmail());
                        	                        System.out.println("=====================================");
                        	                        trig = 0;
                        						}

                        						System.out.println(posts.get(j).toString());
                        					}
                        				}

                        			}

                        			trig = 1;
                        		}



                        System.out.println("==========================================================================");
                        System.out.println("Thematic Posts: ");
                        System.out.println("==========================================================================");

                        int trigger = 0;

                        for(int i = 0; i < posts.size(); i++){
                            if((posts.get(i).getType() == Type.THEMATIC) && (posts.get(i).getStatus()== Status.POSTED)){
                                if(posts.get(i) instanceof Thematic_Post) {

                                	for(int j = 0; j < user.getInterests().size(); j++) {

                                        if((((Thematic_Post) posts.get(i)).getInterests().contains(user.getInterests().get(j))) && (trigger==0)){

                                            trigger++;
                                            trig = 1;
                                    		contacts = manager.getContacts();

                                    		for(int k = 0; k < contacts.size(); k++) {

                                    			for(int x = 0; x < posts.size(); x++) {

                                    				if(posts.get(x).getOwner() == contacts.get(k)) {
                                    					if(posts.get(x).getStatus() == Status.POSTED && (posts.get(x).getType() == Type.THEMATIC)) {
                                    						if(trig == 1) {
                                    	                        System.out.println("=====================================");
                                    	                        System.out.println(posts.get(x).getOwner().getEmail());
                                    	                        System.out.println("=====================================");
                                    	                        trig = 0;
                                    						}

                                    						System.out.println(posts.get(x).toString());
                                    					}
                                    				}


                                    			}

                                    			trig = 1;
                                    		}

                                        }
                                    }

                                    trigger = 0;
                                }
                            }
                        }

                        System.out.println("==========================================================================");
                        System.out.println("Individualized Posts: ");
                        System.out.println("==========================================================================");

                        for(int i = 0; i < posts.size(); i++){

                            if((posts.get(i).getType() == Type.INDIVIDUALIZED) && (posts.get(i).getStatus()== Status.POSTED)){
                                if(posts.get(i) instanceof Individualized_Post) {
                                    if(((Individualized_Post) posts.get(i)).getRecipients().contains(log_username) ){

                                    	trig = 1;
                                		contacts = manager.getContacts();

                                		for(int k = 0; k < contacts.size(); k++) {

                                			for(int j = 0; j < posts.size(); j++) {

                                				if(posts.get(j).getOwner() == contacts.get(k)) {
                                					if(posts.get(j).getStatus() == Status.POSTED && (posts.get(j).getType() == Type.INDIVIDUALIZED)) {
                                						if(trig == 1) {
                                	                        System.out.println("=====================================");
                                	                        System.out.println(posts.get(j).getOwner().getEmail());
                                	                        System.out.println("=====================================");
                                	                        trig = 0;
                                						}

                                						System.out.println(posts.get(j).toString());
                                					}
                                				}


                                			}

                                			trig = 1;
                                		}


                                    }

                                }

                            }
                        }




                        System.out.println("==========================================================================");
                        System.out.println("Flash Posts: ");
                        System.out.println("==========================================================================");

                        for(int i = 0; i < posts.size(); i++){

                            if((posts.get(i).getType() == Type.FLASH) && (posts.get(i).getStatus()== Status.POSTED) ){


                            	trig = 1;
                        		contacts = manager.getContacts();

                        		for(int k = 0; k < contacts.size(); k++) {

                        			for(int j = 0; j < posts.size(); j++) {

                        				if(posts.get(j).getOwner() == contacts.get(k)) {
                        					if(posts.get(j).getStatus() == Status.POSTED && (posts.get(j).getType() == Type.FLASH)) {
                        						if(trig == 1) {
                        	                        System.out.println("=====================================");
                        	                        System.out.println(posts.get(j).getOwner().getEmail());
                        	                        System.out.println("=====================================");
                        	                        trig = 0;
                        						}

                        						System.out.println(posts.get(j).toString());
                        					}
                        				}
                        			}

                        			trig = 1;
                        		}
                            }
                        }
                    }


                    else if(option == 2) {

                            ArrayList <Post> aux_array = new ArrayList<>();

                              for(int i = 0; i < posts.size(); i++){
                                if(posts.get(i).getStatus() == Status.POSTED){
                                    aux_array.add(posts.get(i));

                                }
                            }

                              Collections.sort(aux_array);

                        System.out.println("==========================================================================");
                        System.out.println("Showing the board for " + log_username);
                        System.out.println("==========================================================================");
                        System.out.println("General Posts: ");


                                

                        			for(int j = 0; j < aux_array.size(); j++) {

                        					if(aux_array.get(j).getType() == Type.GENERAL) {

                        						System.out.println(aux_array.get(j).toString());
                        					}
                                    }


                        System.out.println("==========================================================================");
                        System.out.println("Thematic Posts: ");
                        System.out.println("==========================================================================");

                        int trigger = 0;

                        for(int i = 0; i < aux_array.size(); i++){
                            if(posts.get(i).getType() == Type.THEMATIC){
                                if(posts.get(i) instanceof Thematic_Post) {

                                	for(int j = 0; j < user.getInterests().size(); j++) {

                                        if((((Thematic_Post) aux_array.get(i)).getInterests().contains(user.getInterests().get(j))) && (trigger==0)){

                                            trigger++;
                                    		System.out.println(aux_array.get(i).toString());
                                    	}
                                    }
                                }
                            }

                            trigger = 0;
                        }
       

                        System.out.println("==========================================================================");
                        System.out.println("Individualized Posts: ");
                        System.out.println("==========================================================================");

                        for(int i = 0; i < aux_array.size(); i++){

                            if((aux_array.get(i).getType()) == Type.INDIVIDUALIZED){
                                if(posts.get(i) instanceof Individualized_Post) {
                                    if(((Individualized_Post) posts.get(i)).getRecipients().contains(log_username)){
                                
                                		System.out.println(aux_array.get(i).toString());
                                    }

                                }

                            }
                        }




                        System.out.println("==========================================================================");
                        System.out.println("Flash Posts: ");
                        System.out.println("==========================================================================");


                        for(int j = 0; j < aux_array.size(); j++) {
                            if(aux_array.get(j).getType() == Type.FLASH){

                            System.out.println(aux_array.get(j).toString());
                            }
                        }

                    }

                    else{

                        System.out.println("Not a valid option. Try again.[1-2]");
                    }

                    break;

                    default:
                    System.out.println("Not a valid option. Try again.");
                    break;
                    
                }      
            }
        }
    
        else{
            
            System.out.println("User not registered in the contact manager.");
    }
}

/**
* Function that check the existence of a post with a given id
*
* @param id int id searching of
* @return boolean value, true if it exists; false if it doesn't
*
**/


	public boolean checkExistence(int id){

        Iterator <Post> it = posts.iterator();

        while(it.hasNext()){

            if(it.next().getIdentifier() == id){

                return true;
            }
        }

        return false;
	}

  /**
   * Function that adds a given post to the posts list.
   *
   * @param post Post to add
   * @return boolean value, false if its a problem adding the post; true if its added without problems
   *
   **/

	public boolean AddPost(Post post) {

		if(posts.add(post)){

            post.setStatus(Status.EDITED);
			return true;
		}

		return false;

	}

  /**
   * Function that posts an existing post.
   *
   * @param id ID of the post to post
   **/


	public void ToPost(int id) {

        for(int i = 0; i < posts.size(); i++){
        	if((posts.get(i).getIdentifier() == id) && (posts.get(i).getStatus() == Status.POSTED)) {
        		System.out.println("The post has already been posted. ");

        	}

        	else {
                if((posts.get(i).getIdentifier() == id) && (posts.get(i).getStatus() != Status.ARCHIVED)){


                    if(posts.get(i).getType() == Type.FLASH){
                        if(posts.get(i) instanceof Flash_Post) {
                            Date aux_date = new Date();

                            if((aux_date.compareTo(((Flash_Post) posts.get(i)).getDateStart()))<0){

                                posts.get(i).setPublication(((Flash_Post) posts.get(i)).getDateStart());
                                posts.get(i).setStatus(Status.WAITING);
                                System.out.println("Your post has been put into a Waiting status.");


                            }
                            else{
                                Date date = new Date();
                                posts.get(i).setPublication(date);
                                posts.get(i).setStatus(Status.POSTED);
                                System.out.println("Post posted successfully");

                            }

                        }
                    }

                    else{
                        Date date = new Date();
                        posts.get(i).setPublication(date);
                        posts.get(i).setStatus(Status.POSTED);
                        System.out.println("Post posted successfully");

                    }
                }
        	}
        }
	}


  /**
   * Function that updates a post of the posts list.
   *
   * @param new_post Post with the new information to add
   * @param old_contact Post in the list to update
   *
   **/

	public void UpdatePost(Post new_post, Post old_post) {
		int index = posts.indexOf(old_post);
		posts.set(index, new_post);

	}

  /**
   * Function that archive a post from a given id from the list.
   *
   * @param id id of the post to remove
   * @return boolean value, false if its a problem archiving the post; true if its removed without problems
   *
   **/

	public boolean RemovePost(int id){
		if(checkExistence(id)){

			for(int i = 0; i < posts.size(); i++){
				if(posts.get(i).getIdentifier() == id){
					posts.get(i).setStatus(Status.ARCHIVED);
          System.out.println("Post archived successfully.");
					return true;
				}
			}

		}

		System.out.println("The post doesn't exist.");
		return false;

	}

  /**
   * A visual menu to see Search options
   * @throws ParseException if the format of the dates aren't allowed
   **/

    public void ConsultPost() throws ParseException{

        int option;

        ArrayList <Post> res  = new ArrayList<Post>();
        String search_term;

        System.out.println("=====================================");
        System.out.println("Post's Search");
        System.out.println("=====================================");
        System.out.println("How do you wish to search for your post?: ");
        System.out.println("1. By date");
        System.out.println("2. By interest/theme");
        System.out.println("3. By owner");
        System.out.println("4. By recipient");
        System.out.println("0. Exit the menu");
        System.out.println("=====================================");
        System.out.println("Your option: ");
        option = in.nextInt();

            if(option == 1){


                System.out.println("Type the date of the posts to search: (dd-MM-yyyy)");
                search_term = in.next();


                if((res = SearchPostByDate(search_term)) != null){

                    System.out.println("Showing the result of the search: ");
                    for(int i = 0; i < res.size(); i++){

                        System.out.println(res.get(i).toString());
                    }
                }

                else{

                    System.out.println("No results.");
                }
            }

            else if(option == 2){

                System.out.println("Type the interests of the posts to search: ");
                search_term = in.next();

                if((res = SearchPostByInterest(search_term.toUpperCase())) != null){

                    System.out.println("Showing the results of the search: ");

                    for(int i = 0; i < res.size(); i++){

                        System.out.println(res.get(i).toString());
                    }
                }

                else{

                    System.out.println("No results.");

                }
            }

            else if(option == 3){

                System.out.println("Type the owner of the posts to search: ");
                search_term = in.next();

                if((res = SearchPostByOwner(search_term)) != null){

                    System.out.println("Showing the results of the search: ");

                    for(int i = 0; i < res.size(); i++){

                        System.out.println(res.get(i).toString());
                    }
                }

                else{

                    System.out.println("No results.");
                }
            }

            else if (option == 4){

                System.out.println("Type the name of the recipient of the posts to search: ");
                search_term = in.next();

               Contact recipient = manager.SearchContactByEmail(search_term);


                if((res = SearchPostByRecipient( recipient.getEmail() )) != null){

                    System.out.println("Showing the results of the search: ");

                    for(int i = 0; i < res.size(); i++){

                        System.out.println(res.get(i).toString());
                    }
                }

                else{

                    System.out.println("No results.");
                }
            }

            else if(option == 0){

                System.out.println("Exiting the menu");
            }

            else{

                System.out.println("Wrong option. Try using a valid option next time (between 0-4).");
            }
    }

    /**
     * A function that searches for a list of posts with the publication date given
     *
     * @param date publication date of the posts to search for
     * @return A list of post posted on the publication date given
     * @throws ParseException if the format of the dates aren't allowed
     * */

    public ArrayList <Post> SearchPostByDate(String date) throws ParseException {

    	ArrayList<Post> contacts = new ArrayList<Post>();

    	for(int i = 0; i < posts.size(); i++) {
    			if((posts.get(i)).getPublicationStringNoHour().equals(date)) {
    				contacts.add(posts.get(i));
    			}
    		}

        if(contacts.size() != 0){

            return contacts;
        }

        else{

            return null;
        }
    }

    /**
     * A function that searches for the posts with the interests given
     *
     * @param interests Interests of the posts to search for
     * @return A list with all the posts with the same interests as given
     * */

    public ArrayList <Post> SearchPostByInterest(String interests) {

    	ArrayList<Post> contacts = new ArrayList<Post>();

    	for(int i = 0; i < posts.size(); i++) {
    		if(posts.get(i) instanceof Thematic_Post) {
    			if(((Thematic_Post) posts.get(i)).getInterests().contains(interests)) {

    				contacts.add(posts.get(i));

    			}
    		}
    	}

        if(contacts.size() != 0){

            return contacts;
        }

        else{

            return null;
        }


    }

  /**
 * A function that searches for the posts with the owner given
 *
 * @param econtact Owner of the posts to search for
 * @return A list with all the posts with the same owner as given
 * */

    public ArrayList <Post> SearchPostByOwner(String econtact){
    	ArrayList<Post> contacts = new ArrayList<Post>();
    	for(int i = 0; i < posts.size(); i++) {

            if(posts.get(i).getOwner() == manager.SearchContactByEmail(econtact)) {

    			contacts.add(posts.get(i));
    		}
    	}

        if(contacts.size() != 0){

            return contacts;
        }

        else{

            return null;
        }
    }

    /**
   * A function that searches for the posts with the recipient given
   *
   * @param contact Recipient of the posts to search for
   * @return A list with all the posts with the same recipient as given
   * */

    public ArrayList <Post> SearchPostByRecipient(String contact){
    	ArrayList<Post> contacts = new ArrayList<Post>();
    	for(int i = 0; i < posts.size(); i++) {
    		if(posts.get(i) instanceof Individualized_Post){
    				if(((Individualized_Post) posts.get(i)).getRecipients().contains(contact)) {

    				contacts.add(posts.get(i));

    			}
    		}
    	}

        if(contacts.size() != 0){

            return contacts;
        }

        else{

            return null;
        }
    }

    /**
     * A function that saves all the info of the system in a file
     * @throws IOException if the file to load doesn't exist
     * */

    public void SaveFile() throws IOException {

        Properties properties = new Properties();

        InputStream file_ = null;

        file_ = new FileInputStream("properties.properties");

        properties.load(file_);

        String path = properties.getProperty("Posts");

        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));

        for (Post post : posts) {

            bw.write(post.toStringFile());
            bw.write("\n");
        }

        bw.close();
    }



    /**
     * A function that reads all the info of a file and load it all in the system
     * @throws ParseException if the format of the dates aren't allowed
     * @throws IOException if the file to load doesn't exist
     * */

    public void LoadFile() throws ParseException{


        try{
            Properties properties = new Properties();

            InputStream file_ = null;

            file_ = new FileInputStream("properties.properties");

            properties.load(file_);

            String path = properties.getProperty("Posts");
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
            String [] array = new String[9];

            SimpleDateFormat aux_date = new SimpleDateFormat("HH:mm/dd-MM-yyyy");
            StringTokenizer token;

            while((line = br.readLine()) != null){

                Post_Creator_Board aux_post_creator = new Post_Creator_Board();
                Post aux_post;

                token = new StringTokenizer(line, "|");

                for(int i = 0; token.hasMoreTokens(); i++){

                    array[i] = token.nextToken();
                }

                if(array[0].equals("GENERAL")){

                    aux_post = aux_post_creator.getPost(Type.GENERAL, Integer.parseInt(array[1]), array[2], array[3], manager.SearchContactByEmail(array[4]), null, null, null, null);

                    if(!array[5].equals("null")){

                        aux_post.setPublication(aux_date.parse(array[5]));
                    }

                    aux_post.setType(Type.GENERAL);
                    aux_post.setStatus(Status.valueOf(array[6]));
                }

                else if(array[0].equals("INDIVIDUALIZED")){

                    String without_spaces = array[7].replace(" ", "");
                    String without_brackets = without_spaces.substring(1, without_spaces.length()-1);
                    StringTokenizer aux_ind = new StringTokenizer(without_brackets, ",");
                    ArrayList <String> aux_ind_array = new ArrayList<String>();

                    while(aux_ind.hasMoreTokens()){

                        aux_ind_array.add(aux_ind.nextToken());
                    }

                    aux_post = aux_post_creator.getPost(Type.INDIVIDUALIZED, Integer.parseInt(array[1]), array[2], array[3], manager.SearchContactByEmail(array[4]), aux_ind_array, null, null, null);

                    if(!array[5].equals("null")){

                        aux_post.setPublication(aux_date.parse(array[5]));
                    }

                    aux_post.setType(Type.INDIVIDUALIZED);
                    aux_post.setStatus(Status.valueOf(array[6]));
                }

                else if(array[0].equals("THEMATIC")){

                    String without_spaces = array[7].replace(" ", "");
                    String without_brackets = without_spaces.substring(1, without_spaces.length()-1);
                    StringTokenizer aux_ind = new StringTokenizer(without_brackets, ",");
                    ArrayList <String> aux_ind_array = new ArrayList<String>();

                    while(aux_ind.hasMoreTokens()){

                        aux_ind_array.add(aux_ind.nextToken());
                    }

                    aux_post = aux_post_creator.getPost(Type.THEMATIC, Integer.parseInt(array[1]), array[2], array[3], manager.SearchContactByEmail(array[4]), null, aux_ind_array, null, null);

                    if(!array[5].equals("null")){

                        aux_post.setPublication(aux_date.parse(array[5]));
                    }

                    aux_post.setType(Type.THEMATIC);
                    aux_post.setStatus(Status.valueOf(array[6]));
                }

                else{

                    aux_post = aux_post_creator.getPost(Type.FLASH, Integer.parseInt(array[1]), array[2], array[3], manager.SearchContactByEmail(array[4]), null, null, aux_date.parse(array[7]), aux_date.parse(array[8]));

                    if(!array[5].equals("null")){

                        aux_post.setPublication(aux_date.parse(array[5]));
                    }

                    aux_post.setType(Type.FLASH);
                    aux_post.setStatus(Status.valueOf(array[6]));

                    Date aux = new Date();

                    if(((aux.compareTo(aux_date.parse(array[7]))>=0)) && ((aux.compareTo(aux_date.parse(array[8])) <= 0)) && (aux_post.getStatus().equals(Status.WAITING))){

                        aux_post.setStatus(Status.POSTED);
                    }

                    if(((aux.compareTo(aux_date.parse(array[8]))>=0)) && (aux_post.getStatus().equals(Status.POSTED))){

                        aux_post.setStatus(Status.ARCHIVED);
                    }
                }

                posts.add(aux_post);
            }

        br.close();
        }catch(IOException e){
            System.out.println("File not found.");
        }
    }
}

package board_manager;


import contact_manager_package.Contact;
import contact_manager_package.Manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;


public class PostManager {
	
	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	private Scanner in = new Scanner (System.in);
	private ArrayList <Post> posts;
	private Manager manager = Manager.getInstance();
	

	
	
	public void Menu() throws ParseException{

        int option = 1;

        while(option != 0){
        System.out.println("ADVERTISEMENTS MANAGEMENT SYSTEM");
        System.out.println("OPTIONS");
        System.out.println("=====================================");
        System.out.println("=====================================");
        System.out.println("1. Create a post");
        System.out.println("2. Post a post");
        System.out.println("3. Edit a post");
        System.out.println("4. Remove a post");
        System.out.println("5. Search a post");
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
                	
                	in = new Scanner (System.in);
                    System.out.println("What type of post do you want to create?: ");
                    System.out.println("1. General Post.");
                    System.out.println("2. Thematic Post.");
                    System.out.println("3. Individualized Post.");
                    System.out.println("4. Flash Post");
                    System.out.println("0. Cancel");
                    System.out.println("=====================================");
                    System.out.println("Your option: ");
                    option = in.nextInt();
                    switch(option) {
                    	cas
                    }


                    break;

                case 2:


                    break;

                case 3:
               

                	break;
                case 4:

                
                    break;
                  
                case 5:
                	
                	
                	break;
                
                default:
                    System.out.println("Not a valid option. Try again.");
            }
            
        }

    }


	public boolean checkExistence(int id){
        
        Iterator <Post> it = posts.iterator();
        
        while(it.hasNext()){

        	
            if(it.next().getIdentifier() == id){
                
                return true;
            }
        }

        return false;
	}
	
	
	
	public boolean AddPost(Post post) {
		
		if(posts.add(post)){
			post.setStatus(Status.POSTED);
			return true;
		}

		return false;

	}
	

	public void UpdatePost(Post new_post, Post old_post) {
		int index = posts.indexOf(old_post);
		posts.set(index, new_post);
		
	}


	public boolean RemovePost(Post post){
		if(posts.remove(post)){
			post.setStatus(Status.ARCHIVED);
			return true;
		}

		return false;
	}


	public boolean RemovePost(int id){
		if(checkExistence(id)){

			for(int i = 0; i < posts.size(); i++){
				if(posts.get(i).getIdentifier() == id){
					RemovePost(posts.get(i));
					return true;
				}
			}

		}

		System.out.println("The post doesn't exist.");
		return false;

	}


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

            	
                System.out.println("Type the date of the posts to search: ");
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


                if((res = SearchPostByRecipient( recipient )) != null){

                    System.out.println("Showing the results of the search: ");
                    
                    for(int i = 0; i < res.size(); i++){

                        System.out.println(res.get(i).toString());
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
    
    
    
    
    
    
    public ArrayList <Post> SearchPostByDate(String date) throws ParseException {
    	
    	ArrayList<Post> contacts = new ArrayList<Post>();
    	
    	
    	for(int i = 0; i < posts.size(); i++) {
    			if((posts.get(i)).getPublication() == format.parse(date)) {
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
    
    
    
    
    
    public ArrayList <Post> SearchPostByOwner(String econtact){
    	ArrayList<Post> contacts = new ArrayList<Post>();
    	for(int i = 0; i < posts.size(); i++) {
    			if(posts.get(i).getOwner().getEmail() == econtact) {
    				
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
    
    
    
    
    
    
    public ArrayList <Post> SearchPostByRecipient(Contact contact){
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
    
    
    
    
}

	









	
	
	
	
	
	
	
	
	
	
	
	
	


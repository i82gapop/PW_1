package board_manager;


import contact_manager_package.Contact;
import contact_manager_package.Manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Date;
import java.text.*;
import java.io.*;
import java.util.Properties;
import java.util.StringTokenizer;

public class PostManager {
    
    private static PostManager instance = null;

	SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
	private Scanner in = new Scanner (System.in);
    private ArrayList <Post> posts;
    private ArrayList <String> interests;
    private Manager manager = Manager.getInstance();
	
    private PostManager() throws ParseException {

        this.posts = new ArrayList <Post>();
        this.interests = new ArrayList <String>();

        manager.LoadFile();
    }

    public static PostManager getInstance() throws ParseException {

        if(instance == null){
            
            instance = new PostManager();
        }

        return instance;
    }

    public void Menu() throws ParseException{

        Contact user;
        String buff_title, buff_body, log_username, buff_interest, buff_recipients, without_spaces;
    
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
            System.out.println("4. Remove a post");
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
                                System.out.println("When do you want to post it?: ");
                                String buff_date_pub = in.next();
                                System.out.println("When do you want to remove it?: ");
                                String buff_date_end = in.next();


                                aux_post = aux_post_creator.getPost(Type.FLASH, posts.size(), buff_title, buff_body, user, null, null, format.parse(buff_date_pub), format.parse(buff_date_end));
                                aux_post.setType(Type.FLASH);


                                AddPost(aux_post);

                                break;
                        }
                    
                        break;
                    
                    case 2:
                        in = new Scanner (System.in);
                        System.out.println("Type the id of the post to post: ");
                        id = in.nextInt();
                        ToPost(id);
                        
                    
                    
                        break;
                    
                    case 3:
                    
                        in = new Scanner (System.in);
                        System.out.println("What post do you want to edit?: (type the id)");
                        id = in.nextInt();

                        if(checkExistence(id)){

                            int option_1;

                            System.out.println("What info of the post do you want to edit?: ");
                            System.out.println("1. Title");
                            System.out.println("2. Body");
                            System.out.println("3. Interests");
                            System.out.println("=====================================");
                            System.out.println("Your option: ");

                            option_1 = in.nextInt();


                            if(option_1 == 1){
                                System.out.println("Type the new title of the post: ");
                                buff_title = in.next();
                                posts.get(id).setTitle(buff_title);
                                
                            }

                        }



                        break;
                        
                    case 4:

                        in = new Scanner (System.in);
                        System.out.println("What post do you want to remove?: (type the id)");
                        id = in.nextInt();
                        RemovePost(id);

                        
                    
                    
                        break;
                    
                    case 5:

                        ConsultPost();

                    case 6:

                        System.out.println("=====================================");
                        System.out.println("Showing the board for " + log_username);
                        System.out.println("=====================================");
                        System.out.println("General Posts: ");
                        for(int i = 0; i < posts.size(); i++){

                            if((posts.get(i).getType() == Type.GENERAL) && (posts.get(i).getStatus()!= Status.POSTED)){

                                System.out.println("Post ID: " + posts.get(i).getIdentifier());
                                System.out.println("Post Title: " + posts.get(i).getTitle());
                                System.out.println("Post Body: " + posts.get(i).getBody());
                                System.out.println("Post Owner: " + posts.get(i).getOwner());
                                System.out.println("Post ID: " + posts.get(i).getIdentifier());

                            }

                        }
                        System.out.println("=====================================");
                        System.out.println("Thematic Posts: ");
                        System.out.println("=====================================");

                        for(int i = 0; i < posts.size(); i++){

                            if((posts.get(i).getType() == Type.THEMATIC) && (posts.get(i).getStatus()!= Status.POSTED)){
                                if(posts.get(i) instanceof Thematic_Post) {
                                    if(((Individualized_Post) posts.get(i)).getRecipients().contains(log_username)){

                                System.out.println("Post ID: " + posts.get(i).getIdentifier());
                                System.out.println("Post Title: " + posts.get(i).getTitle());
                                System.out.println("Post Body: " + posts.get(i).getBody());
                                System.out.println("Post Owner: " + posts.get(i).getOwner());
                                System.out.println("Post ID: " + posts.get(i).getIdentifier());

                                    }

                                }

                            }
                        }

                        System.out.println("=====================================");
                        System.out.println("Individualized Posts: ");
                        System.out.println("=====================================");

                        for(int i = 0; i < posts.size(); i++){

                            if((posts.get(i).getType() == Type.INDIVIDUALIZED) && (posts.get(i).getStatus()== Status.POSTED)){
                                if(posts.get(i) instanceof Individualized_Post) {
                                    if(((Individualized_Post) posts.get(i)).getRecipients().contains(log_username)){

                                System.out.println("Post ID: " + posts.get(i).getIdentifier());
                                System.out.println("Post Title: " + posts.get(i).getTitle());
                                System.out.println("Post Body: " + posts.get(i).getBody());
                                System.out.println("Post Owner: " + posts.get(i).getOwner());
                                System.out.println("Post ID: " + posts.get(i).getIdentifier());

                                    }

                                }

                            }
                        }




                        System.out.println("=====================================");
                        System.out.println("Flash Posts: ");
                        System.out.println("=====================================");

                        for(int i = 0; i < posts.size(); i++){

                            if((posts.get(i).getType() == Type.INDIVIDUALIZED) && (posts.get(i).getStatus()!= Status.POSTED) ){
                                
                            
                                System.out.println("Post ID: " + posts.get(i).getIdentifier());
                                System.out.println("Post Title: " + posts.get(i).getTitle());
                                System.out.println("Post Body: " + posts.get(i).getBody());
                                System.out.println("Post Owner: " + posts.get(i).getOwner());
                                System.out.println("Post ID: " + posts.get(i).getIdentifier());

                                

                            }

                        }


                    	break;
                    
                    default:
                    System.out.println("Not a valid option. Try again.");
            }
            
        }

    }
    else{
        System.out.println("User not registered in the contact manager.");
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
			post.setStatus(Status.EDITED);
			return true;
		}

		return false;

	}


	public void ToPost(int id) {
    
        for(int i = 0; i < posts.size(); i++){
            if(posts.get(i).getIdentifier() == id){

                posts.get(i).setStatus(Status.POSTED);
                System.out.println("Post posted successfully");
            } 
        }


	}
	

	public void UpdatePost(Post new_post, Post old_post) {
		int index = posts.indexOf(old_post);
		posts.set(index, new_post);
		
	}



	public boolean RemovePost(int id){ 
		if(checkExistence(id)){

			for(int i = 0; i < posts.size(); i++){
				if(posts.get(i).getIdentifier() == id){
					posts.get(i).setStatus(Status.ARCHIVED);
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
            /*
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

        br.close();*/
        }catch(IOException e){
            System.out.println("File not found.");
        }
    }


    public void imprimirPost(){

        for (Post post : posts) {
            
            System.out.println(post.toStringFile());
        }
    }

}
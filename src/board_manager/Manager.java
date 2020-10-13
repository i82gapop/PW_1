package board_manager;


import contact_manager_package.Contact;
import java.util.Iterator;
import java.util.Date;
import java.util.ArrayList;


public class Manager {

	private ArrayList <Post> posts;
	
	
	
	public boolean AddPost(Post post) {
		return posts.add(post);
	}
	
	public void UpdatePost(Post new_post, Post old_post) {
		int index = posts.indexOf(old_post);
		posts.set(index, new_post);
		
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

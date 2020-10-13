package board_manager;

import java.util.ArrayList;


public class Manager {

	private ArrayList <Post> posts;


	public boolean checkExistence(int id){
        
        Iterator <Contact> it = posts.iterator();
        
        while(it.hasNext()){

            if(it.next().getIdentifier().equals(email)){
                
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
				if(posts.get(i).getIdentifier().equals(id)){

					posts.remove(i);
					post.setStatus(Status.ARCHIVED);
					System.out.println("Post removed succesfully");
					return true;
				}
			}

		}

		System.out.println("The post doesn't exist.");
		return false;

	}


	public boolean ToPost(int id){


	}


	public boolean SearchPostBy(){}

	









	
	
	
	
	
	
	
	
	
	
	
	
	
}
